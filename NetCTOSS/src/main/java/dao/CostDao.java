package dao;

import java.util.List;

import entity.Cost;

public interface CostDao {
	public List<Cost> findAll();
	
	public void save(Cost c);
	
	public Cost findById(int id);
	public void saveUpdate(Cost c);

}
