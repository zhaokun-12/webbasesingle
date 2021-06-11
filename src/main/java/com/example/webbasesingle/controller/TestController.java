package com.example.webbasesingle.controller;

import com.example.webbasesingle.bean.Person;
import com.example.webbasesingle.common.ResponseData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author zhaokun
 * @Date 2021/6/11
 * @Version 1.0
 **/
@RestController
@RequestMapping("/hello")
public class TestController<T> {
    @RequestMapping("hello1")
    public ResponseData hello1() {
//        return "" + 123;
        return null;
    }
    @RequestMapping("hello2")
    public ResponseData<T> hello2() {
        return null;
    }
    @RequestMapping("hello3")
    public ResponseData<?> hello3() {
        return null;
    }
    @RequestMapping("hello4")
    public ResponseData<Person> hello4() {
        return null;
    }
}
