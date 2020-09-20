package own.stu.highConcurrence.nettyClient.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import own.stu.highConcurrence.nettyClient.Client;

@RestController
public class ClientController {

    @Autowired
    private Client client;

    @RequestMapping("/send")
    public String sendMessage(@RequestParam(value = "msg", defaultValue = "hello") String msg) {
        client.sendData(msg);
        return "UP";
    }
}
