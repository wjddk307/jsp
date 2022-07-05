package co.micol.prj.emp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.dept.DeptDAO;

@WebServlet("/empInsert")
public class EmpInsertServ extends HttpServlet {

	// 등록페이지로 이동
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// DB조회
		// jobs, 부서, 사원
		EmpDAO empDAO = new EmpDAO();
		DeptDAO deptDAO = new DeptDAO();
		request.setAttribute("jobs", empDAO.selectJobs());
		request.setAttribute("depts", deptDAO.selectAll());
		
		request.getRequestDispatcher("/WEB-INF/jsp/emp/empInsert.jsp")
		       .forward(request, response);
	}
	
	// DB 등록처리
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
		int cnt = empDAO.insert(vo);
		//결과출력
		response.getWriter()
                .append(cnt + "건이 등록됨");	
		
     
	}
	
	

}
