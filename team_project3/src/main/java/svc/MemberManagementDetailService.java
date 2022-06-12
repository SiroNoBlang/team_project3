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
		
		// DAO 작업을 하여 가져올 getMemberArticle() 메서드 _이효민 06.12 수정
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
