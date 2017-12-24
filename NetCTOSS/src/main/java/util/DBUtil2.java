package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

/**
 * 使用连接池访问数据库
 * 1.获取Properties
 * 2.初始化BasicDataBase
 * 3.初始化ThreadLocal
 * 4.实现getConnection方法，获取Connection之后，将获取的Connection存入到ThreadLocal的Map中
 * 5.实现closeConnection方法，使用ThreadLocal获取当前线程对应的Connection,关闭*/

public class DBUtil2 {
	//数据库连接池
	private static BasicDataSource ds ;
	//为不同线程管理连接
	private  static ThreadLocal<Connection> tl;
	
	private static String driver;
	private static String url;
	private static String user;
	private static String password;
	private static String initsize;
	private static String maxactive;
	private static String maxwait;
	
	/**
	 * 这是一个工具类，不使用构造函数来初始化，而是使用static块来初始化，
	 * 这是因为，工具类不需要实例化*/
	static{
		try{
			Properties prop = new Properties();
			String path="config.properties";
			InputStream is = DBUtil2.class.getClassLoader().getResourceAsStream(path);
			//FileInputStream fis = new FileInputStream(new File("src"+File.separator+"main"+File.separator+"resources"+File.separator+"config.properties"));
			System.out.println(is);
			prop.load(is);
			is.close();
			
			driver = prop.getProperty("driver");
		    url = prop.getProperty("url");
			user = prop.getProperty("user");
			password = prop.getProperty("password");
			initsize = prop.getProperty("initsize");
			maxactive = prop.getProperty("maxactive");
			maxwait = prop.getProperty("maxwait");
			
			System.out.println(url);
			
			//初始化连接池
			ds = new BasicDataSource();
			
			//设置驱动Class.forName()
			ds.setDriverClassName(driver);
		    ds.setUrl(url);
		    ds.setUsername(user);
		    ds.setPassword(password);
		    //初始连接数
		    ds.setInitialSize(Integer.parseInt(initsize));
		    //最大连接数
		    ds.setMaxActive(Integer.parseInt(maxactive));
		    //最大等待时间
		    ds.setMaxWait(Long.parseLong(maxwait));
		    //最大空闲连接
		    ds.setMaxIdle(Integer.parseInt(prop.getProperty("maxidle")));
		    //最小空闲连接、
		    ds.setMinIdle(Integer.parseInt(prop.getProperty("minidle")));
		    
		    //创建ThreadLocal实例
		    tl=new ThreadLocal<Connection>();	    
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static  Connection getConnection() throws SQLException{
		System.out.println("获取连接中...");
		Connection conn = ds.getConnection();
		tl.set(conn);
		
		return conn;	
	}
	
	public static void closeConnection() throws SQLException{
		Connection conn=tl.get();
		if(conn!=null){
			/**
			 * BasicDataSource的close方法并没有真正关闭连接，而是把连接还给了连接池*/
			conn.close();
		}
	}
}
