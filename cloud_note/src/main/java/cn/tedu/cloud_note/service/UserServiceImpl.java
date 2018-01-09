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
			result.setMsg("�û���������");
			return result; 
		}
//		if(!password.equals(user.getCn_user_password())) {
//			result.setStatus(2);
//			result.setMsg("�������");	
//			return result;
//		}
		//��¼�ɹ�
		result.setStatus(0);
		result.setMsg("��¼�ɹ�");
		result.setData(user);
		
		return result;
	}
		
	public NoteResult<User>  saveUser(User user) {
		NoteResult<User> result = new NoteResult<User>();
		//����û�
		User user2 = dao.findByName(user.getCn_user_name());
		if(user2!=null) {
			System.out.println("�û����ѱ�ʹ�ã��޷�ע��");
			result.setStatus(1);
			result.setMsg("�û����ѱ�ʹ�ã��޷�ע��");
			return result;
		}
		//����û�
		String userId=NoteUtil.createId();
		String userPassword = NoteUtil.md5(user.getCn_user_password());
		
		user.setCn_user_id(userId);
		user.setCn_user_password(userPassword);
		dao.save(user);
		
		result.setStatus(0);
		result.setMsg("ע��ɹ�");
		
		return result;
	}
}
