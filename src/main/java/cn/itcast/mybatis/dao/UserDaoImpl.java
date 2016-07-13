package cn.itcast.mybatis.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import cn.itcast.mybatis.domain.User;

public class UserDaoImpl extends SqlSessionDaoSupport implements IUserDao {
    
    //删除
    public int deleteById(Integer id) {
        return this.getSqlSession().delete("cn.itcast.mybatis.domain.User.deleteOne",id);
    }

    //新增
    public int insert(User u) {
        return this.getSqlSession().insert("cn.itcast.mybatis.domain.User.create", u);
    }

    //列表
    public List<User> list() {
        return this.getSqlSession().selectList("cn.itcast.mybatis.domain.User.listAll");
    }

    //修改
    public int update(User u) {
        return this.getSqlSession().update("cn.itcast.mybatis.domain.User.update",u);
    }

    //获取对象
    public User get(Integer id) {
        return (User) this.getSqlSession().selectOne("cn.itcast.mybatis.domain.User.get", id);
    }

}