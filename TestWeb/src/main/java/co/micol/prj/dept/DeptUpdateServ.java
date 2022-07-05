package co.micol.prj.dept;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.dept.DeptDAO;
import co.micol.prj.dept.DeptVO;


@WebServlet("/DeptUpdate")
public class DeptUpdateServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
       
    // 수정 페이지 요청
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터
		String departmentId = request.getParameter("departmentId");
		//부서번호 단건조회
		DeptDAO deptDAO = new DeptDAO();
		request.setAttribute("dept", deptDAO.selectOne(departmentId)); //vo가 하나라서
		
		request.getRequestDispatcher("/WEB-INF/jsp/dept/deptUpdate.jsp")
		.forward(request, response);
	}
	
    // DB 수정 처리
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("departmentId");
		String name = request.getParameter("departmentName");
		// 파라미터VO담고
		DeptVO vo = new DeptVO();
		vo.setDepartmentId(id);
		vo.setDepartmentName(name);
		
		DeptDAO deptDAO = new DeptDAO();
		int cnt = deptDAO.update(vo);
		
		response.getWriter()
		        .append(cnt +"건이 등록됨");
		response.getWriter()
        .append(id)
        .append(name);
	}

}
