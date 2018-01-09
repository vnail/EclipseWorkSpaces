package cn.tedu.cloud_note.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloud_note.entity.Note;
import cn.tedu.cloud_note.service.NoteService;
import cn.tedu.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/note")
public class LoadNoteController {
	@Resource
	NoteService noteService;
	
	@RequestMapping("/loadnotes.do")
	@ResponseBody
	public NoteResult<List<Note>> loadNotes(String bookId){
		NoteResult<List<Note>> result = new  NoteResult<List<Note>>();
		
		System.out.println("Get Request:/loadnotes.do"+bookId);
		
		result=noteService.getNotesByBookId(bookId);
		
		return result;		
	}
}
