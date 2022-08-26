package co.micol.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.micol.comm.DataSource;
import co.micol.service.ReplyService;
import co.micol.vo.NoticeVO;
import co.micol.vo.ReplyVO;

public class ReplyServiceImpl implements ReplyService{
	private DataSource dao = DataSource.getInstance();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	@Override
	public List<ReplyVO> ReplySelectList(ReplyVO vo) {
		// 전체목록
		List<ReplyVO> list = new ArrayList<>();
		String sql = "select * from reply where notice_id in (select notice_id from notice where notice_id = ?)";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getNoticeId());
			rs = psmt.executeQuery();
			while(rs.next()) {
				vo = new ReplyVO();
				vo.setNo(rs.getInt("no"));
				vo.setWriter(rs.getString("writer"));
				vo.setWrdate(rs.getDate("wrdate"));
				vo.setContent(rs.getString("content"));
				vo.setNoticeId(rs.getInt("notice_id"));
				list.add(vo);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return list;
	}

	@Override
	public int replyInsert(ReplyVO vo) {
		// 댓글등록
		int cnt = 0;
		String sql = "insert into reply (no, writer, wrdate, content, notice_id) values (?, ?, ?, ?, ?)";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getNo());
			psmt.setString(2, vo.getWriter());
			psmt.setDate(3, vo.getWrdate());
			psmt.setString(4, vo.getContent());
			psmt.setInt(5, vo.getNoticeId());
			
			cnt = psmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}

	@Override
	public int replyDelete(ReplyVO vo) {
		// 댓글 삭제
		
		return 0;
	}

	@Override
	public int replyUpdate(ReplyVO vo) {
		// 댓글 수정
		
		return 0;
	}
	
	private void close() {
		try {
			if(rs != null) rs.close();
			if(psmt != null) psmt.close();
			if(conn != null) conn.close();
	}catch(SQLException e) {
		e.printStackTrace();
	}
		
	}


}
