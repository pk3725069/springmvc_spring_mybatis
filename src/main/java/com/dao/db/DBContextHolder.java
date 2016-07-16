package com.dao.db;

public class DBContextHolder {
	/** 
     * 线程threadlocal 
     */  
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>(); 

    public  final static String DB_TYPE_W = "dataSourceKeyW";  
    public  final static String DB_TYPE_R = "dataSourceKeyR";  
   //private String DB_TYPE_R = "dataSourceKeyR";  
    /**
     * 获取Db连接池的类型
     * @return
     */
    public  static String getDbType() {  
        String db = contextHolder.get();  
        if (db == null) {  
            db = DB_TYPE_W;// 默认是读写库  
        }  
        return db;  
    }  
    /** 
     *  
     * 设置本线程的dbtype 
     * @param str 
     * @see [相关类/方法](可选) 
     * @since [产品/模块版本](可选) 
     */  
    public  static void  setDbType(String str) {  
        contextHolder.set(str);  
    }  
    /** 
     * clearDBType 
     *
     * @Description: 清理连接类型 
     */  
    public  static void clearDBType() {  
        contextHolder.remove();  
    } 
}
