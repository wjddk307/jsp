package co.micol.prj.emp;

//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import co.micol.prj.comm.DAO;

public class EmpDAO extends DAO {
	//JOBs 전체조회
	public ArrayList<JobsVO> selectJobs() {
		ArrayList<JobsVO> list = new ArrayList<JobsVO>();
		try {
			getConnect();
			String sql = "select * from jobs order by job_id";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery(sql);
			while(rs.next()) {
				JobsVO vo = new JobsVO();
				vo.setJobId(rs.getString("job_id"));
				vo.setJobTitle(rs.getString("job_title"));
				list.add(vo);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	
	// 전체조회
	public ArrayList<EmpVO> selectAll(String departmentId) {
		ArrayList<EmpVO> list = new ArrayList<EmpVO>();
		try {
			
			 
			//1.getConnect 연결
			getConnect();
			
			String sql = "select * from employees"; // 실행시켜서 결과 받아옴.
			
			if(departmentId != null && ! departmentId.isEmpty()) {
				sql += " where department_id = ? ";
			}
			sql += " order by employee_id";
			
			// sql 구문 준비
			
			psmt = conn.prepareStatement(sql);
			if(departmentId != null && !  departmentId.isEmpty()) {
				psmt.setString(1, departmentId);
			}
			
			//실행
			ResultSet rs = psmt.executeQuery();
			
			//조회결과 list에 담기
			while(rs.next()) { //여러개라서 While
				EmpVO vo = new EmpVO(); 
				vo.setEmployeeId(rs.getString("employee_id"));
				vo.setLastName(rs.getString("last_name"));
				vo.setEmail(rs.getString("email"));
				vo.setHireDate(rs.getString("hire_date"));
				vo.setJobId(rs.getString("job_id"));
				list.add(vo);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			
		} finally {
			
			//4.disconnect 연결해제
			disconnect();
		}
		return list;
	}
	
	// 단건조회
	public EmpVO selectOne(String id) {
		EmpVO vo = new EmpVO();
		return vo;
	}
	
	// 등록
	public int insert(EmpVO vo) { // 값이 여러개면 VO가 편함 // 처리건수 넘어옴
		int cnt=0;
		try {
			// 1.cnnect
			getConnect();
			String sql = " insert into"
					   + " employees( employee_id, last_name, email, hire_date, job_id)"
					   + " values(?, ?, ?, ?, ?)";
			// 2.sql 구문준비
			psmt = conn.prepareStatement(sql);
			// 3.sql 구문실행
			psmt.setString(1, vo.getEmployeeId());
			psmt.setString(2, vo.getLastName());
			psmt.setString(3, vo.getEmail());
			psmt.setString(4, vo.getHireDate());
			psmt.setString(5, vo.getJobId());
			cnt = psmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return cnt;
	}
	
	// 수정
	
	// 삭제
	
	

}
