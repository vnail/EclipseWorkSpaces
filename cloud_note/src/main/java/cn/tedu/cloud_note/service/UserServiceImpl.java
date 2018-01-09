package cn.tedu.cloud_note.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.cloud_note.dao.UserDao;
import cn.tedu.cloud_note.entity.User;
import cn.tedu.cloud_note.util.NoteResult;
import cn.tedu.cloud_note.util.NoteUtil;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {
	@Resource
	private UserDao dao;
	
	public NoteResult<User> checkLogin(String name, String password) {
		// TODO Auto-generated method stub
		NoteResult<User> result = new NoteResult<User>();
		User user=dao.findByName(name);
		System.out.println(user);
		
		if(user==null) {
			result.setStatus(1);
			result.setMsg("用户名不存在");
			return result; 
		}
//		if(!password.equals(user.getCn_user_password())) {
//			result.setStatus(2);
//			result.setMsg("密码错误");	
//			return result;
//		}
		//登录成功
		result.setStatus(0);
		result.setMsg("登录成功");
		result.setData(user);
		
		return result;
	}
		
	public NoteResult<User>  saveUser(User user) {
		NoteResult<User> result = new NoteResult<User>();
		//检查用户
		User user2 = dao.findByName(user.getCn_user_name());
		if(user2!=null) {
			System.out.println("用户名已被使用，无法注册");
			result.setStatus(1);
			result.setMsg("用户名已被使用，无法注册");
			return result;
		}
		//添加用户
		String userId=NoteUtil.createId();
		String userPassword = NoteUtil.md5(user.getCn_user_password());
		
		user.setCn_user_id(userId);
		user.setCn_user_password(userPassword);
		dao.save(user);
		
		result.setStatus(0);
		result.setMsg("注册成功");
		
		return result;
	}
}
