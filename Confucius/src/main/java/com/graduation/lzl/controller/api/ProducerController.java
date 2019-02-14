package com.graduation.lzl.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.graduation.lzl.Constant.Kafka;
import com.graduation.lzl.pojo.HttpResult;
import com.graduation.lzl.service.ApiService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liuzhaolu
 * @version create_time：2018/12/15 类说明:
 */
@RestController
@EnableAutoConfiguration
public class ProducerController {

    public static Logger logger = LoggerFactory.getLogger(ProducerController.class);

    @Value("${TOPIC}")
    private static String topic;


    @Value("${KEY}")
    private String key;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Resource
    private ApiService apiService;

    //Kafka发送消息
    /**
     * @Author liuzhaolu
     * sendMsg
     * @param params
     * @return
     * 下午6:09 2018/12/16
     */
    @RequestMapping("/sendMsg")
    private JSONObject sendMsg(@RequestBody JSONObject params){
        logger.info("topic:{}||key:{}||message:{}",Kafka.topic,Kafka.key,params.toJSONString());
        kafkaTemplate.send(Kafka.topic, Kafka.key, params.toJSONString());
        return params;
    }

    //Kafka接受消息
    @KafkaListener(id = "key", topics = "graduationCallBack")
    public void callBack(ConsumerRecord<?, ?> cr) throws Exception {
        logger.info("graduationCallBack");
        logger.info("{} - {} : {}", cr.topic(), cr.key(), cr.value());
        logger.info(cr.topic()+"============="+cr.toString()+"================"+cr.key()+"==============="+cr.value());
        Map<String, Object> map = new HashMap<>();
        map.put("Test Data",cr.value().toString());
        HttpResult response = null;
        try {
            response = apiService.doPost(Kafka.url,map);
            JSONObject object = JSONObject.parseObject(response.getData());
            if (object.getString("errno").equals("0")) {
                logger.info("{}", object);
            } else {
                logger.error("http response error||"+object.get("data"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("http response error");
        }
    }

}
