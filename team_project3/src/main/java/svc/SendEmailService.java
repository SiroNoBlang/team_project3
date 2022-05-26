package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.MemberDAO;

public class SendEmailService {

	public boolean isSendEmail(String email) {
		System.out.println(" SendEmailService -  isSendEmail ");
		
		boolean isSendEmail = false;
		
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		
		close(con);
		
		return isSendEmail;
	}

}
