package svc;

import java.sql.Connection;

import dao.MemberDAO;

import static db.JdbcUtil.*;
import vo.MemberBean;

public class LoginProService {

	public MemberBean isLogin(String member_id, String member_passwd) {
		MemberBean isLogin = null;
		
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		isLogin = memberDAO.isLogin(member_id, member_passwd);
		
		close(con);
		
		return isLogin;
	}

}
