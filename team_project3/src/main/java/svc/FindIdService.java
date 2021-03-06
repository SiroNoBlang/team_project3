package svc;

import java.sql.Connection;

import dao.MemberDAO;

import static db.JdbcUtil.*;

public class FindIdService {

	public String isFindId(String nickname, String email) {
		
		String isFindId ="";
		
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance(); 
		memberDAO.setConnection(con);
		
		isFindId = memberDAO.isFindId(nickname, email);
		
		close(con);
		
		return isFindId;
	}

}
