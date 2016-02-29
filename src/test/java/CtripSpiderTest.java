import com.ctrip.entity.City;
import com.ctrip.spider.CtripSpider;
import org.json.simple.JSONValue;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by LucasX on 2016/2/23.
 */
public class CtripSpiderTest {


    @Test
    public void testSearchHotelDetailByHotelId() throws IOException {
        CtripSpider ctripSpider = new CtripSpider();
        ctripSpider.searchHotelDetailByHotelId(435924);
    }



}
