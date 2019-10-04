package own.springboot.with.mybatis;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.Data;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class Battle {

  public static void main(String[] args) throws Exception {
    ExecutorService executorService = Executors.newFixedThreadPool(20);

    AtomicInteger count = new AtomicInteger();
    for (int i = 0; i < 20; i++) {
      executorService.submit(() -> {
        RestTemplate restTemplate = getRestTemplate();
        for (int j = 0; j < 45; j++) {
          send(restTemplate);
          System.out.println("send ->> " + count.incrementAndGet());
        }
      });
    }

  }

  public static void main2(String[] args) throws Exception {

    RestTemplate restTemplate = getRestTemplate();

    for (; ; ) {

//      TimeUnit.SECONDS.sleep(70);

    }
  }

  private static void send(RestTemplate restTemplate) {
    String url = "https://battlehand-game-kong.anotherplacegames.com/v1/packet/execute";

    String token = "f847502817f1d728dd3be89c0fe43d5be848197fd8c579240edd61fd2339868f";
    String content = "{\"player\":\"0bf973d3-d476-49f2-a51a-dd749c8d9ded\",\"sessionKey\":\"7cd968090fcc441f8efd2f211422d9e5\",\"packets\":[{\"id\":\"0efaf9b3-c649-449a-b3ff-4adb1999aaf9\",\"commands\":[{\"cmd\":\"add_quest_progress\",\"args\":{\"quest\":\"e3c53eb5-a5fb-4574-91d4-e6df3ad04fe2\",\"objective\":\"60764c12-0a10-408b-8cc3-44fd972b03b7\",\"progress\":419}}]},{\"id\":\"407c0b3b-7f65-41f0-bf45-5793a6e73f27\",\"commands\":[{\"cmd\":\"add_crafting_materials\",\"args\":{\"material\":\"6960b015-604c-4e36-8d79-6e995686db4c\",\"quantity\":1}}]},{\"id\":\"e139ff87-da8a-4c8c-8d38-0cbc8a08e16d\",\"commands\":[{\"cmd\":\"complete_dungeon\",\"args\":{\"dungeon\":\"dd98a3ea-5ff0-48ae-a3cf-7c5073ead513\",\"rewards\":[\"47db5c96-cc72-4990-a3d5-79e3e8df64a5\"],\"loot_modify_factor\":1.0,\"stars\":7,\"level\":11,\"archetypes\":[\"b3747d9a-8820-4d04-8d06-76dec01a4862\",\"292751d2-d264-4b36-9fbf-84b98ddeca58\"]}}]}]}";
//    SimpleData simpleData = new SimpleData(token, content);

    HttpHeaders headers = new HttpHeaders();
    MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
    headers.setContentType(type);
    headers.add("Accept", MediaType.APPLICATION_JSON.toString());
    headers.add("User-Agent",
        "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.100 Mobile Safari/537.36");
    headers.add("X-APP-Sign", token);
    HttpEntity<String> formEntity = new HttpEntity<>(content, headers);

    String result = restTemplate.postForObject(String.format(url), formEntity, String.class);
    System.out.println(result);
  }

  @Test
  public void test() {

  }


  protected static RestTemplate getRestTemplate() {
    RestTemplate restTemplate = new RestTemplate();
    List<HttpMessageConverter<?>> converters = restTemplate.getMessageConverters();
    for (HttpMessageConverter converter : converters) {
      if (converter instanceof StringHttpMessageConverter) {
        ((StringHttpMessageConverter) converter).setDefaultCharset(StandardCharsets.UTF_8);
        break;
      }
    }
    return restTemplate;
  }

  @Data
  static class SimpleData {

    String token;

    String content;

    public SimpleData(String token, String content) {
      this.token = token;
      this.content = content;
    }
  }
}

