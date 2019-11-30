package own.guava.base.io;

import com.google.common.io.CharSource;
import org.junit.Test;

import java.io.IOException;

public class CharSourceTest {

    @Test
    public void test() throws IOException {
        CharSource wrap = CharSource.wrap("this is very hard year, ");
        wrap.readLines().stream().forEach(System.out::println);

    }
}
