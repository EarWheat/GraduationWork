package com.confucius.dao;

import com.confucius.mapper.IrisInfoMapper;
import com.confucius.pojo.IrisInfo;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class IrisDao {

    @Resource
    private IrisInfoMapper irisInfoMapper;

    public void putIrisInfo(IrisInfo irisInfo){
        irisInfoMapper.insertSelective(irisInfo);
    }
}
