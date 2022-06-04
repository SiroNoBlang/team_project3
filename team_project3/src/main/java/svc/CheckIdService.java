package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.MemberDAO;

public class CheckIdService {
	// MemberDAO 객체의 checkId() 메서드를 호춣하여 데이터베이스에서의 아이디 중복 판별
	// checkNicknameService랑 합쳐서 판별해야함.
	public boolean checkId(String id) {
		boolean isDuplicate=false;
		
		Connection con = getConnection();
		
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		isDuplicate = memberDAO.checkId(id); 
		
		if(isDuplicate) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isDuplicate;
	}
}
