package svc;

import java.sql.Connection;

import dao.MemberDAO;

import static db.JdbcUtil.*;
import vo.MemberBean;

public class JoinProService {

	public boolean joinSuccess(MemberBean memberBean) {
		System.out.println("JoinProService");
		boolean isJoinSuccess = false;
		
		Connection con = getConnection();
		
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		int joinCount = memberDAO.joinSuccess(memberBean);
		
		if(joinCount > 0) {
			commit(con);
			isJoinSuccess = true;
		} else {
			rollback(con);
		}
		close(con);
		
		return isJoinSuccess;
	}
	
}
