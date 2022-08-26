package co.micol.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.comm.Command;
import co.micol.service.NoticeService;
import co.micol.service.ReplyService;
import co.micol.serviceImpl.NoticeServiceImpl;
import co.micol.serviceImpl.ReplyServiceImpl;
import co.micol.vo.NoticeVO;
import co.micol.vo.ReplyVO;

public class NoticeSelect implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 게시글 조회
		NoticeService noticeDao = new NoticeServiceImpl();
		NoticeVO vo = new NoticeVO();
		vo.setNoticeId(Integer.valueOf(request.getParameter("id")));
		vo = noticeDao.noticeSelect(vo);
		request.setAttribute("vo", vo);
		
		// 댓글 전체조회(ReplySelectList())
		//1. 쿼리문 가져오기(serviceImpl)
		ReplyService replyDao = new ReplyServiceImpl();
		//2. 값을 담을 vo 선언하기
		List<ReplyVO> list = new ArrayList<>();
		// 파라메타로 가져온 notice_id 넘겨주기
		ReplyVO rvo = new ReplyVO();
		rvo.setNoticeId(Integer.valueOf(request.getParameter("id")));
		//3. vo에 호출되어 리턴 값 담은 메소드(사용할 쿼리문이 담긴 메소드) 대입
		list = replyDao.ReplySelectList(rvo);
		//4. vo를 jsp에 보내기 위해 setAttribute에 담기 
		request.setAttribute("list", list);
		
		
		return "notice/noticeSelect";
	}

}
