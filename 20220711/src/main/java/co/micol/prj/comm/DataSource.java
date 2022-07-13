package co.micol.prj.comm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
   
    private static DataSource dataSource = new DataSource(); // 인스턴스 생성
    private Connection conn; // 커넥션 생성
    
    private DataSource() {  // 외부에서 생성x: private 생성자를 만들고
    	
    } 
    
    public static DataSource getInstance() { // 인스턴스를 리턴
    	return dataSource;
    }
    
    public Connection getConnection() {
    	try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "micol", "1234");
			System.out.println("DB연결성공");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
    	return conn;
    }
}
