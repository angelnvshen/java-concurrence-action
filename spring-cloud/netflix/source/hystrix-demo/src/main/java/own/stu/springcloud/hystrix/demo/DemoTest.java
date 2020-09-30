package com.netflix.hystrix.examples.own;

import com.netflix.hystrix.examples.own.command.UserServiceGetNameByIdCommand;
import com.netflix.hystrix.examples.own.service.UserService;
import com.netflix.hystrix.examples.own.service.UserServiceImpl;

public class DemoTest {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
 
        UserServiceGetNameByIdCommand command = new UserServiceGetNameByIdCommand(userService);
        command.setUserId(1);
        String userName = command.execute();
        System.out.println(userName);
    }
}
