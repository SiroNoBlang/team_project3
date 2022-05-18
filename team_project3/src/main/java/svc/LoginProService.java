package svc;

import java.sql.Connection;

import dao.MemberDAO;

import static db.JdbcUtil.*;
import vo.MemberBean;

public class LoginProService {

	public MemberBean isLogin(String member_id, String member_passwd) {
//		System.out.println("LoginProService");
		MemberBean isLogin = null;
		
//		System.out.println(member_id);
//		System.out.println(member_passwd);
		
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		
		isLogin = memberDAO.isLogin(member_id, member_passwd);
//		System.out.println(isLogin);
		
		close(con);
		
		return isLogin;
	}

}
