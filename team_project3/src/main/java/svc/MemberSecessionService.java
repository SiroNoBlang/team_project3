package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.MemberDAO;
import vo.MemberBean;

public class MemberSecessionService {

	public boolean getUpdateSuccess(MemberBean member) {
		System.out.println("회원탈퇴 서비스");
		boolean MemberSecession = false;
		
		Connection con = getConnection();
		
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		int updateCount = memberDAO.getMemberSecessionCount(member);
		
		if(updateCount > 0) {
			commit(con);
			MemberSecession = true;
		} else {
			rollback(con);
		}
		
		close(con);
		
		
		return MemberSecession;
	}

}
