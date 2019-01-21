package Confucius.Service.ServiceImpl;

import Confucius.Dao.DemoDao;
import Confucius.Pojo.Demo;
import Confucius.Service.DemoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DemoServiceImpl implements DemoService {

    @Resource
    private DemoDao demoDao;

    public Demo getById(int id){
        return demoDao.getById(id);
    }
}
