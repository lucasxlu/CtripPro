import com.ctrip.entity.City;
import com.ctrip.util.Toolkit;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LucasX on 2016/2/23.
 */
public class ToolkitTest {

    @Test
    public void testReadCityXmlConfig() {
        try {
            Toolkit.readCityXmlConfig("D:\\Users\\CtripPro\\src\\main\\resources\\city.xml").forEach(city -> System.out.println(city));
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetDays() {
        String[] days = Toolkit.getDays();
        for (String day : days)
            System.out.println(day);
    }

    @Test
    public void testGetCityListForCrawler() throws ParserConfigurationException, SAXException, IOException {
        List<String> list = new ArrayList<>();
        list.add("武汉");
        list.add("北京");
        list.add("广州");
        List<City>[] lists = Toolkit.getCityListForCrawler(list);
        System.out.println("第一个列表...");
        lists[0].forEach(city -> {
            System.out.println(city);
        });

        System.out.println("第二个列表...");
        lists[1].forEach(city -> {
            System.out.println(city);
        });
    }
}
