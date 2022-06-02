package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.AdminDAO;
import vo.MemberBean;

public class MemberManagementDetailService {
	public MemberBean getMemberDetail(String member_code) {
		MemberBean memberDetail = null;
		
		Connection con = getConnection();
		
		AdminDAO adminDAO = AdminDAO.getInstance();
		adminDAO.setConnection(con);
		
		memberDetail = adminDAO.getMemberArticle(member_code);
		
		if(memberDetail != null) {
			commit(con);
			
		} else {
			rollback(con);
		}
		
		close(con);
		
		return memberDetail;
	}
}
