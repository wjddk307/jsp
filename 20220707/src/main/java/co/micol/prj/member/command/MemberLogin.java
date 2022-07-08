package co.micol.prj.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.micol.prj.comm.Command;
import co.micol.prj.member.service.MemberService;
import co.micol.prj.member.serviceImpl.MemberServiceImpl;
import co.micol.prj.member.vo.MemberVO;

public class MemberLogin implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 로그인 처리
		HttpSession session = request.getSession(); // 서버가 만들어 놓은 세션을 가져온다. 서버정보는 request통해서 가져옴
		MemberService memberDao = new MemberServiceImpl();
		MemberVO vo = new MemberVO();
		vo.setMemberId(request.getParameter("memberId")); //login from에 input 태그 name 속성 가져옴(form에서 가져오는건 string)
		vo.setMemberPassword(request.getParameter("memberPassword"));
		//vo.setMemberName(request.getParameter("memberName"));
		
		vo = memberDao.memberLogin(vo); // 오른쪽 수행 왼쪽 저장
		if(vo.getMemberName() != null) {
			session.setAttribute("id", vo.getMemberId()); // 세션에 담는다 id라는 이름으로
			session.setAttribute("name", vo.getMemberName());
			session.setAttribute("author", vo.getMemberAuthor()); // 세션에 담는다 author라는 이름으로
			request.setAttribute("message", vo.getMemberName() + "님 환영합니다."); // 넘겨줄 페이지
		} else {
			request.setAttribute("message", "아이디 또는 패스워드가 일치하디 않습니다");
		}
		return "member/memberLogin";
	}

}
