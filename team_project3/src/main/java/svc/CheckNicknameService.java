package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.MemberDAO;

public class CheckNicknameService {
	public boolean checkNickname(String nickname) {
		boolean isDuplicate = false;
		
		Connection con = getConnection();
		
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		isDuplicate = memberDAO.checkNickname(nickname);
		
		if(isDuplicate) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		System.out.println(isDuplicate);
		return isDuplicate;
	}
}
