package com.base.storage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.define.EnumCodeDefine;
import com.exception.xsgException;
import com.netease.cloud.ClientException;
import com.netease.cloud.ServiceException;
import com.netease.cloud.auth.BasicCredentials;
import com.netease.cloud.auth.Credentials;
import com.netease.cloud.services.nos.NosClient;
import com.netease.cloud.services.nos.model.PutObjectRequest;
import com.netease.cloud.services.nos.model.PutObjectResult;

/**
 * 图片存储
 * 调用NOS网易云对象存储API实现图片的存储
 * @author hzlinghongshun
 *
 */
public class baseStorage {

	private static final  String accessKey = "a499c0e70a734f49889035ffd51072d9";
	private static final String secretKey = "796c0a2d020443508a55a6ecdf4a742b";
	private static final  String bucketName  = "mini-xsg1";
	private  static Credentials credentials = null;
	private static NosClient nosClient  = null;
	
	private static final Logger log = LoggerFactory.getLogger(baseStorage.class);
	//创建NosClient
	public static NosClient getNosClientInstance()
	{
		
		if(nosClient==null)
		{
			credentials = new BasicCredentials(accessKey, secretKey);
			
			nosClient = new NosClient(credentials);
			log.info("create nosClient"+ nosClient.toString());
		}
		return nosClient; 
	}
	
	public static String pushFileToNOS(Integer imageType,String objectName,File file) throws xsgException
	{
		//图片目录
		String storageSrc = null;
		//图片保存完整url地址
		String returnUrl = null;
		
		if(imageType.equals(EnumCodeDefine.IMAGE_TYPE_USER))
		{
			//图片目录
			storageSrc = EnumCodeDefine.IMAGE_TYPE_USER_SRC+'/'+objectName;
		}
		else if(imageType.equals(EnumCodeDefine.IMAGE_TYPE_PRODUCT))
		{
			//图片目录
			storageSrc = EnumCodeDefine.IMAGE_TYPE_PRODUCT_SRC+'/'+objectName;	
		}
		else
		{
			//图片不在定义的类型内抛出异常
			log.info("imageType is wrong : "+imageType);
			throw new xsgException("imageType is wrong : "+imageType);
		}
		try{
			log.info("pushFileToNOS imageType: "+imageType+",storageSrc:"+ storageSrc+",file:"+file.toString());
			//推送图片至服务器
			PutObjectResult result = getNosClientInstance().putObject(new PutObjectRequest(bucketName, storageSrc, file));
			log.debug(result.getETag());
			//图片保存完整url地址
			returnUrl = EnumCodeDefine.STORAGE_ROOT_URL+'/'+storageSrc;
		}
		catch (ServiceException ase) {
			log.info("ServiceException.some errors occur in server point.");
			log.info("Error Message:    " + ase.getMessage());
	    } catch (ClientException ace) {
	    	log.info("ClientException.some errors occur in client point");
	    	log.info("Error Message: " + ace.getMessage());
	    }
		log.info(returnUrl);
		return returnUrl;
	}
	
	public static InputStream getFileFromNOS(String imageUrl)  throws xsgException
	{
		InputStream inputStream = null;
		if(imageUrl.indexOf(EnumCodeDefine.STORAGE_ROOT_URL)==-1)
		{
			//传入路径不包含EnumCodeDefine.STORAGE_ROOT_URL 抛出错误
			log.info("imageUrl is wrong : "+imageUrl);
			throw new xsgException("imageUrl is wrong : "+imageUrl);
		}
		try {
			URL url = new URL(imageUrl);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			// 设置连接网络的超时时间
			httpURLConnection.setConnectTimeout(3000);
			httpURLConnection.setDoInput(true);
			httpURLConnection.setRequestMethod("GET");

			int responseCode = httpURLConnection.getResponseCode();
			if (responseCode == 200) {
				inputStream = httpURLConnection.getInputStream();

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return inputStream;
	}
	public static void deleteFileFromNOS(String imageUrl)
	{
		if(imageUrl.indexOf(EnumCodeDefine.STORAGE_ROOT_URL)==-1)
		{
			//传入路径不包含EnumCodeDefine.STORAGE_ROOT_URL 抛出错误
			log.info("imageUrl is wrong : "+imageUrl);
			throw new xsgException("imageUrl is wrong : "+imageUrl);
		}
		//图片目录
		String storageSrc =  imageUrl.split(EnumCodeDefine.STORAGE_ROOT_URL)[1];
		nosClient.deleteObject(bucketName, storageSrc);
	}
	
	public static void main(String[] args) 
	{
		File file = new File("C:/123.jpg");
		String url = pushFileToNOS(1,"test6.jpg", file);
		try {
			Thread.sleep(1000);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try{
			InputStream NOSObjectInput = getFileFromNOS(url);	
			FileOutputStream fos = new FileOutputStream("c:/b.jpg");
			byte[] b = new byte[1024];
			int len = 0;
			while ((len = NOSObjectInput.read(b)) != -1) {
				fos.write(b, 0, len);

			}
			NOSObjectInput.close();
			fos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//deleteFileFromNOS(url);
		 url = pushFileToNOS(4,"test4.jpg", file);
		
	}
}
