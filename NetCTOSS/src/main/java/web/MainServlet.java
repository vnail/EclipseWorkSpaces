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
		System.out.println("��ʼִ��MainServlet");
		String path=req.getServletPath();
		System.out.println("path="+path);
		if("/findCosts.do".equals(path)) {
			System.out.println("ִ��findCosts.do");
			findCosts(req,res);
		} else if("/toAddCost.do".equals(path)) {
			System.out.println("ִ��"+path);
			toAddCost(req,res);
			
		}else if("/addCost.do".equals(path)){
			System.out.println("ִ��"+path);
			addCost(req,res);
		}else if("/toUpdateCost.do".equals(path)){
			toUpdateCost(req,res);
		}else if("/saveUpdate.do".equals(path)){
			saveUpdate(req,res);
		}else {
			System.out.println("û�����·��");
			throw new RuntimeException("�����ڵ�·��");
		}
		
	}
	
	private void findCosts(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		CostDaoImpl cdi = new CostDaoImpl();
		List<Cost> list = cdi.findAll();
		
		for(Cost c:list) {
			System.out.println(c.getName());
		}
		
		req.setAttribute("costs", list);
		//��ǰ��/netctoss/findCost.do
		//Ŀ�꣺/netctoss/WEB_INF/cost/find.jsp
		req.getRequestDispatcher("WEB-INF/cost/find.jsp").forward(req, res);
	}
	
	private void toAddCost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		
		//��ǰ��/netctoss/toAddCost.do
		//Ŀ�꣺/netctoss/WEB-INF/cost/add.jsp
		req.getRequestDispatcher("WEB-INF/cost/add.jsp").forward(req, res);
	}
	
	private void addCost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException {
		req.setCharacterEncoding("utf-8");
		//�ʷ���
		String name=req.getParameter("name");
		//����ʱ��
		String baseDuration=req.getParameter("baseDuration");
		//��������
		String baseCost=req.getParameter("baseCost");
		//��λ����
		String unitCost=req.getParameter("unitCost");
		//״̬:0-��ͨ��1-����
		String status=req.getParameter("status");
		//����
		String descr = req.getParameter("descr");
		//�ʷ�����1-���£�2-�ײͣ�3-��ʱ��
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
		
		//��ǰ��/entctoss/addCost.do
		//Ŀ�꣺/netctoss/findCost.do
		res.sendRedirect("findCosts.do");

	}
	
	private void toUpdateCost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		//���ղ���
		String id = req.getParameter("id");
		//����id��ѯ��Ӧ������
		CostDao dao = new CostDaoImpl();
		Cost cost=dao.findById(new Integer(id));
		
		//ת��
		req.setAttribute("cost", cost);
		req.getRequestDispatcher("WEB-INF/cost/update.jsp").forward(req, res);	
	}
	private void saveUpdate(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		req.setCharacterEncoding("utf-8");
		String costId = req.getParameter("costId");
		//�ʷ���
		String name=req.getParameter("name");
		//����ʱ��
		String baseDuration=req.getParameter("baseDuration");
		//��������
		String baseCost=req.getParameter("baseCost");
		//��λ����
		String unitCost=req.getParameter("unitCost");
		//״̬:0-��ͨ��1-����
		String status=req.getParameter("status");
		//����
		String descr = req.getParameter("descr");
		//�ʷ�����1-���£�2-�ײͣ�3-��ʱ��
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
		
		//��ǰ��/entctoss/addCost.do
		//Ŀ�꣺/netctoss/findCost.do
		res.sendRedirect("findCosts.do");
	}
}
