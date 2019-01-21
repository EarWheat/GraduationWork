package Confucius.controller;

import Confucius.service.DemoService;
import org.apache.ibatis.io.ResolverUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hello")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public ResolverUtil.Test getTestById(@RequestParam("id") String id) {
        return demoService.getById(Integer.parseInt(id));
    }

}
