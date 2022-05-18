package svc;

import vo.MemberBean;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.MemberDAO;

public class ModifyUpdateService {

	public boolean getUpdateSuccess(MemberBean memberBean) {
//		System.out.println("ModifyUpdateService");
		boolean updateSuccess = false;
		
		Connection con = getConnection();
		
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		int updateCount = memberDAO.getUpdateCount(memberBean);
		
		if(updateCount > 0) {
			commit(con);
			updateSuccess = true;
		} else {
			rollback(con);
		}
		
		close(con);
		
		return updateSuccess;
	}
	
}
