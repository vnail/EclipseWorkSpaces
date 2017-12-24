package web;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CostDao;
import dao.CostDaoImpl;
import entity.Cost;

public class MainServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("开始执行MainServlet");
		String path=req.getServletPath();
		System.out.println("path="+path);
		if("/findCosts.do".equals(path)) {
			System.out.println("执行findCosts.do");
			findCosts(req,res);
		} else if("/toAddCost.do".equals(path)) {
			System.out.println("执行"+path);
			toAddCost(req,res);
			
		}else if("/addCost.do".equals(path)){
			System.out.println("执行"+path);
			addCost(req,res);
		}else if("/toUpdateCost.do".equals(path)){
			toUpdateCost(req,res);
		}else if("/saveUpdate.do".equals(path)){
			saveUpdate(req,res);
		}else {
			System.out.println("没有这个路径");
			throw new RuntimeException("不存在的路径");
		}
		
	}
	
	private void findCosts(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		CostDaoImpl cdi = new CostDaoImpl();
		List<Cost> list = cdi.findAll();
		
		for(Cost c:list) {
			System.out.println(c.getName());
		}
		
		req.setAttribute("costs", list);
		//当前：/netctoss/findCost.do
		//目标：/netctoss/WEB_INF/cost/find.jsp
		req.getRequestDispatcher("WEB-INF/cost/find.jsp").forward(req, res);
	}
	
	private void toAddCost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		
		//当前：/netctoss/toAddCost.do
		//目标：/netctoss/WEB-INF/cost/add.jsp
		req.getRequestDispatcher("WEB-INF/cost/add.jsp").forward(req, res);
	}
	
	private void addCost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException {
		req.setCharacterEncoding("utf-8");
		//资费名
		String name=req.getParameter("name");
		//基本时长
		String baseDuration=req.getParameter("baseDuration");
		//基本费用
		String baseCost=req.getParameter("baseCost");
		//单位费用
		String unitCost=req.getParameter("unitCost");
		//状态:0-开通；1-禁用
		String status=req.getParameter("status");
		//描述
		String descr = req.getParameter("descr");
		//资费类型1-包月；2-套餐；3-计时；
		String costType=req.getParameter("costType");
		
		Cost c = new Cost();
		c.setName(name);
		c.setCostType(costType);
		if(baseDuration!=null&&!baseDuration.equals("")) {
			c.setBaseDuration(new Integer(baseDuration));
		}
		if(baseCost!=null&&!baseCost.equals("")) {
			c.setBaseCost(new Double(baseCost));
		}
		if(unitCost!=null&&!unitCost.equals("")) {
			c.setUnitCost(new Double(unitCost));
		}
		c.setDescr(descr);
		
		System.out.println(c);
		
		CostDao dao = new CostDaoImpl();
		dao.save(c);
		
		//当前：/entctoss/addCost.do
		//目标：/netctoss/findCost.do
		res.sendRedirect("findCosts.do");

	}
	
	private void toUpdateCost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		//接收参数
		String id = req.getParameter("id");
		//根据id查询对应的数据
		CostDao dao = new CostDaoImpl();
		Cost cost=dao.findById(new Integer(id));
		
		//转发
		req.setAttribute("cost", cost);
		req.getRequestDispatcher("WEB-INF/cost/update.jsp").forward(req, res);	
	}
	private void saveUpdate(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		req.setCharacterEncoding("utf-8");
		String costId = req.getParameter("costId");
		//资费名
		String name=req.getParameter("name");
		//基本时长
		String baseDuration=req.getParameter("baseDuration");
		//基本费用
		String baseCost=req.getParameter("baseCost");
		//单位费用
		String unitCost=req.getParameter("unitCost");
		//状态:0-开通；1-禁用
		String status=req.getParameter("status");
		//描述
		String descr = req.getParameter("descr");
		//资费类型1-包月；2-套餐；3-计时；
		String costType=req.getParameter("costType");
		
		Cost c = new Cost();
		if(costId!=null&&!costId.equals("")) {
			c.setCostId(new Integer(costId));
		}
		c.setName(name);
		c.setCostType(costType);
		if(baseDuration!=null&&!baseDuration.equals("")) {
			c.setBaseDuration(new Integer(baseDuration));
		}
		if(baseCost!=null&&!baseCost.equals("")) {
			c.setBaseCost(new Double(baseCost));
		}
		if(unitCost!=null&&!unitCost.equals("")) {
			c.setUnitCost(new Double(unitCost));
		}
		c.setDescr(descr);
		
		System.out.println(c);
		
		CostDao dao = new CostDaoImpl();
		dao.saveUpdate(c);
		
		//当前：/entctoss/addCost.do
		//目标：/netctoss/findCost.do
		res.sendRedirect("findCosts.do");
	}
}
