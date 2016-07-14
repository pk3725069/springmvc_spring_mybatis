package cn.itcast.mybatis.dao;

import cn.itcast.mybatis.domain.User;

public class UserDaoImpl extends BaseDao implements IUserDao {
    
    //删除
    public int deleteById(Integer id) {
        return this.delete("cn.itcast.mybatis.domain.User.deleteOne",id);
    }

    //新增
    public int insert(User u) {
        return this.insert("cn.itcast.mybatis.domain.User.create", u);
    }

    //列表
/*    public List<User> list() {
        return this.selectList("cn.itcast.mybatis.domain.User.listAll");
    }

    //修改
    public int update(User u) {
        return this.update("cn.itcast.mybatis.domain.User.update",u);
    }*/

    //获取对象
    public User get(Integer id) {
        return (User) this.get("cn.itcast.mybatis.domain.User.get", id);
    }

}