package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.AdminDAO;
import vo.QnaBean;

public class QnaWriteProService {

	public boolean registQnaArticle(QnaBean qna) {
		System.out.println("QnaWriteProService - registQnaArticle()");
		boolean isQnaWriteSuccess = false;
		
		Connection con = getConnection();
		AdminDAO adminDAO = AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
				
		int insertCount = adminDAO.insertQnaArticle(qna);
		
		if(insertCount > 0) {
			commit(con);
			isQnaWriteSuccess = true;
		} else { // 작업 실패 시
			rollback(con);
		}
		close(con);
		
		return isQnaWriteSuccess;
	}

}
