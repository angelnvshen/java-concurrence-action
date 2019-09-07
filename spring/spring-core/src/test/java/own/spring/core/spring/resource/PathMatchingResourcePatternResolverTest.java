package own.spring.core.spring.resource;

import java.io.IOException;
import org.junit.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

public class PathMatchingResourcePatternResolverTest {

  @Test
  public void test() throws IOException {

    PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
//    patternResolver.setPathMatcher(new AntPathMatcher("classpath:jdbc*.properties"));
    Resource[] resources = patternResolver.getResources("classpath*:jdbc*.properties");
    for (Resource resource : resources) {
      System.out.println(resource.getFilename());
    }
  }
}
