package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

/**
 * ʹ�����ӳط������ݿ�
 * 1.��ȡProperties
 * 2.��ʼ��BasicDataBase
 * 3.��ʼ��ThreadLocal
 * 4.ʵ��getConnection��������ȡConnection֮�󣬽���ȡ��Connection���뵽ThreadLocal��Map��
 * 5.ʵ��closeConnection������ʹ��ThreadLocal��ȡ��ǰ�̶߳�Ӧ��Connection,�ر�*/

public class DBUtil2 {
	//���ݿ����ӳ�
	private static BasicDataSource ds ;
	//Ϊ��ͬ�̹߳�������
	private  static ThreadLocal<Connection> tl;
	
	private static String driver;
	private static String url;
	private static String user;
	private static String password;
	private static String initsize;
	private static String maxactive;
	private static String maxwait;
	
	/**
	 * ����һ�������࣬��ʹ�ù��캯������ʼ��������ʹ��static������ʼ����
	 * ������Ϊ�������಻��Ҫʵ����*/
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
			
			//��ʼ�����ӳ�
			ds = new BasicDataSource();
			
			//��������Class.forName()
			ds.setDriverClassName(driver);
		    ds.setUrl(url);
		    ds.setUsername(user);
		    ds.setPassword(password);
		    //��ʼ������
		    ds.setInitialSize(Integer.parseInt(initsize));
		    //���������
		    ds.setMaxActive(Integer.parseInt(maxactive));
		    //���ȴ�ʱ��
		    ds.setMaxWait(Long.parseLong(maxwait));
		    //����������
		    ds.setMaxIdle(Integer.parseInt(prop.getProperty("maxidle")));
		    //��С�������ӡ�
		    ds.setMinIdle(Integer.parseInt(prop.getProperty("minidle")));
		    
		    //����ThreadLocalʵ��
		    tl=new ThreadLocal<Connection>();	    
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static  Connection getConnection() throws SQLException{
		System.out.println("��ȡ������...");
		Connection conn = ds.getConnection();
		tl.set(conn);
		
		return conn;	
	}
	
	public static void closeConnection() throws SQLException{
		Connection conn=tl.get();
		if(conn!=null){
			/**
			 * BasicDataSource��close������û�������ر����ӣ����ǰ����ӻ��������ӳ�*/
			conn.close();
		}
	}
}
