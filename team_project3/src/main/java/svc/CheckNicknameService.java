package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.MemberDAO;

public class CheckNicknameService {
	// MemberDAO 객체의 checkNickname() 메서드를 호출하여 데이터베이스에서의 닉네임 중복 판별
	// checkIdService랑 합쳐서 판별해야함. 하나의 서비스만 있으면 된다는 소리임.
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
		
		return isDuplicate;
	}
}
