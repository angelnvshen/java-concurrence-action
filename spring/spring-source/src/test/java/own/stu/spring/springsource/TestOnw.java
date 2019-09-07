package own.stu.spring.springsource;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.mockito.internal.util.io.IOUtil;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TestOnw {

    @Test
    public void test12() throws InterruptedException {

        for (; ; ) {
            String url = "https://battlehand-game-kong.anotherplacegames.com/v1/player/stamina?player=0bf973d3-d476-49f2-a51a-dd749c8d9ded&sessionKey=c34716e8e59949128e126e2d65fa81e8";

            RestTemplate restTemplate = getRestTemplate();
            MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(type);
            headers.add("Accept", MediaType.APPLICATION_JSON.toString());
            headers.add("X-APP-Sign", "ec6985e0b55abaae8d350a8b6c6f50cc92d83e4bcbfd2a85536e8654cf1f7b2a");
            headers.add("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36");
            ResponseEntity<Stamina> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    new HttpEntity<String>(headers),
                    Stamina.class);
            if (response.getStatusCode().equals(HttpStatus.OK)) {
                Stamina stamina = response.getBody();
                if (stamina.getStamina() > 6) {
                    for (; ; ) {
                        try {
                            test2();
                        } catch (Exception e) {
                            break;
                        }
                    }
                } else {
                    TimeUnit.MINUTES.sleep(3);
                }
            }
        }
    }


    public void test2() {
        HttpHeaders headers = new HttpHeaders();
        String url = "https://battlehand-game-kong.anotherplacegames.com/v1/packet/execute";
        String content = "{\\\"player\\\":\\\"0bf973d3-d476-49f2-a51a-dd749c8d9ded\\\",\\\"sessionKey\\\":\\\"c34716e8e59949128e126e2d65fa81e8\\\",\\\"packets\\\":[{\\\"id\\\":\\\"35b3b10b-8aa3-46e1-ba3c-f70c9fb0468e\\\",\\\"commands\\\":[{\\\"cmd\\\":\\\"leave_dungeon_sequence\\\",\\\"args\\\":{\\\"dungeon_sequence\\\":\\\"1c0c0109-c7c6-4cf2-99b0-2d89b1077677\\\",\\\"dungeon\\\":\\\"1cfb9a26-8b9e-4749-bedf-927ebb51acd8\\\",\\\"difficulty\\\":0}}]},{\\\"id\\\":\\\"91c61284-d22f-45f1-945f-b2db0e382052\\\",\\\"commands\\\":[{\\\"cmd\\\":\\\"add_feeder_card\\\",\\\"args\\\":{\\\"card\\\":\\\"2f84bc97-2002-40bd-a509-e064fee3f44f\\\",\\\"quantity\\\":1}}]},{\\\"id\\\":\\\"394b9d72-407e-4dea-aab0-fc23f055608b\\\",\\\"commands\\\":[{\\\"cmd\\\":\\\"add_quest_progress\\\",\\\"args\\\":{\\\"quest\\\":\\\"6495977e-b081-4f37-a91c-c9e1ac49dad6\\\",\\\"objective\\\":\\\"ee3c924e-0804-4d40-9ad9-96a6ea8350e4\\\",\\\"progress\\\":1}}]},{\\\"id\\\":\\\"f34ac1b8-2076-42b4-adb4-2a73bcf8ccca\\\",\\\"commands\\\":[{\\\"cmd\\\":\\\"add_crafting_materials\\\",\\\"args\\\":{\\\"material\\\":\\\"e9b58af4-8ac1-489a-ae13-d5184b61868a\\\",\\\"quantity\\\":1}}]},{\\\"id\\\":\\\"70f4fc8e-c9ad-4b2b-a917-d6486d3ca249\\\",\\\"commands\\\":[{\\\"cmd\\\":\\\"add_crafting_materials\\\",\\\"args\\\":{\\\"material\\\":\\\"3847a2fb-0414-4c6c-807b-9d8d6ba1bf60\\\",\\\"quantity\\\":1}}]},{\\\"id\\\":\\\"fdae547e-09fa-42dc-b897-603601b8113d\\\",\\\"commands\\\":[{\\\"cmd\\\":\\\"add_crafting_materials\\\",\\\"args\\\":{\\\"material\\\":\\\"da8997fb-a571-4797-870c-a3dd03d4cf1b\\\",\\\"quantity\\\":1}}]},{\\\"id\\\":\\\"e16455cc-43eb-4c00-b9a3-a9d072360711\\\",\\\"commands\\\":[{\\\"cmd\\\":\\\"complete_dungeon\\\",\\\"args\\\":{\\\"dungeon\\\":\\\"1cfb9a26-8b9e-4749-bedf-927ebb51acd8\\\",\\\"rewards\\\":[\\\"bdbf766b-ad52-4d97-802d-4cfea757c8b2\\\",\\\"47db5c96-cc72-4990-a3d5-79e3e8df64a5\\\",\\\"51ca8240-ce8f-4c8e-8545-5fc15dbee12e\\\"],\\\"loot_modify_factor\\\":1.0,\\\"stars\\\":7,\\\"level\\\":19,\\\"archetypes\\\":[\\\"b3747d9a-8820-4d04-8d06-76dec01a4862\\\",\\\"292751d2-d264-4b36-9fbf-84b98ddeca58\\\"]}}]},{\\\"id\\\":\\\"116ba5a9-a425-42aa-a4b0-b2750da28e8a\\\",\\\"commands\\\":[{\\\"cmd\\\":\\\"add_quest_progress\\\",\\\"args\\\":{\\\"quest\\\":\\\"c7ecc8f5-f80b-4e47-b88d-2f8c21a4eee4\\\",\\\"objective\\\":\\\"3f28a091-5ab3-48c1-9cea-a2e58b8852b6\\\",\\\"progress\\\":1}}]}]}";

        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        headers.add("X-APP-Sign", "abbe0defd0597d85a708a227deefafed4c6ccb3e23cf5dc00681712f134577b2");
        headers.add("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36");
        HttpEntity<String> formEntity = new HttpEntity<>(content, headers);
        RestTemplate restTemplate = getRestTemplate();
        try {
            String result = restTemplate.postForObject(String.format(url), formEntity, String.class);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("hhhhh");
        }

    }

    public static void main22(String[] args) {
        System.out.println("76890-");
    }

    public static void main(String[] args) {
        HttpHeaders headers = new HttpHeaders();
        String url = "https://battlehand-game-kong.anotherplacegames.com/v1/packet/execute";

        String content = "{\"player\":\"0bf973d3-d476-49f2-a51a-dd749c8d9ded\",\"sessionKey\":\"2cfa741a325345c080c6cfdf10a8df53\",\"packets\":[{\"id\":\"b91a838a-0722-43b0-a24f-9d6dec2e5109\",\"commands\":[{\"cmd\":\"add_feeder_card\",\"args\":{\"card\":\"02a9bb2b-f32f-438a-911c-735facf21390\",\"quantity\":1}}]},{\"id\":\"eec26ebd-8854-4c66-a994-e25fd2e4c98c\",\"commands\":[{\"cmd\":\"add_feeder_card\",\"args\":{\"card\":\"392c9733-de3f-4ea2-8ae4-e4f3dbce74f8\",\"quantity\":1}}]},{\"id\":\"d79765d4-4173-46fe-9045-c85d877af028\",\"commands\":[{\"cmd\":\"add_crafting_materials\",\"args\":{\"material\":\"3cc591fe-7e7e-4e1a-bce6-712868f16c47\",\"quantity\":1}}]},{\"id\":\"5c63c734-4267-41d7-a6b3-ce8847306ac2\",\"commands\":[{\"cmd\":\"add_crafting_materials\",\"args\":{\"material\":\"6fb2726b-674c-42da-a571-010f3b95edd3\",\"quantity\":1}}]},{\"id\":\"cff656d2-6616-4dcd-aa64-1489a579fe9b\",\"commands\":[{\"cmd\":\"add_crafting_materials\",\"args\":{\"material\":\"720c5250-4cc6-458d-a7c4-fa2d72029301\",\"quantity\":1}}]},{\"id\":\"3ec1a861-b4fb-4848-ab0c-140f750573f4\",\"commands\":[{\"cmd\":\"complete_dungeon\",\"args\":{\"dungeon\":\"be77fff3-f252-43b1-89aa-f63831a7f027\",\"rewards\":[\"47db5c96-cc72-4990-a3d5-79e3e8df64a5\"],\"loot_modify_factor\":1.0,\"stars\":7,\"level\":30,\"archetypes\":[\"ef412dd7-80a5-444f-aba5-be1cd8024360\",\"442cb574-92c2-42a8-a55b-62316b3fcf10\"]}}]}]}";

        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        headers.add("X-APP-Sign", "c4c2b41fefefe49dd2025fcad48ba592b41e8b2446516ebb8573fd5627bb7255");
        headers.add("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36");
        HttpEntity<String> formEntity = new HttpEntity<>(content, headers);

        ExecutorService executorService = Executors.newFixedThreadPool(30);
        for (int t = 0; t < 30; t++)
            executorService.submit(() -> {
                RestTemplate restTemplate = getRestTemplate();
                for (int i = 0; i < 8; i++) {
                    String result = restTemplate.postForObject(String.format(url), formEntity, String.class);
                    System.out.println(result);
                }
            });

        executorService.shutdown();
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

    @Test
    public void test3() {

        int m = 500;
        Integer obj = m; // 自动装箱
        int n = obj; // 自动拆箱
        System.out.println("n = " + n);
        Integer obj1 = 500;
        System.out.println("obj 等价于 obj1？" + obj.equals(obj1));
        Integer i1 = new Integer(501);
        Integer i2 = new Integer(501);
        System.out.println("" + i1);
        System.out.println("" + i2);
        System.out.println("obj 等价于 obj1？" + i1.equals(i2));
        System.out.println("obj 等价于 obj1？" + (i1 == i2));
    }

    public static void main2(String[] args) throws InterruptedException {
        TestO t0 = new TestO();
        TestO t1 = new TestO();
        TestO t2 = new TestO();
        TestO t3 = new TestO();
        TestO t4 = new TestO();
        t0.start();
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t0.join();
        t1.join();
        t2.join();
        t4.join();
        t3.join();
    }


    static class TestO extends Thread {
        static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(2);
        static AtomicInteger at = new AtomicInteger();

        public void run() {
            while (at.get() < 100000) {
                map.put(at.get(), at.get());
                at.incrementAndGet();
            }
        }
    }

    @Test
    public void test5() {
        index();
    }

    public String index() {

        String jsonStr = "";

        try {
            ClassPathResource resource = new ClassPathResource("javaHash.txt");
            jsonStr = FileCopyUtils.copyToString(new FileReader(resource.getFile()));

        } catch (IOException e) {
            System.out.println("指定文件不存在");//处理异常
        }

        Map<String, Object> map = JSON.parseObject(jsonStr, Map.class);
        System.out.println(map.size());
        return "Hash Collision ~";
    }
}

