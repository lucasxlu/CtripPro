import com.ctrip.util.Toolkit;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

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
}
