package test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.mybatis.domain.User;
import cn.itcast.mybatis.service.IUserService;

public class TestSpringMybatis {

    @Test
    public void test(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-beans.xml");
        IUserService us = (IUserService) ac.getBean("userService");
        List<User> users = us.list();
        System.out.println(users.size());
    }
    
    @Test
    public void testDelete(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-beans.xml");
        IUserService us = (IUserService) ac.getBean("userService");
        us.deleteById(123);
    }
    @Test
    public void testCreate(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-beans.xml");
        IUserService us = (IUserService) ac.getBean("userService");
        User user = new User();
        user.setId(12356);
        user.setAddress("≤‚ ‘");
        user.setAge(27);
        user.setName("¡Ë√˙");
        us.insert(user);
    }
    
}