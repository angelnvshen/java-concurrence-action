package own.stu.highConcurrence.cacheconsistence_bak.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping("/")
    public String on() {
        return "UP";
    }
}
