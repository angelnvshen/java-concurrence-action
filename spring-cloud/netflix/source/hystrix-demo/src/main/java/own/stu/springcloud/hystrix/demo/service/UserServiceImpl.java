package com.netflix.hystrix.examples.own.service;

public class UserServiceImpl implements UserService {
    @Override
    public String getNameById(Integer id) {
        return "hello world";
    }
}
