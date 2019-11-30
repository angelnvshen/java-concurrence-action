package own.guava.base.io;

import com.google.common.io.BaseEncoding;
import org.junit.Test;

/**
 * encode :
 * 1：string -> byte[];
 * 2：byte[] -> byteString(8*byte[].len) = byteStr;
 * 3：byteStr % 6 != 0 ，补足0 -> byteStr2;
 * 4：byteStr2 /6， 每6个字符映射对应的字符表，如果有剩余，每2个0，补足一个=。
 */
public class BaseEncodingTest {

    @Test
    public void test() {

        String content = "hello";

        BaseEncoding baseEncoding = BaseEncoding.base64();

        String encode = baseEncoding.encode(content.getBytes());
        String decode = new String(baseEncoding.decode(encode));

        assert content.equals(decode);
    }


}
