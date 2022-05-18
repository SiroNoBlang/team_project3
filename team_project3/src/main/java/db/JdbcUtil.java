package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

// 데이터베이스 관련 설정 및 관리 작업을 수행하는 클래스
public class JdbcUtil {
	
	//1. DB 연결 작업을 수행한 후 Connection 객체를 리턴하는 getConnection() 메서드 정의
	//=> 파라미터 :없음 , 리턴타입 :java.sql.Connection
	public static Connection getConnection() {
		Connection con = null;
		
		try {
				// 1 Context 태그 부분(객체) 가져오기 => Context 타입으로 업캐스팅
				Context initCtx = new InitialContext();
							
				//2
	//			Context envCtx = (Context)initCtx.lookup("java:comp/env");
							
				// 3 * context.xml 파일에 지정된 이름에 따라 문자열 바뀔 수 있다.*
	//			DataSource ds = (DataSource)envCtx.lookup("jdbc/MySQL");
						
				//2,3 하나로 결합하는 경우 (2번문자열/3번문자열)
				DataSource ds = (DataSource)initCtx.lookup("java:comp/env/jdbc/MySQL");
						
				//4 Connection 객체 가져오기 . 리턴타입 : java.sql.Connection
				con = ds.getConnection();
				
				con.setAutoCommit(false);//auto commint 기능 해제(기능 실행 시 true전달)
				
			} catch (NamingException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//Connection 객체 리턴
			return con;
		
		}//getConnection() 메서드 끝
	
		//2. DB작업 완료 후 자원을 반환하기 위한 close() 메서드 정의
		//=>Connection, PreparedStatement, Result객체
		public static void close(Connection con) {
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		public static void close(PreparedStatement pstmt) {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		public static void close(ResultSet rs) {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		//3 트랜잭션 처리에 필요한 commint, rollback 작업을 수행하기 위한 메서드 정의
		// 단 , Connection 객체에 대해 auto Commint기능 해제 필수
		//파라미터 : Connection 객체(con)
		public static void commit(Connection con) {
			try {
				con.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		public static void rollback(Connection con) {
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		

	
}
