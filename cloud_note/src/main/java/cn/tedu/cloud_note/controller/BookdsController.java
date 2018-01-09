package cn.tedu.cloud_note.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloud_note.entity.NoteBook;
import cn.tedu.cloud_note.service.NoteBookService;
import cn.tedu.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/book")
public class BookdsController {
	@Resource
	NoteBookService noteBookService;
	
	@RequestMapping("/loadBookds.do")
	@ResponseBody
	public NoteResult<List<NoteBook>> loadBookds(String userId) {
		NoteResult<List<NoteBook>> result
		                  =new NoteResult<List<NoteBook>>();
		
		result=noteBookService.getUserNoteBook(userId);

		return result;
		
	}

}
