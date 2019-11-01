package own.stu.redis.oneMaster.logging.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import own.stu.redis.oneMaster.logging.service.LoggingService;

import java.util.Date;
import java.util.Random;

@RequestMapping("logging")
@Controller
public class LoggingController {

    @Autowired
    private LoggingService loggingService;

    private static Random random = new Random(new Date().getTime());

    @ResponseBody
    @RequestMapping("recent")
    public String logRecent(String type, String message, @RequestParam("l") String severityCode) {

        loggingService.log_recent(type, severityCode, message);
        return "SUCCESS";
    }

    @ResponseBody
    @RequestMapping("recent-fake")
    public String logRecentFake(String type, String message, @RequestParam("l") String severityCode) {

        message = message + " " + random.nextInt(1000);

        return logRecent(type, message, severityCode);
    }
}
