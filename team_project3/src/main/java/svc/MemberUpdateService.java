package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.MemberDAO;

public class MemberUpdateService {

	public boolean getMemberUpdate(String member_code, String member_status, String reason) {
		boolean isMemberUpdate = false;
		
		Connection con = getConnection();
		
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		isMemberUpdate = memberDAO.getMemberUpdate(member_code, member_status, reason);
		
		if(isMemberUpdate) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isMemberUpdate;
	}

}
