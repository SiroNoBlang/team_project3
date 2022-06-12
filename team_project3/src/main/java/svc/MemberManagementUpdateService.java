package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.AdminDAO;

public class MemberManagementUpdateService {

	public boolean getMemberUpdate(String member_code, String member_status, String reason) {
		boolean isMemberUpdate = false;
		
		Connection con = getConnection();
		
		AdminDAO adminDAO = AdminDAO.getInstance();
		adminDAO.setConnection(con);
		
		// 업데이트 한 결과를 가져올 getMemberUpdate() 메서드 _이효민 06.12 확인
		isMemberUpdate = adminDAO.getMemberUpdate(member_code, member_status, reason);
		
		if(isMemberUpdate) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isMemberUpdate;
	}

}
