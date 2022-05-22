package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.AdminDAO;
import vo.QnaBean;

public class QnaModifyProService {

	public boolean qnaModifyArticle(QnaBean qna) {
	boolean isQnaModifySuccess = false;
		
		Connection con = getConnection();
		
		AdminDAO adminDAO = AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
		int updateCount = adminDAO.updateQnaArticle(qna);
		
		if(updateCount > 0) {
			commit(con);
			isQnaModifySuccess = true;
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isQnaModifySuccess;
	}
	

}
