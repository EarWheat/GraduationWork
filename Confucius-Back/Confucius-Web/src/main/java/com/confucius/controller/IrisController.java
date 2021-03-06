package com.confucius.controller;

import com.confucius.common.bean.JsonResult;
import com.confucius.pojo.IrisInfo;
import com.confucius.pojo.UserInfo;
import com.confucius.requestJson.IrisRequest;
import com.confucius.service.IrisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RequestMapping("Iris")
@Controller
public class IrisController {

    private static final Logger logger = LoggerFactory.getLogger(IrisController.class);
    @Resource
    private IrisService irisService;

    @ResponseBody
    @RequestMapping(value = "putIrisInfo", method = RequestMethod.POST)
    public JsonResult putIris(HttpServletRequest request, HttpServletResponse response) {
        try {
//            irisService.putInfo(irisRequest);
//            logger.info(irisRequest.getKey());
//            logger.info("=====================");
//            logger.info(irisRequest.getValue());
//            System.out.println(irisRequest.getKey());
//            System.out.println("=================");
//            System.out.println(irisRequest.getValue());
            System.out.println("==============================");
            System.out.println(request);
            Map<String, String[]> params = request.getParameterMap();
            for(String key: params.keySet()){
                System.out.println(key+"======================"+params.get(key));
            }
            Object object = params.get("Test Data");
            System.out.println(object.toString());
            return JsonResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JsonResult.build(1, "putIris error");
    }
}
