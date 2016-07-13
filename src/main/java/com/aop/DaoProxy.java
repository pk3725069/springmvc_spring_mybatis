package com.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DaoProxy implements InvocationHandler {
	
	//单例实现创建日志
	Log log = LogFactory.getLog(this .getClass()); 
	
	private Object targetObject;
	public Object createProxyIntance(Object targetObject){
	  this.targetObject = targetObject;
	  return Proxy.newProxyInstance(this.targetObject.getClass().getClassLoader(), 
	    this.targetObject.getClass().getInterfaces(), this);
	}
	 
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		 
		  Object obj = null;
		  long startTime=System.currentTimeMillis();
		  log.info("invoke before");
		  obj = method.invoke(this.targetObject, args);
		  
		  log.info("invoke after cost time:"+(System.currentTimeMillis()-startTime));
		 
		  return obj;
		 }

}
