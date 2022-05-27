package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.AdminDAO;

public class EventDeleteService {

	public boolean deleteEventArticle(int event_num) {
		boolean isDeleteSuccess = false;
		Connection con = getConnection();
		
		AdminDAO adminDAO = AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
		int deleteCount = adminDAO.deleteEventArticle(event_num);
		
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
