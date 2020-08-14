package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
	// static block : 이 클래스(JDBCUtil)가 로드될 때, static block이 존재하면 단 한번만 실행된다. 
	// 이 클래스를 로드하면 드라이버 로딩이 자동으로 진행된다. 
	static {
		try {
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// Connection 객체를 반환하는 메서드.
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "WHC", "java");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DB 연결 실패");
			return null;
		}
	}
}
