package com.ctrip.spider;

import com.ctrip.entity.City;
import com.ctrip.entity.Csv;
import com.ctrip.entity.Hotel;
import com.ctrip.util.BrowserVersion;
import com.ctrip.util.Constant;
import com.ctrip.util.Toolkit;
import org.apache.http.HttpEntity;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LucasX on 2016/2/23.
 */
public class CtripSpider {

    private static List<String> visitedHotel = new ArrayList<>();
    private static final String REFERER_PREFIX = "http://m.ctrip.com/webapp/hotel/";
    private static final Logger logger = LogManager.getLogger();
    public static String folder = "D:/携程";
    private static Csv csv = null;
    private static List<Hotel> hotelList = null;

    /**
     * 查询指定城市下的酒店信息
     *
     * @param city
     * @return
     * @throws UnsupportedEncodingException
     */
    public static void searchHotelByCity(City city) throws IOException, InterruptedException {

        hotelList = new ArrayList<>();
        String urlReferer = CtripSpider.REFERER_PREFIX + city.getPinyin() + city.getId() + "/checkin-" + Constant.DEFAULT_CHECKIN_DAYS + "-0?fr=index";
        logger.trace("urlReferer : " + urlReferer);
        String url = "http://m.ctrip.com/restapi/soa2/10932/hotel/Product/HotelGet";

        //get inDay and outDay
        String[] days = Toolkit.getDays();

        CloseableHttpClient closeableHttpClient = CtripSpider.customeCloseableHttpClient();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type", "application/json");

        int pageNum = 1;
        boolean flag = true;

        while (flag) {
            httpPost.setEntity(new StringEntity(CtripSpider.generatePayload(pageNum, city.getId(), days[0], days[1])));
            HttpEntity httpEntity = closeableHttpClient.execute(httpPost).getEntity();
            String jsonData = EntityUtils.toString(httpEntity);

            flag = CtripSpider.extractHotelFromPerPage(jsonData, city.getName());
            pageNum++;
        }

        Thread.sleep(5000);
        Toolkit.openDirectory(folder);
        System.exit(0);
    }

    private static CloseableHttpClient customeCloseableHttpClient() {
        // Create a local instance of cookie store
        CookieStore cookieStore = new BasicCookieStore();
        // Populate cookies if needed
        BasicClientCookie cookie = new BasicClientCookie("cookie", Constant.COOKIE);
        cookie.setDomain("m.ctrip.com");
        cookie.setPath("/");
        cookieStore.addCookie(cookie);
        return HttpClients.custom().setDefaultCookieStore(cookieStore).setUserAgent(BrowserVersion.CHROME).build();
    }

    /**
     * 动态设置 Http Payload参数
     *
     * @param index
     * @return
     */
    private static String generatePayload(int index, String cityId, String inDay, String outDay) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("biz", 1);
        payload.put("contrl", 3);
        payload.put("facility", 0);
        payload.put("faclist", new ArrayList<>());
        payload.put("key", "");
        payload.put("keytp", 0);
        payload.put("pay", 0);
        payload.put("querys", new ArrayList<>());

        Map<String, Object> setInfo = new HashMap<>();
        setInfo.put("cityId", cityId);
        setInfo.put("dstId", 0);
        setInfo.put("inDay", inDay);
        setInfo.put("outDay", outDay);
        payload.put("setInfo", setInfo);

        Map<String, Object> sort = new HashMap<>();
        sort.put("dir", 1);
        sort.put("idx", index);
        sort.put("ordby", 0);
        sort.put("size", Constant.HOTEL_NUMBER);
        payload.put("sort", sort);


        payload.put("qbitmap", 0);

        Map<String, Object> alliance = new HashMap<>();
        alliance.put("ishybrid", 0);
        payload.put("alliance", alliance);

        Map<String, Object> head = new HashMap<>();
        head.put("cid", "09031076410193757977");
        head.put("ctok", "");
        head.put("cver", "1.0");
        head.put("lang", "01");
        head.put("sid", "8888");
        head.put("syscode", "09");
        head.put("auth", null);

        List<Map> extension = new ArrayList<>();
        Map<String, String> extensionSub = new HashMap<>();
        extensionSub.put("name", "protocal");
        extensionSub.put("value", "http");
        extension.add(extensionSub);

        head.put("extension", extension);
        payload.put("head", head);

        payload.put("contentType", "json");

        return JSONValue.toJSONString(payload);
    }

    public static boolean extractHotelFromPerPage(String jsonData, String cityName) {

        JSONObject jsonObject = (JSONObject) JSONValue.parse(jsonData);
        JSONArray jsonArray = (JSONArray) JSONValue.parse(jsonObject.get("htlInfos").toString());
        if (!"[]".equals(jsonObject.get("htlInfos").toString())) {
            //loop 15 hotels' info in each response json
            jsonArray.forEach(hotelJsonObject -> {
                JSONObject allInfoJsonObject = (JSONObject) hotelJsonObject;

                JSONObject basicInfoJsonObj = (JSONObject) allInfoJsonObject.get("baseInfo");
                String zone = basicInfoJsonObj.get("zone").toString();
                String name = basicInfoJsonObj.get("name").toString();
                String id = basicInfoJsonObj.get("id").toString();

                JSONObject extendJsonObj = (JSONObject) allInfoJsonObject.get("extend");
                String distance = extendJsonObj.get("distance").toString();
                String voter = extendJsonObj.get("voter").toString();
                String point = extendJsonObj.get("point").toString();

                JSONObject activeinfoJsonObj = (JSONObject) allInfoJsonObject.get("activeinfo");
                String star = activeinfoJsonObj.get("star").toString();

                JSONArray pricesJsonObj = (JSONArray) allInfoJsonObject.get("prices");
                String price = ((JSONObject) ((JSONObject) pricesJsonObj.get(0)).get("detail")).get("price").toString();

                if (!visitedHotel.contains(id)) {
                    Hotel hotel = new Hotel(id, name, zone, price, point, voter, distance, star);
                    logger.debug(hotel);
                    hotelList.add(hotel);
                    visitedHotel.add(id);
                }
            });
            csv.setList(hotelList);
            try {
                Toolkit.outPut(CtripSpider.folder, csv);
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("城市 " + cityName + " 的数据写入失败...");
            }
            return true;
        } else {
            logger.debug("数据获取完成，程序将在5s后自动退出...");
            return false;
        }
    }

    public void process(List<City> cityList) {
        cityList.forEach(city -> {
            try {
                csv = new Csv();
                CtripSpider.searchHotelByCity(city);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

}