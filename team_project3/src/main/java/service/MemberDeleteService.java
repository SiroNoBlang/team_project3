package service;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.AdminDAO;

public class MemberDeleteService {

	public boolean deleteNoticeArticle(String member_code) {
		boolean isDeleteSuccess = false;
		Connection con = getConnection();
		
		AdminDAO adminDAO = AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
		int deleteCount = adminDAO.deleteMemberArticle(member_code);
		
		if(deleteCount > 0) {
			commit(con);
			isDeleteSuccess = true;
		} else {
			rollback(con);
		}
		
		close(con);
		return isDeleteSuccess;
	}

}
