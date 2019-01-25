package com.confucius.controller;

import com.confucius.common.bean.JsonResult;
import com.confucius.pojo.UserInfo;
import com.confucius.service.DemoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RequestMapping("demo")
@Controller
public class DemoController {

    @Resource
    private DemoService service;

    @ResponseBody
    @RequestMapping(value = "getInfo", method = RequestMethod.POST)
    public JsonResult getInfo(HttpServletRequest request) {
        try {
            UserInfo userInfo = service.getInfo("liuzhaolu");
            return JsonResult.ok(userInfo);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return JsonResult.build(1, "repairCallback update error");
    }

    @ResponseBody
    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public JsonResult repairCallback(HttpServletRequest request) {
        try {

            return JsonResult.build(0, "hello world");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JsonResult.build(1, "repairCallback update error");
    }
}
