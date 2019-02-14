package com.graduation.lzl.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuzhaolu
 * @version create_time：2018/12/15 类说明:
 */
@RestController
@EnableAutoConfiguration
public class HelloController {

    @RequestMapping("/hello")
    public String index(){
        return "this is liuzhaolu";
    }
}
