package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.AdminDAO;
import vo.QnaBean;

public class QnaReplyProService {

	public boolean replyQnaArticle(QnaBean qnaArticle) {
		boolean isQnaReplySuccess = false;
		Connection con = getConnection();
		
		AdminDAO adminDAO = AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
		int insertCount = adminDAO.insertQnaReplyArticle(qnaArticle);
		
		if(insertCount > 0) {
			commit(con);
			isQnaReplySuccess = true;
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isQnaReplySuccess;
	}

}
