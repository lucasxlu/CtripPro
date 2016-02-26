import org.junit.Test;
import java.io.File;
import java.io.IOException;

/**
 * Created by LucasX on 2016/2/26.
 */
public class FileTest {

    @Test
    public void testFile() throws IOException {
        File file = new File("test.txt");
        file.createNewFile();
    }
}
