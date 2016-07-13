package cn.itcast.mybatis.service;

import java.util.List;

import com.aop.DaoProxy;

import cn.itcast.mybatis.dao.IUserDao;
import cn.itcast.mybatis.domain.User;

public class UserServiceDaoImpl implements IUserService {

    private IUserDao userDao;
    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    public int deleteById(Integer id) {
    	DaoProxy pro = new DaoProxy();
    	UserServiceDaoImpl daoObject = new UserServiceDaoImpl();
    	IUserService us = (IUserService)pro.createProxyIntance(daoObject);
        int i = us.deleteById(id);
               

        return i;
    }

    public User get(Integer id) {
    	DaoProxy pro = new DaoProxy();

    	IUserDao ud = (IUserDao)pro.createProxyIntance(userDao);
    	
        return ud.get(id);
    }

    public int insert(User u) {
        return userDao.insert(u);
    }

    public List<User> list() {
        return userDao.list();
    }

    public int update(User u) {
        return userDao.update(u);
    }

}