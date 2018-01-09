package cn.tedu.cloud_note.dao;

import java.util.List;

import cn.tedu.cloud_note.entity.NoteBook;

public interface NoteBookDao {
	public List<NoteBook> findByUserId(String userId);
}
