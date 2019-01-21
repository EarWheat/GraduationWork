package Confucius.Dao;

import Confucius.Pojo.Demo;
import org.springframework.stereotype.Repository;


@Repository
public interface DemoDao {
    Demo getById(Integer id);
}
