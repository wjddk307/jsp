package co.micol.prj.member.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.micol.prj.comm.DataSource;
import co.micol.prj.member.service.MemberService;
import co.micol.prj.member.vo.MemberVO;

public class MemberServiceImpl implements MemberService {
	private DataSource dao = DataSource.getInstance();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	@Override
	public List<MemberVO> memberSelectList() {
		//전체멤버 목록
		List<MemberVO> list = new ArrayList<MemberVO>(); // 결과담을 객체
		MemberVO vo; //사용할 매개변수
		String sql = "SELECT * FROM MEMBER";
		 try {
			 conn = dao.getConnection();
			 psmt = conn.prepareStatement(sql);
			 rs = psmt.executeQuery();
			 while(rs.next()) {
				 vo = new MemberVO(); // vo초기회
				 vo.setMemberId(rs.getString("member_id")); // 테이블 컬럼과  이름일치
				 vo.setMemberPassword(rs.getString("member_password"));
				 vo.setMemberName(rs.getString("member_name"));
				 vo.setMemberAuthor(rs.getString("member_author"));
				 list.add(vo);
			 }
		 } catch(SQLException e) {
			 e.printStackTrace();
		 } finally {
			 close();
		 }
		return list;
	}

	@Override
	public MemberVO memberSelectOne(MemberVO vo) {
		// 한명조회
		String sql = "SELCT * FROM MEMBER WHERE MEMBER_ID = ?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getMemberId());
			rs = psmt.executeQuery();
			if(rs.next()) {
				 vo.setMemberId(rs.getString("member_id")); // 테이블 컬럼과  이름일치
//				 vo.setMemberPassword(rs.getString("member_password"));
				 vo.setMemberName(rs.getString("member_name"));
				 vo.setMemberAuthor(rs.getString("member_author"));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}

	@Override
	public int memberInsert(MemberVO vo) {
		int cnt = 0;
		String sql = "insert into member (member_id, member_name, member_password, member_author) values(?, ? ,?, ?) ";
		try {
			conn = dao.getConnection();
			 psmt = conn.prepareStatement(sql);
			 psmt.setString(1, vo.getMemberId());
			 psmt.setString(2, vo.getMemberName());
			 psmt.setString(3, vo.getMemberPassword());
			 psmt.setString(4, vo.getMemberAuthor());
			 cnt = psmt.executeUpdate();	
			 
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int memberUpdate(MemberVO vo) {
		// 회원정보 변경 (password, author)
		int cnt = 0;
		String sql = "update member set member_password = ?, "
				+ " member_author = ? where member_id = ?";
		try {
			conn = dao.getConnection();
			 psmt = conn.prepareStatement(sql);		
			 psmt.setString(1, vo.getMemberPassword());
			 psmt.setString(2, vo.getMemberAuthor());
			 psmt.setString(3, vo.getMemberId());
			 cnt = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}

	@Override
	public int memberDelete(MemberVO vo) {
		int n = 0;
		String sql = "DELETE FROM MEMBER WHERE_ID = ?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getMemberId());
			n = psmt.executeUpdate();
			} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}

	@Override
	public boolean isMemberIdCheck(String id) {
		//회원 아이디 중복체크
		boolean b = false;
		String sql = "SELECT MEMBER_ID FROM MEMBER WHERE MEMBER_ID = ? ";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if(!rs.next()) {   // 존재하지 않으면  true, 존재하면 false를 돌려준다.
				b = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return b;
	}

	@Override
	public MemberVO memberLogin(MemberVO vo) {
		  // 회원 로그인
		String sql = "SELECT * FROM MEMBER WHERE MEMBER_ID = ? AND MEMBER_PASSWORD = ?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getMemberId());
			psmt.setString(2, vo.getMemberPassword());
			rs = psmt.executeQuery();
			if(rs.next()) {
				vo.setMemberId(rs.getString("member_id"));
				vo.setMemberName(rs.getString("member_name"));
				vo.setMemberAuthor(rs.getString("member_author"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
			
		}
 		return vo;
	}
	
	private void close() {
		try {
			if(rs != null) rs.close();
			if(psmt !=null) psmt.close();
			if(conn != null) conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
