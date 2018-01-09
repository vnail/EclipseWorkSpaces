package test;

import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.cloud_note.dao.NoteDao;
import cn.tedu.cloud_note.dao.UserDao;
import cn.tedu.cloud_note.entity.Note;
import cn.tedu.cloud_note.entity.User;
import cn.tedu.cloud_note.service.NoteService;
import cn.tedu.cloud_note.service.UserServiceImpl;
import cn.tedu.cloud_note.util.NoteResult;

public class TestCase {
	
	public void test1() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("conf/spring-mybatis.xml");
		
		UserDao dao = ac.getBean("userDao",UserDao.class);
		
		User user=dao.findByName("demo");
		System.out.println(user);	
	}
	
	
	public void test2() {
		String[] conf= {"conf/spring-mvc.xml","conf/spring-mybatis.xml"};
		ApplicationContext ac= new ClassPathXmlApplicationContext(conf);
		
		UserServiceImpl usi=ac.getBean("userServiceImpl",UserServiceImpl.class);
		NoteResult<User> nr=usi.checkLogin("zhan", "111");
		System.out.println(nr);
	}
	
	
	public void test3() {
		String[] conf= {"/conf/spring-mvc.xml","/conf/spring-mybatis.xml"};
		ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
		
		NoteDao nb =ac.getBean("noteDao",NoteDao.class);
		List<Note> ln=nb.findByBookId("1db556b9-d1dc-4ed9-8274-45cf0afbe859");
		
		for(Note n:ln) {
			System.out.println(n);
		}
		
	}
	
	@Test
	public void  test4() {
		String[] conf= {"/conf/spring-mvc.xml","/conf/spring-mybatis.xml"};
		ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
		
		String BookId="2a4ca5bb-3073-4194-9d4e-5db0ec5c0998";
		NoteService noteService = ac.getBean("noteServiceImpl",NoteService.class);
		NoteResult<List<Note>> result=noteService.getNotesByBookId(BookId);
		
		System.out.println(result);
		
	}
}


