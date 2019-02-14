package com.graduation.lzl.controller;

import com.graduation.lzl.service.ApiService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class HttpClientController {

    @Resource
    private ApiService apiService;

    @RequestMapping("http")
    public String test() throws Exception {
        String str = apiService.doGet("http://www.baidu.com");
        System.out.println(str);
        return "hello";
    }
}