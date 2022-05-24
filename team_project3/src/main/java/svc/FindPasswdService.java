package svc;


import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.MemberDAO;

public class FindPasswdService {

	public boolean isFindPasswd(String id, String email) {
		System.out.println("FindPasswdService - isFindPasswd");
		
		boolean isFindPasswd = false;
		
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		isFindPasswd = memberDAO.isFindPasswd(id, email);
		
		close(con);
		
		return isFindPasswd;
	}

}
