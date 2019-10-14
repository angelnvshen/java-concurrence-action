package own.stu.springboot.async.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import own.stu.springboot.async.service.AsyncService;
import own.stu.springboot.async.service.SimpleServiceImpl;

@RestController
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private AsyncService asyncService;

    @Autowired
    private SimpleServiceImpl simpleService;

    @RequestMapping("/")
    public String submit() {
        logger.info("start submit");

        //调用service层的任务
        asyncService.executeAsync();

        logger.info("end submit");

        return "success";
    }

    @RequestMapping("/test")
    public void test(){
        logger.info("test start submit");
        simpleService.test();
        logger.info("test end submit");
    }
}