package own.stu.springboot.logging.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import own.stu.springboot.logging.LoggingApplication;

@RestController
public class HelloWorld {

  static Logger logger = LoggerFactory.getLogger(HelloWorld.class);

  @RequestMapping("hello")
  public String hello() {

    logger.debug("debug: {}", LoggingApplication.class);
    logger.info("info: {}", LoggingApplication.class);
    logger.trace("trace: {}", LoggingApplication.class);
    logger.warn("warn: {}", LoggingApplication.class);
    logger.error("error: {}", LoggingApplication.class);

    return "success";
  }
}
