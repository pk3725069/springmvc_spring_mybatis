package com.dao.db;

public class DBContextHolder {
	/** 
     * �߳�threadlocal 
     */  
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>(); 

    public  final static String DB_TYPE_W = "dataSourceKeyW";  
    public  final static String DB_TYPE_R = "dataSourceKeyR";  
   //private String DB_TYPE_R = "dataSourceKeyR";  
    /**
     * ��ȡDb���ӳص�����
     * @return
     */
    public  static String getDbType() {  
        String db = contextHolder.get();  
        if (db == null) {  
            db = DB_TYPE_W;// Ĭ���Ƕ�д��  
        }  
        return db;  
    }  
    /** 
     *  
     * ���ñ��̵߳�dbtype 
     * @param str 
     * @see [�����/����](��ѡ) 
     * @since [��Ʒ/ģ��汾](��ѡ) 
     */  
    public  static void  setDbType(String str) {  
        contextHolder.set(str);  
    }  
    /** 
     * clearDBType 
     *
     * @Description: ������������ 
     */  
    public  static void clearDBType() {  
        contextHolder.remove();  
    } 
}
