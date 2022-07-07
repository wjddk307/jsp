package co.micol.prj.comm;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.MainCommand;


@WebServlet("*.do") // 모든 *.do 요청은 내가 처리한다.
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private HashMap<String, Command> map = new HashMap<>(); // 요청과 실행문을 매핑하기위해   
    
    public FrontController() {
        super();
    }

	
	public void init(ServletConfig config) throws ServletException {
		// 초기화하는 메소드(Mapping 하는 부분 작성)
		map.put("/main.do", new MainCommand()); // 처음 접속하는 페이지 -> main.do로 들어오면 MainCommand() 실행
	}

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 실행하는 메소드(서비스 해주는것)
		request.setCharacterEncoding("utf-8"); // 한글깨짐 방지
		String uri = request.getRequestURI(); // 요청된 URI를 확인
		String contextPath = request.getContextPath(); // 요청 URL로부터 contextPath를 확인한다(contextPath에 값 담음.)
		String page = uri.substring(contextPath.length()); // 실제 요청명령( 실제 요청페이지를 찾는다)
		
		Command command = map.get(page); // 실제 수행할 Command를 찾음. // MainCommand()를 통해 초기화
		// = new MainCommand();
		String viewPage = command.exec(request, response); // 요청 Commdand를 수행하고 결과를 받음 - 실행값이 문자열
		// viewPage <= main/main
		
		
		// viewResolve -> 우리가 만들어줘야함
		if(!viewPage.endsWith(".do") && !viewPage.equals(null)) { // 마지막 문자열 끝에 .do가 붙어있지 않고 결과가 없으면
			viewPage = "WEB-INF/views/" + viewPage + ".jsp";  // 시스템에서 접근가능한 폴더를 더해주고
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response); // 원하는 페이지를 호출해서 전달함 // 갖고있는 ((response, request)=내가갖고있는) 데이터 객체를 사용가능.
			} else {
				response.sendRedirect(viewPage); // .do로 권한 위임 처리 // 무조건 response,request 객체 만듬
			}
	}

}
