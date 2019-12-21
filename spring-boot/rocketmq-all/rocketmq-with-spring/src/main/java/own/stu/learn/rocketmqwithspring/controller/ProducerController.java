package own.stu.learn.rocketmqwithspring.controller;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProducerController {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Value("${common.topic}")
    private String topic;

    @ResponseBody
    @RequestMapping("/send")
    public String sendMessage(@RequestParam(required = false, defaultValue = "world-tag") String tag, String messsage) {

        rocketMQTemplate.asyncSend(topic + ":" + tag, messsage, new SendCallback() {

            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println(sendResult);
            }

            @Override
            public void onException(Throwable e) {
                e.printStackTrace();
            }
        });

        return "SUCCESS";
    }
}
