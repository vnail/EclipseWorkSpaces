package cn.tedu.cloud_note.service;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.cloud_note.dao.NoteBookDao;
import cn.tedu.cloud_note.entity.NoteBook;
import cn.tedu.cloud_note.util.NoteResult;

@Service
public class NoteBookServiceImpl implements NoteBookService, Serializable {
    @Resource
	NoteBookDao noteBookDao;
    
	public NoteResult<List<NoteBook>> getUserNoteBook(String userId) {
		NoteResult<List<NoteBook>> result=new NoteResult<List<NoteBook>>();
		
		// TODO Auto-generated method stub
		if(userId==null) {
			System.out.println("userId为空");
			result.setStatus(1);
			result.setMsg("userId为空");
			
			return result;
		}
		
		List<NoteBook> noteBooks=noteBookDao.findByUserId(userId);

		result.setStatus(0);
		result.setMsg("查询成功");
		result.setData(noteBooks);
		
		return result;
	}

}
