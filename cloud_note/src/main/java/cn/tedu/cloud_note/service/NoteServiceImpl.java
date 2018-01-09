package cn.tedu.cloud_note.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.cloud_note.dao.NoteDao;
import cn.tedu.cloud_note.entity.Note;
import cn.tedu.cloud_note.util.NoteResult;

@Service("noteServiceImpl")
public class NoteServiceImpl implements NoteService {
	@Resource
	NoteDao noteDao;

	public NoteResult<List<Note>> getNotesByBookId(String BookId) {
		NoteResult<List<Note>> result= new NoteResult<List<Note>>();
		// TODO Auto-generated method stub
		if(BookId==null) {
			result.setStatus(1);
			result.setMsg("illegal parameter ");
			return result;
		}
		
		List<Note> ln= noteDao.findByBookId(BookId);
		
		result.setStatus(0);
		result.setMsg("fetch notes successfully");
		result.setData(ln);
		
		
		for(Note n:ln) {
			System.out.println(n);
		}
		
		
		return result;
	}

}
