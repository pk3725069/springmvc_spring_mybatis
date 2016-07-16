package cn.itcast.mybatis.dao;

import java.util.List;

import com.dao.dynamicDS.DBContextHolder;
import com.dao.dynamicDS.DataSource;

import cn.itcast.mybatis.domain.User;


public interface IUserDao {
    public List<User> list();
    @DataSource(DBContextHolder.DB_TYPE_W)
    public User get(Integer id);
    public int insert(User u);
    public int update(User u);
    public int deleteById(Integer id);
}