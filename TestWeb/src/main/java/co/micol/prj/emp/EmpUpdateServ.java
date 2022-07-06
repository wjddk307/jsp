package co.micol.prj.emp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.dept.DeptDAO;

//http://localhost/컨텍스트패스/empInsert
@WebServlet("/empUpdate")
public class EmpUpdateServ extends HttpServlet {

	// 수정페이지로 이동
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// DB조회
		// jobs, 부서, 사원
		EmpDAO empDAO = new EmpDAO();
		DeptDAO deptDAO = new DeptDAO();
		request.setAttribute("jobs", empDAO.selectJobs());
		request.setAttribute("depts", deptDAO.selectAll());
		
		// 수정할 사원의 사번을 받아 (사번 단건 조회)
		String employeeId = request.getParameter("employeeId");
		request.setAttribute("emp", empDAO.selectOne(employeeId));
		request.getRequestDispatcher("/WEB-INF/jsp/emp/empUpdate.jsp")
		       .forward(request, response);
	}
	
	// DB 수정처리
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,  IOException  {
		
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		// 파라미터를 VO담고
		String id = request.getParameter("employeeId");
		String name = request.getParameter("lastName");
		String jobid = request.getParameter("jobId");
		String email = request.getParameter("email");
		String hiredate = request.getParameter("hireDate");
		
		EmpVO vo = new EmpVO();
		vo.setEmployeeId(id);
		vo.setLastName(name);
		vo.setJobId(jobid);
		vo.setEmail(email);
		vo.setHireDate(hiredate);
		
		//DB처리
		EmpDAO empDAO = new EmpDAO();
		int cnt = empDAO.update(vo);
		
		//결과출력
		response.getWriter()
                .append(cnt + "건이 등록됨");	
		
     
	}
	
	

}
