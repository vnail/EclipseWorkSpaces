package cn.tedu.cloud_note.service;

import java.util.List;

import cn.tedu.cloud_note.entity.Note;
import cn.tedu.cloud_note.util.NoteResult;

public interface NoteService {
	public NoteResult<List<Note>> getNotesByBookId(String BookId);

}
