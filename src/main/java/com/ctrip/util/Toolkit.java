package com.ctrip.util;

import com.ctrip.entity.City;
import com.ctrip.entity.Csv;
import com.ctrip.entity.Hotel;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.LogManager;

/**
 * Created by LucasX on 2016/2/23.
 */
public class Toolkit {

    private static final Logger logger = org.apache.logging.log4j.LogManager.getLogger();

    public static List<City> readCityXmlConfig(String xmlPath) throws ParserConfigurationException, IOException, SAXException {
        List<City> cityList = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new FileInputStream(new File(xmlPath)));
        Element element = document.getDocumentElement();

        NodeList nodeList = element.getChildNodes();
        int len = nodeList.getLength();
        for (int i = 0; i < len; i++) {
            if (nodeList.item(i) instanceof Element) {
                String name = nodeList.item(i).getTextContent().trim();
                String pinyin = ((Element) nodeList.item(i)).getAttribute("pinyin").trim();
                String id = ((Element) nodeList.item(i)).getAttribute("id").trim();
                City city = new City(pinyin, id, name);
                cityList.add(city);
            }
        }

        return cityList;
    }

    /**
     * 得到入住时间和离开时间，默认为<b>一天</b>
     *
     * @return
     */
    public static String[] getDays() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String inDay = simpleDateFormat.format(new Date());

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, Constant.DEFAULT_CHECKIN_DAYS);
        Date date = calendar.getTime();

        String outDay = simpleDateFormat.format(date);

        return new String[]{inDay, outDay};
    }

    /**
     * 将List<Hotel> 结果写出CSV文件
     *
     * @param folder
     * @param csv
     * @throws IOException
     */
    public static void outPut(String folder, Csv csv) {
        try {
            Files.deleteIfExists(Paths.get(folder + File.separator + csv.getName() + ".csv"));
            Files.createFile(Paths.get(folder + File.separator + csv.getName() + ".csv"));
            StringBuilder stringBuilder = new StringBuilder("编号, 名称, 价格, 距离, 简称, 地址, 商区, 星级, 电话, 描述, 评分人数, 综合得分, 卫生得分, 位置得分, 服务得分, 设施得分, 早餐\r\n");
            csv.getList().forEach(hotel -> {
                stringBuilder.append(hotel.getId()).append(", ").append(hotel.getName()).append(", ").append(hotel.getPrice()).append(", ").append(hotel.getDistance()).append(", ").append(hotel.getShrtName()).append(", ").append(hotel.getAddr()).append(", ").append(hotel.getZone()).append(", ").append(hotel.getStar()).append(", ").append(hotel.getPhe()).append(", ").append(hotel.getBrief()).append(", ").append(hotel.getVote()).append(", ").append(hotel.getPoint()).append(", ").append(hotel.getRat()).append(", ").append(hotel.getRaAt()).append(", ").append(hotel.getServ()).append(", ").append(hotel.getFacl()).append(", ").append(hotel.getBrefast()).append("\r\n");
            });
            Files.write(Paths.get(folder + File.separator + csv.getName() + ".csv"), stringBuilder.toString().getBytes("GBK"));
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("写入失败, 原因是 : " + e.getMessage());
        }
    }


    /**
     * 将List转为数组
     *
     * @param list
     */
    public static String[] convertListToArray(List<String> list) {
        String[] result = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }

        return result;
    }

    /**
     * 将选择的城市列表分拆成两组List
     * 拆分算法:
     * 若item个数为偶数，则每个List均为 item/2
     * 若item个数为奇数，则第一个List为 [item/2], 另一个为 [item/2] + 1
     *
     * @param list
     * @return
     */
    public static List<City>[] getCityListForCrawler(List<String> list) throws IOException, SAXException, ParserConfigurationException {
        List<City>[] cityLists = new List[2];
        List<City> cityList1 = new ArrayList<>();
        List<City> cityList2 = new ArrayList<>();

        for (int i = 0; i < list.size() / 2; i++) {
            cityList1.add(Toolkit.findCityInList(list.get(i)));
        }

        for (int i = list.size() / 2; i < list.size(); i++) {
            cityList2.add(Toolkit.findCityInList(list.get(i)));
        }
        cityLists[0] = cityList1;
        cityLists[1] = cityList2;

        return cityLists;
    }

    private static final City findCityInList(String cityName) throws IOException, SAXException, ParserConfigurationException {
        List<City> cityList = Toolkit.readCityXmlConfig(Constant.CITY_XML_CONFIG);
        Iterator<City> cityIterator = cityList.iterator();
        while (cityIterator.hasNext()) {
            City city = cityIterator.next();
            if (city.getName().equals(cityName))
                return city;
        }
        return null;
    }

    public static void openDirectory(String directoryPath) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        runtime.exec("explorer " + directoryPath);
    }

    /**
     * 合并两个List，返回一个大的List
     *
     * @param objectList1
     * @param objectList2
     * @return
     */
    public static List<Hotel> appendList(List<Hotel> objectList1, List<Hotel> objectList2) {
        if (objectList1.size() < objectList2.size()) {
            objectList1.forEach(o -> {
                objectList2.add(o);
            });
            return objectList2;
        } else {
            objectList2.forEach(o -> {
                objectList1.add(o);
            });
            return objectList1;
        }
    }

    public static List<String> findJar(String txtPath) throws IOException {
        List<String> list = new ArrayList<>();
        Files.readAllLines(Paths.get(txtPath), Charset.forName("GBK")).forEach(s -> {
            if (s.contains("CtripPro_jar") && !list.contains(s.split("CtripPro_jar")[1])) {
                list.add(s.split("CtripPro_jar")[1]);
            }
        });
        return list;
    }

    public static void checkJdkVersion() {
        String jdkVersion = System.getProperties().getProperty("java.version");
        if (!jdkVersion.startsWith("1.8")) {
            JOptionPane.showMessageDialog(null, "您当前的JDK版本为 " + jdkVersion + " ;本系统需要JDK1.8及以上版本支持！");
            System.exit(0);
        }
    }

}