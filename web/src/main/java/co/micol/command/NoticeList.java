package co.micol.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.comm.Command;
import co.micol.service.NoticeService;
import co.micol.serviceImpl.NoticeServiceImpl;
import co.micol.vo.NoticeVO;

public class NoticeList implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 게시글 목록 가져오기
		NoticeService noticeDao = new NoticeServiceImpl();
		List<NoticeVO> list = new ArrayList<>();
		list = noticeDao.noticeSelectList();
		request.setAttribute("list", list);
		return "notice/noticeList";
	}

}
