package com.confucius.service;

import com.confucius.Dao.DemoDao;
import com.confucius.pojo.UserInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DemoService {
    @Resource
    private DemoDao demoDao;

    public UserInfo getInfo(String username){
        return demoDao.getInfo(username);
    }
}
