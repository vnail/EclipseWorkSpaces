package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import entity.Cost;
import util.DBUtil2;

public class CostDaoImpl implements Serializable, CostDao {

	public List<Cost> findAll() {
	    Connection con = null;
	    Statement stmt = null;
	    ResultSet rs = null;
	    try {
	    con = DBUtil2.getConnection();
	    
	    stmt = con.createStatement();
	    String sql = "select * from cost order by id";
	    rs = stmt.executeQuery(sql);
	    
	    List<Cost> list = new ArrayList<Cost>();
	    while(rs.next()) {
	    	Cost c = createCost(rs);
	    	
	    	 list.add(c);
	    }
	    return list;
	    }catch(SQLException e) {
	    	//1。记录日志：log4j
	    	e.printStackTrace();
	    	//2.抛出异常
	    	throw new RuntimeException("findAll fail",e);
	    }finally {
	    	try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	
	}

	/**提取方法的快捷键：ALT+Shif+M*/
	private Cost createCost(ResultSet rs) throws SQLException {
		Cost c = new Cost();
		//主键
		 c.setCostId(rs.getInt("id"));;
		//资费名
		 c.setName(rs.getString("name"));
		//基本时长   
    c.setBaseDuration(rs.getInt("base_duration"));
		//基本费用
		 c.setBaseCost(rs.getDouble("base_cost"));
		//单位费用
		 c.setUnitCost(rs.getDouble("unit_cost"));
		//状态:0-开通；1-禁用
		 c.setStatus(rs.getString("status"));
		//描述
		 c.setDescr(rs.getString("descr"));
		//创建时间
		 c.setCreatetime(rs.getTimestamp("creatime"));
		//开通时间
		 c.setStarttime(rs.getTimestamp("startime"));
		//资费类型1-包月；2-套餐；3-计时；
		
		 c.setCostType(rs.getString("cost_type"));
		return c;
	}
	
	public void save(Cost c) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DBUtil2.getConnection();
			String sql="insert into cost values("+
			            "cost_seq.nextval,"+
					     "?,?,?,?,1,?,sysdate,null,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, c.getName());
			/**
			 * 2,3,4字段使用setObject 而不是使用setInt()/setDouble()原因：
			 * 2,3,4字段可以为null,setInt()/setDouble()不支持*/
			ps.setObject(2, c.getBaseDuration());
			ps.setObject(3, c.getBaseCost());
			ps.setObject(4,c.getUnitCost());
			ps.setString(5, c.getDescr());
			ps.setString(6, c.getCostType());
			ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			try {
				DBUtil2.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Cost findById(int id) {
		Connection con =null;
		PreparedStatement ps = null;
		ResultSet rs =null;
		try {
			 con = DBUtil2.getConnection();
			 
			 String sql="select * from cost where id=?";
			 
			 ps = con.prepareStatement(sql);
			 ps.setInt(1,id);
			 rs = ps.executeQuery();
			 if(rs.next()) {
				 return createCost(rs);
			 }
			 
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询资费失败",e);
		}finally{
			try {
				DBUtil2.closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

}
