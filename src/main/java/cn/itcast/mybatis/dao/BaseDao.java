package cn.itcast.mybatis.dao;

import java.util.Date;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import cn.itcast.mybatis.domain.User;

import com.dao.dynamicDS.DBContextHolder;
/**
 * proxy 
 * @author hzlinghongshun
 *
 */
public class BaseDao extends SqlSessionDaoSupport {

	//新增
    public int insert(String statement,Object parameter) {
    	System.out.println("insert before :"+new Date());
        return  this.getSqlSession().insert(statement, parameter);
    }
	//新增
    public int delete(String statement,Object parameter) {
    	System.out.println("insert before :"+new Date());
        return  this.getSqlSession().delete(statement, parameter);
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
    public User get(String statement,Object parameter) {
        return this.getSqlSession().selectOne(statement, parameter); 
    }
    
    
}
