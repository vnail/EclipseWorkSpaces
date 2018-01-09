package cn.tedu.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloud_note.entity.User;
import cn.tedu.cloud_note.service.UserServiceImpl;
import cn.tedu.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/user")
public class UserLoginController {
	@Resource
	UserServiceImpl userServiceImpl;
	
	@RequestMapping("/login.do")
	@ResponseBody
	public NoteResult<User> VerifyLogin(String name,String password) {
		System.out.println(name+"---"+password);
		NoteResult<User> result=userServiceImpl.checkLogin(name, password);	
		System.out.println(result);
		return result;	
	}
	
	@RequestMapping("/add.do")
	@ResponseBody
	public NoteResult<User> userRegister(String name,String nick,String password) {
		System.out.println(name+"==="+nick+"==="+password);
		User user = new User();
		user.setCn_user_name(name);
		user.setCn_user_nick(nick);
		user.setCn_user_password(password);
		
		NoteResult<User> result= userServiceImpl.saveUser(user);
		
		return result;
	
	}
}
