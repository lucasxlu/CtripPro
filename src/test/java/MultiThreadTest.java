import com.ctrip.spider.MultiThreadCrawlerOne;
import com.ctrip.spider.MultiThreadCrawlerTwo;
import org.junit.Test;

/**
 * Created by LucasX on 2016/2/25.
 */
public class MultiThreadTest {

    @Test
    public void testMultiThread() {
        MultiThreadCrawlerOne multiThreadCrawlerOne = new MultiThreadCrawlerOne();
        MultiThreadCrawlerTwo multiThreadCrawlerTwo = new MultiThreadCrawlerTwo();

        Thread thread1 = new Thread(multiThreadCrawlerOne);
        Thread thread2 = new Thread(multiThreadCrawlerTwo);

        thread1.start();
        System.out.println("线程1开始运行...");
        thread2.start();
        System.out.println("线程2开始运行...");

    }
}
