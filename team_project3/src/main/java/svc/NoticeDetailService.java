package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.AdminDAO;
import vo.NoticeBean;
import vo.NoticeImgFileBean;

public class NoticeDetailService {

	public NoticeBean getNoticeArticle(int admin_notice_num) {

		System.out.println("BoardDetailService - getNoticeArticle()");
		
		NoticeBean noticeArticle = null;
		
		Connection con = getConnection();
		AdminDAO adminDAO = AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
		noticeArticle = adminDAO.selectNoticeArticle(admin_notice_num);
		
		close(con);
		
		return noticeArticle;
	}

	public void increaseReadcount(int admin_notice_num) {

		Connection con = getConnection(); 
		AdminDAO adminDAO = AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
		adminDAO.updateReadcount(admin_notice_num);
		
		commit(con);
		
		close(con);
	}

	public NoticeImgFileBean getNoticeImg(int admin_notice_num) {
		
		NoticeImgFileBean noticeImgFile = null;
		
		Connection con = getConnection();
		AdminDAO adminDAO = AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
		noticeImgFile = adminDAO.getNoticeImg(admin_notice_num);
		
		close(con);
		
		return noticeImgFile;
	}
	
}
