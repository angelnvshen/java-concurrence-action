package own.spring.cloud.zuul.funny;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 喜马拉雅音乐 爬音乐
 */
@Slf4j
public class DownXimalayaYinYue {

    String url = "http://audio.xmcdn.com/group59/M0A/53/7A/wKgLeFy8mSqzkJ-xAKbUQHfsTNw570.m4a";

    protected RestTemplate restTemplate;

    @Before
    public void getRestTemplate() {
        restTemplate = new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }

    @Test
    public void rename() {
        File file = new File("/Users/my/Desktop/fire");
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            Arrays.asList(files).stream().forEach(file1 -> {
                System.out.println(file1.getName());
                int start = file1.getName().indexOf("雪");
                String newName = file1.getName().substring(0, start +1) + "(下).mp3";
                System.out.println(newName);
                file1.renameTo(new File("/Users/my/Desktop/fire/" + newName));
            });
        }
    }

    @Test
    public void test3() {
        int i = 104;
        char c = (char) i;
        System.out.println(c);

        System.out.println(Date.from(Instant.ofEpochMilli(1571538087263L)));

    }

    @Test
    public void test2() throws UnsupportedEncodingException {

        long now = new Date().getTime();

        HttpHeaders headers = new HttpHeaders();
        headers.add("authority", "www.ting22.com");
        headers.add("method", "GET");
        headers.add("path", "/api.php?c=Json&id=1255&page=5&pagesize=10&callback=/**/jQuery21407081450534972427_1571538087232&_=1571538087233");
        headers.add("scheme", "https");
        headers.add("accept", "text/javascript, application/javascript, application/ecmascript, application/x-ecmascript, */*; q=0.01");
        headers.add("accept-encoding", "gzip, deflate, br");
        headers.add("accept-language", "zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7");
        headers.add("cookie", "PHPSESSID=12b9n8e436ig39389bhpqgm7b3; Hm_lvt_b3038a04fad08f9e74270f8999205b49=1570237561,1571532924; index_setID=1255; 1255_setNAME=%E9%9B%AA%E4%B8%AD%E6%82%8D%E5%88%80%E8%A1%8C%EF%BC%88%E4%B8%8B%E9%83%A8%EF%BC%89%20%E7%AC%AC898%E7%AB%A0; 1255_setURL=https://www.ting22.com/ting/1255-41.html; Hm_lpvt_b3038a04fad08f9e74270f8999205b49=1571538087");
        headers.add("referer", "https://www.ting22.com/ting/1255-41.html");
        headers.add("sec-fetch-mode", "cors");
        headers.add("sec-fetch-site", "same-origin");
        headers.add("sign", now + "");
        headers.add("x-requested-with", "XMLHttpRequest");

        String page_url = "https://www.ting22.com/api.php?c=Json&id=1255&page=1&pagesize=100&callback=jQuery21407081450534972427_1571538087232&_=1571538087233";
        HttpEntity<Resource> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(page_url, HttpMethod.GET,
                httpEntity, String.class);

        log.info("===状态码================");
        log.info(">> {}", response.getStatusCodeValue());
        log.info("===返回信息================");
        log.info(">> {}", response.getHeaders().getContentType());
        log.info(">> {}", response.getHeaders().getContentType().getSubtype());

        String result = new String(response.getBody().getBytes(), "UTF-8");

        int start = result.indexOf("{");
        int end = result.lastIndexOf("}");
        result = result.substring(start, end + 1);

        PageInfo pageInfo = JSON.parseObject(result, PageInfo.class);
        System.out.println(pageInfo);
        System.out.println(pageInfo.getPlaylist().size());
        pageInfo.playlist.stream().forEach(song -> {
            String file = song.file;
            if (StringUtils.isEmpty(file)) {
                return;
            }
            file = formatFile(file);
            getSong(restTemplate, file, "/Users/my/Desktop/fire", song.getTrackName(), ".mp3");
        });
    }

    @Test
    public void test4() {
        System.out.println(formatFile("104*116*116*112*58*47*47*97*117*100*105*111*46*120*109*99*100*110*46*99*111*109*47*103*114*111*117*112*51*51*47*77*48*54*47*69*56*47*55*48*47*119*75*103*74*84*70*111*67*117*51*88*65*65*109*84*66*65*74*81*87*51*45*113*117*53*65*81*52*51*50*46*109*52*97"));
    }

    private String formatFile(String file) {
        if (StringUtils.isEmpty(file)) {
            return "";
        }
        String[] split = file.split("\\*");
        StringBuffer sb = new StringBuffer();
        for (String s : split) {
            sb.append((char) Integer.valueOf(s).intValue());
        }
        return sb.toString();
    }

    @Test
    public void test1() throws UnsupportedEncodingException {
        String str = "\u96ea\u4e2d\u608d\u5200\u884c\uff08\u4e0b\u90e8\uff09";
        System.out.println(new String(str.getBytes(), "UTF-8"));
//        System.out.println(String.valueOf(JSON.parse(str)));

    }

    public void getSong(RestTemplate restTemplate, String url, String dir, String prefix, String suffix) {

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Resource> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<byte[]> response = restTemplate.exchange(url, HttpMethod.GET,
                httpEntity, byte[].class);
        log.info("===状态码================");
        log.info(">> {}", response.getStatusCodeValue());
        log.info("===返回信息================");
        log.info(">> {}", response.getHeaders().getContentType());
        log.info(">> {}", response.getHeaders().getContentType().getSubtype());
        try {
            File file = File.createTempFile(prefix, suffix, new File(dir));
            System.out.println(file.getAbsolutePath());
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(response.getBody());
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Data
    public static class PageInfo {
        private String title;
        private String author;
        private String announcer;
        private Integer limit;
        private List<Song> playlist;
    }

    @Data
    public static class Song {
        private String url;
        private String file;
        private String trackName;
        private Integer pid;
    }
}
