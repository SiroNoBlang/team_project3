package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.AdminDAO;
import vo.QnaBean;

public class QnaDetailService {

	public QnaBean getQnaArticle(int qna_num) {
//		System.out.println("QnaDetailService - getQnaArticle()");
		
		QnaBean qnaArticle = null;
		
		Connection con = getConnection();
		AdminDAO adminDAO = AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		qnaArticle = adminDAO.selectQnaArticle(qna_num);
		
		close(con);
		
		return qnaArticle;
	}

	public void increaseQnaReadcount(int qna_num) {
		
		Connection con = getConnection(); 
		AdminDAO adminDAO = AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
		adminDAO.updateQnaReadcount(qna_num);
		
		commit(con);
		
		close(con);
		
	}

}
