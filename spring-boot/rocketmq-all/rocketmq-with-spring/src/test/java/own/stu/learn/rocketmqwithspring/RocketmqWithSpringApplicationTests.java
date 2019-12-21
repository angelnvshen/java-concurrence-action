package own.stu.learn.rocketmqwithspring;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RocketmqWithSpringApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Test
    public void test(){
        SendResult sendResult = rocketMQTemplate.syncSend("hello:world-tag", "very happy ....");
        System.out.println(sendResult);
    }
}
