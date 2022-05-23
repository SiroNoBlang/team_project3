package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.MemberDAO;

public class CheckIdService {
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
