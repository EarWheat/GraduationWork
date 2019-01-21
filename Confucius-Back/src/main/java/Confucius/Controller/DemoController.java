package Confucius.Controller;

import Confucius.Pojo.Demo;
import Confucius.Service.DemoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/hello")
public class DemoController {

    @Resource
    private DemoService demoService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Demo getTestById(@RequestParam("id") String id) {
        return demoService.getById(Integer.parseInt(id));
    }

}
