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
			getConnect(); // app와 dbms연결
			String sql = "select * from jobs order by job_id"; 
			psmt = conn.prepareStatement(sql); // sql을 dbms로 가져감.
			rs = psmt.executeQuery(sql); // 돌아온 결과가 resultset  // rs: 한행의 이름이 rs
			while(rs.next()) {
				JobsVO vo = new JobsVO();
				vo.setJobId(rs.getString("job_id")); // 만약 int이면 rs.getint(a)
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
	
	//단건조회
	public EmpVO selectOne(String employeeId) {
		EmpVO vo = new EmpVO();
		try {
			getConnect();
			String sql = "select * from employees where employee_id=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, employeeId);
			rs = psmt.executeQuery();
			if(rs.next()) {
				vo.setEmployeeId(rs.getString("employee_id"));
				vo.setLastName(rs.getString("last_name"));
				vo.setEmail(rs.getString("email"));
				vo.setHireDate(rs.getString("hire_date"));
				vo.setJobId(rs.getString("job_id"));
				vo.setDepartmentId(rs.getString("department_id"));
	
			}
			
		} catch (Exception e){
			 e.printStackTrace();
		} finally {
			disconnect();
		}
		return vo;
	}
	
	// 전체조회
	public ArrayList<EmpVO> selectAll(String departmentId) {
		ArrayList<EmpVO> list = new ArrayList<EmpVO>();
		try {
			
			 
			//1.getConnect 연결
			getConnect();
			
			String sql = "select * from employees"; // 실행시켜서 결과 받아옴.
			
			if(departmentId != null && ! departmentId.isEmpty()) {
				sql += " where department_id = ? "; // ?: 외부에서 전달하는 값.
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
	
	
	
	// 등록
	public int insert(EmpVO vo) { // 값이 여러개면 VO가 편함 // 처리건수 넘어옴
		int r=0;
		try {
			// 1.cnnect
			getConnect();
			String sql = " insert into"
					   + " employees( employee_id, last_name, email, hire_date, job_id)"
					   + " values((select max(employee_id)+1 from employees),"
					   + " ?, ?, ?, ?, ?)";
			// 2.sql 구문준비
			psmt = conn.prepareStatement(sql);
			// 3.sql 구문실행
			psmt.setString(1, vo.getEmployeeId());
			psmt.setString(2, vo.getLastName());
			psmt.setString(3, vo.getEmail());
			psmt.setString(4, vo.getHireDate());
			psmt.setString(5, vo.getJobId());
			r = psmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return r;
	}
	
	// 수정
	public int update(EmpVO vo) {
		int cnt =0;
		
		return cnt;
	}
	// 삭제
	public int delete(String employeeId) {
		int cnt=0;
		try {
			getConnect();
			String sql = "delete from employees where employee_id = ?";			
			psmt = conn.prepareStatement(sql);			
			psmt.setString(1, employeeId);
			cnt = psmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return cnt;
	}
	

}
