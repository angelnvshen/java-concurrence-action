package own.stu.learn.withSpringBoot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import own.stu.learn.withSpringBoot.websocket.WebSocketServer;

@Controller
public class DemoController {
    @ResponseBody
    @GetMapping("/index")
    public String index(String data) {
        if (StringUtils.isEmpty(data)) {
            data = "hello spring-cloud-study";
        }
        return data;
    }

    @GetMapping("/socket/{cid}")
    public ModelAndView sockethtml(@PathVariable String cid) {
        ModelAndView mav = new ModelAndView("socket");
        mav.addObject("cid", cid);
        return mav;
    }

    @ResponseBody
    @GetMapping("/socket/sendMessage")
    public String sendByHand(String message, String id) {

        WebSocketServer.sendInfo(message, id);

        return "SUCCESS";
    }
}