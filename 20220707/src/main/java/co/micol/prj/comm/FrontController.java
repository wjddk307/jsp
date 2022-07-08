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
import co.micol.prj.member.command.AjaxMemberIdCheck;
import co.micol.prj.member.command.MemberJoin;
import co.micol.prj.member.command.MemberJoinForm;
import co.micol.prj.member.command.MemberList;
import co.micol.prj.member.command.MemberLogin;
import co.micol.prj.member.command.MemberLoginForm;
import co.micol.prj.member.command.MemberLogout;


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
		map.put("/memberLoginForm.do", new MemberLoginForm()); //로그인 폼 호출
		map.put("/memberLogin.do", new MemberLogin()); //로그인처리
		map.put("/memberLogout.do", new MemberLogout()); //로그아
		map.put("/memberList.do", new MemberList());
		map.put("/memberJoinForm.do", new MemberJoinForm());
	    map.put("/ajaxMemberIdCheck.do", new AjaxMemberIdCheck());
	    map.put("/memberJoin.do", new MemberJoin()); //회원가입 처리
		// 파일명
	}

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 실행하는 메소드(서비스 해주는것)
		request.setCharacterEncoding("utf-8"); // 한글깨짐 방지
		String uri = request.getRequestURI(); // 요청된 URI를 확인
		String contextPath = request.getContextPath(); // 요청 URL로부터 contextPath를 확인한다(contextPath에 값 담음.)
		String page = uri.substring(contextPath.length()); // 실제 요청명령( 실제 요청페이지를 찾는다)
		System.out.println("page"+ page) ;
		Command command = map.get(page); // 실제 수행할 Command를 찾음. // MainCommand()를 통해 초기화
		// Command command = new MainCommand();
		System.out.println("command"+ command) ;
		String viewPage = command.exec(request, response); // 요청 Command를 수행하고 결과를 받음 - 실행값이 문자열
		System.out.println("viewPage"+ viewPage) ; 
		// viewPage <= main/main
		
		
		// viewResolve -> 우리가 만들어줘야함 => 보여줄페이지
		if(!viewPage.endsWith(".do") && !viewPage.equals(null)) { // 마지막 문자열 끝에 .do가 붙어있지 않고 결과가 없으면
			if(viewPage.startsWith("ajax:")) { // ajax를 처리하는 뷰 리즐브
				response.setContentType("text/html; charset=UTF-8");
				response.getWriter().append(viewPage.substring(5));
				return;
			}
			viewPage = "WEB-INF/views/" + viewPage + ".jsp";  //정상적으로 들어올 때 // 시스템에서 접근가능한 폴더를 더해주고
			System.out.println(viewPage);
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response); // 원하는 페이지를 호출해서 전달함 // 갖고있는 ((response, request)=내가갖고있는) 데이터 객체를 사용가능.
			} else {
				response.sendRedirect(viewPage); // .do로 왔을 때 권한 위임 처리 // 무조건 response,request 객체 만듬
			}
	}

}
