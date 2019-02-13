package com.confucius.service;

import com.confucius.dao.IrisDao;
import com.confucius.pojo.IrisInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class IrisService {
    @Resource
    private IrisDao irisDao;

    public void putInfo(IrisInfo irisInfo){
        try {
            irisDao.putIrisInfo(irisInfo);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
