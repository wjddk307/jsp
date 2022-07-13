package co.micol.prj.member.service;

import java.util.List;

import co.micol.prj.member.vo.MemberVO;

public interface MemberService {
   
	// return value : 돌려받을 타입
	List<MemberVO> memberSelectList(); // 멤버 전체조회 R
	MemberVO memberSelectOne(MemberVO vo); // 멤버조회 R
	int memberInsert(MemberVO vo); // 멤버삽입 C
	int memberUpdate(MemberVO vo); // 멤버수정 U
	int memberDelete(MemberVO vo); // 멤버삭제 D

	boolean isMemberIdCheck(String id); // 아이디 중복체크 //존재하면 true -> is => 존재하면 false
	MemberVO memberLogin(MemberVO vo); // 로그인 처리 R
	
}
