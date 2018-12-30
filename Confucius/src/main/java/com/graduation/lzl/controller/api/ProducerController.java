package com.graduation.lzl.controller.api;

import com.alibaba.fastjson.JSONObject;
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
        logger.info("topic:{}||key:{}||message:{}",topic,key,params.toJSONString());
        kafkaTemplate.send(topic, key, params.toJSONString());
        return params;
    }

    //Kafka接受消息
    @KafkaListener(id = "t1", topics = "graduationCallBack")
    public void callBack(ConsumerRecord<?, ?> cr) throws Exception {
        logger.info("{} - {} : {}", cr.topic(), cr.key(), cr.value());
    }

}
