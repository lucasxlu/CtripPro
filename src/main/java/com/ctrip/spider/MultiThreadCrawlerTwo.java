package com.ctrip.spider;

import com.ctrip.entity.City;
import com.ctrip.util.Toolkit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

/**
 * Created by LucasX on 2016/2/25.
 */
public class MultiThreadCrawlerTwo extends CtripSpider implements Runnable {

    private static Logger logger = LogManager.getLogger();

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        try {
            List<City>[] cityLists = Toolkit.getCityListForCrawler(MainJFrame.listCityData);
            logger.info(Thread.currentThread().getName() + "新建了list");
            cityLists[1].forEach(city -> {
                try {
                    CtripSpider.searchHotelByCity(city);
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.error(city.getName() + " 的数据获取失败...");
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

    }


}
