package cn.tedu.cloud_note.service;

import java.util.List;

import cn.tedu.cloud_note.entity.NoteBook;
import cn.tedu.cloud_note.util.NoteResult;

public interface NoteBookService {
	public NoteResult<List<NoteBook>> getUserNoteBook(String userId);

}
