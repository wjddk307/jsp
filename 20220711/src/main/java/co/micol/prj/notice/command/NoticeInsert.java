package co.micol.prj.notice.command;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import co.micol.prj.comm.Command;
import co.micol.prj.notice.service.NoticeService;
import co.micol.prj.notice.service.NoticeServiceImpl;
import co.micol.prj.notice.vo.NoticeVO;

public class NoticeInsert implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 게시글 등록(파일 업로드 포함)
		NoticeService noticeDao = new NoticeServiceImpl();
		NoticeVO vo = new NoticeVO(); // DB사용위해 vo사용
		
		String savePath = "C:\\Temp\\";  // 파일 저장 위치 실서버 : "fileSave"
		int upLoadSize = 1024*1024*1024; // 최대파일 사이즈: 100MB
		int n = 0; // 결과값 비교를 위해
		
		try {
			MultipartRequest multi = new MultipartRequest(
					request, savePath, upLoadSize,"utf-8", // savePath: 파일 올라갈 위치
					new DefaultFileRenamePolicy()); // DefaultFileRenamePolicy: 파일 네임 중복시 자동 이름 변경.
			String orignalFileName = multi.getOriginalFileName("file"); // 오리지날 파일 네임 : 파일이 존재하는지
			String saveFileName = multi.getFilesystemName("file"); // 물리적 파일 네임
//			System.out.println(orignalFileName);
//			System.out.println(saveFileName);
			vo.setNoticeWriter(multi.getParameter("noticeWriter")); // Multipart는
			vo.setNoticeTitle(multi.getParameter("noticeTitle"));
			vo.setNoticeDate(Date.valueOf(multi.getParameter("noticeDate")));
			vo.setNoticeSubject(multi.getParameter("noticeSubject"));
			if(orignalFileName != null) { // 만약 파일이 존재 한다면
				vo.setNoticeAttach(orignalFileName);
				saveFileName = savePath + saveFileName; // 파일 경로 추가한다
				vo.setNoticeAttachDir(saveFileName);
			}
			n = noticeDao.noticeInsert(vo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String returnPage = null;
		if(n != 0) { // 정상등록
			returnPage = "noticeList.do"; // 정상삽입일 때 list보여줌.
		} else {
			request.setAttribute("message", "게시글 등록을 실패했습니다.");
			returnPage = "notice/noticeError";
		}
		return returnPage;
	}

}
