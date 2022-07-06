package co.micol.prj.emp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = {"/EmpListServ", "/empList"})
public class EmpListServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public EmpListServ() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터
		String id = request.getParameter("departmentId");
		
		//DB처리
	    EmpDAO dao = new EmpDAO();  
		request.setAttribute("list", dao.selectAll(id));
		
		//페이지이동
		request.getRequestDispatcher("/WEB-INF/jsp/emp/empList.jsp")
		       .forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
