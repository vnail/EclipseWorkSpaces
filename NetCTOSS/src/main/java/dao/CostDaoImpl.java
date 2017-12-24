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
	    	//1����¼��־��log4j
	    	e.printStackTrace();
	    	//2.�׳��쳣
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

	/**��ȡ�����Ŀ�ݼ���ALT+Shif+M*/
	private Cost createCost(ResultSet rs) throws SQLException {
		Cost c = new Cost();
		//����
		 c.setCostId(rs.getInt("id"));;
		//�ʷ���
		 c.setName(rs.getString("name"));
		//����ʱ��   
    c.setBaseDuration(rs.getInt("base_duration"));
		//��������
		 c.setBaseCost(rs.getDouble("base_cost"));
		//��λ����
		 c.setUnitCost(rs.getDouble("unit_cost"));
		//״̬:0-��ͨ��1-����
		 c.setStatus(rs.getString("status"));
		//����
		 c.setDescr(rs.getString("descr"));
		//����ʱ��
		 c.setCreatetime(rs.getTimestamp("creatime"));
		//��ͨʱ��
		 c.setStarttime(rs.getTimestamp("startime"));
		//�ʷ�����1-���£�2-�ײͣ�3-��ʱ��
		
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
			 * 2,3,4�ֶ�ʹ��setObject ������ʹ��setInt()/setDouble()ԭ��
			 * 2,3,4�ֶο���Ϊnull,setInt()/setDouble()��֧��*/
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
			throw new RuntimeException("��ѯ�ʷ�ʧ��",e);
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
