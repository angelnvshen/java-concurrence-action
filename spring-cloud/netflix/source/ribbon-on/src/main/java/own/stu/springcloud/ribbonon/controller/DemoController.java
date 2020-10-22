package own.stu.springcloud.ribbonon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DemoController {

    private static final String URL = "http://product/product/get-by-id?productId=%d";

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/ribbon")
    public String ribbon(@RequestParam(value = "id", defaultValue = "1") Integer id) {
        return this.restTemplate.getForObject(String.format(DemoController.URL, id), String.class);
    }

}
