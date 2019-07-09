package own.stu.springboot.helloworld.controller;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    @Autowired
    private Environment environment;

    @Autowired
    private Person person;

    @RequestMapping("hello")
    public String hello() {

        System.out.println("hello : " + environment.getProperty("hello"));
        System.out.println(environment.getProperty("pets", Person.class));
        System.out.println(environment.getProperty("pets2", Person.class));
        System.out.println(environment.getProperty("pets2"));

        System.out.println("====");
        System.out.println(person);
        return "hello world";
    }

    @Component
    @ConfigurationProperties(prefix = "friend")
    @Data
    public static class Person {
        private Integer age;

        private String name;
    }
}
