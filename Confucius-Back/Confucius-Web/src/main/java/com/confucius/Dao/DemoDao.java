package com.confucius.Dao;

import com.confucius.mapper.UserInfoMapper;
import com.confucius.pojo.UserInfo;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class DemoDao {
    @Resource
    private UserInfoMapper mapper;

    public UserInfo getInfo(String username){
        Example example = new Example(UserInfo.class);
        Example.Criteria createCriteria = example.createCriteria();
        createCriteria.andEqualTo("username",username);
        example.setOrderByClause("create_time Desc");//设置排序;
        List<UserInfo> items = mapper.selectByExample(example);
        return items.get(0);
    }
}
