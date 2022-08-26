package co.micol.service;

import java.util.List;


import co.micol.vo.ReplyVO;

public interface ReplyService {
	List<ReplyVO> ReplySelectList(ReplyVO vo); // 전체조회
	int replyInsert(ReplyVO vo); // 댓글 입력
	int replyDelete(ReplyVO vo); // 댓글 삭제
	int replyUpdate(ReplyVO vo); // 댓글 수정


}
