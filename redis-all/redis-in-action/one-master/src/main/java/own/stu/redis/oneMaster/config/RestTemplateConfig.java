package own.stu.redis.oneMaster.config;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
public class RestTemplateConfig {

    @Bean("httpRestTemplate")
    public RestTemplate httpRestTemplate() {

        HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        RestTemplate restTemplate = new RestTemplate(httpComponentsClientHttpRequestFactory);
        addMessageConverts(restTemplate);

        return restTemplate;
    }

    @Bean("okRestTemplate")
    public RestTemplate okRestTemplate() {

        RestTemplate restTemplate = new RestTemplate(new OkHttp3ClientHttpRequestFactory());
        addMessageConverts(restTemplate);

        return restTemplate;
    }

    private void addMessageConverts(RestTemplate restTemplate) {

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
        messageConverters.add(converter);

        ByteArrayHttpMessageConverter byteArrayHttpMessageConverter = new ByteArrayHttpMessageConverter();
        byteArrayHttpMessageConverter.setSupportedMediaTypes(
                Lists.newArrayList(MediaType.MULTIPART_FORM_DATA, MediaType.IMAGE_JPEG, MediaType.IMAGE_GIF, MediaType.IMAGE_GIF));
        messageConverters.add(byteArrayHttpMessageConverter);

        restTemplate.setMessageConverters(messageConverters);
    }
}
