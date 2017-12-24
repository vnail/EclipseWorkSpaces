package test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.Test;

import dao.CostDaoImpl;
import entity.Cost;
import util.DBUtil2;

public class TestDBUtil2 {
	
	
	public void testDBUtil2() throws SQLException {
		
		Connection con = DBUtil2.getConnection();
		
		Statement stmt = con.createStatement();
		String sql = "select empno,ename,sal from emp";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			System.out.println(rs.getInt("empno")+"---"+rs.getString("ename")+"---"+rs.getDouble("sal"));
		}
		
	}
	
	
	public void testCostDaoImpl() {
		CostDaoImpl cdi = new CostDaoImpl();
		List<Cost> list = cdi.findAll();
		
		for(Cost c: list) {
			System.out.println(c.getName());
		}
	}
	
	public void testsave() {
		CostDaoImpl cdi = new CostDaoImpl();
		
		Cost c = new Cost();
		c.setName("包月");
		c.setBaseDuration(600);
		c.setBaseCost(100.0);
		c.setUnitCost(0.6);
		c.setDescr("包月什么的最划算了");
		c.setCostType("1");
		cdi.save(c);
		
		List<Cost> list = cdi.findAll();
		
		for(Cost l: list) {
			System.out.println(c.getName());
		}
	}
	
	@Test
	public void testFindbyId() {
		CostDaoImpl cdi = new CostDaoImpl();
		Cost c=cdi.findById(10);
		
		System.out.println(c);
	}
	

}
