package cn.itcast.mybatis.service;

import java.util.List;

import cn.itcast.mybatis.domain.User;


public interface IUserService {
    public List<User> list();
    public User get(Integer id);
    public int insert(User u);
    public int update(User u);
    public int deleteById(Integer id);
}