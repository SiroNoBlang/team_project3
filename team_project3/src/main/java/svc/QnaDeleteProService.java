package svc;

import static db.JdbcUtil.*;
import java.sql.Connection;

import dao.AdminDAO;


public class QnaDeleteProService {

	public boolean deleteQnaArticle(int qna_num) {
		boolean isDeleteSuccess = false;
		Connection con = getConnection();
		
		AdminDAO adminDAO = AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
		int deleteCount = adminDAO.deleteQnaArticle(qna_num);
		
		if(deleteCount > 0) {
			commit(con);
			isDeleteSuccess = true;
		} else {
			rollback(con);
		}
		
		close(con);
		return isDeleteSuccess;
	}

	public boolean isArticleWriter(int qna_num, String qna_delete) {

		boolean isArticleWriter = false;
		Connection con = getConnection();
		
		AdminDAO adminDAO = AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
		isArticleWriter = adminDAO.isArticleWriter(qna_num, qna_delete);
		
		close(con);
		
		return isArticleWriter;
	}
	
}
