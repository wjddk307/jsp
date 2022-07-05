package co.micol.prj.dept;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.dept.DeptDAO;
import co.micol.prj.dept.DeptVO;


@WebServlet("/DeptInsert")
public class DeptInsertServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
       
    // 등록 페이지 요청
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/dept/deptinsert.jsp")
		.forward(request, response);
	}
    // DB 등록 처리
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("departmentId");
		String name = request.getParameter("departmentName");
		// 파라미터를 
		DeptVO vo = new DeptVO();
		vo.setDepartmentId(id);
		vo.setDepartmentName(name);
		
		DeptDAO deptDAO = new DeptDAO();
		int cnt = deptDAO.deptInsert(vo);
		
		response.getWriter()
		        .append(cnt +"건이 등록됨");
		response.getWriter()
        .append(id)
        .append(name);
	}

}
