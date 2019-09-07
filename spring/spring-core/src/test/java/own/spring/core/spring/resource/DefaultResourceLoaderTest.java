package own.spring.core.spring.resource;

import java.io.IOException;
import java.nio.charset.Charset;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.ResourcePropertySource;

public class DefaultResourceLoaderTest {

  @Test
  public void test() throws IOException {
    DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
    Resource resource = resourceLoader.getResource("classpath:jdbc.properties");
    if (resource instanceof ClassPathResource) {
      System.out.println(" ---- " + ((ClassPathResource) resource).getPath());
    }
    EncodedResource encodedResource = new EncodedResource(resource, Charset.defaultCharset());
    ResourcePropertySource resourcePropertySource = new ResourcePropertySource(encodedResource);

    System.out.println(resourcePropertySource.getProperty("jdbc.username"));
    System.out.println(resourcePropertySource.getProperty("chinese"));
  }
}