package cn.tedu.cloud_note.dao;

import java.util.List;

import cn.tedu.cloud_note.entity.Note;

public interface NoteDao {
	public List<Note> findByBookId(String bookId);
}
