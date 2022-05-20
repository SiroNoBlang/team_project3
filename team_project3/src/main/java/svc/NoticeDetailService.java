package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.AdminDAO;
import vo.NoticeBean;
import vo.NoticeImgFileBean;

public class NoticeDetailService {

	public NoticeBean getNoticeArticle(int notice_num) {

//		System.out.println("NoticeDetailService - getNoticeArticle()");
		
		NoticeBean noticeArticle = null;
		
		Connection con = getConnection();
		AdminDAO adminDAO = AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
		noticeArticle = adminDAO.selectNoticeArticle(notice_num);
		
		close(con);
		
		return noticeArticle;
	}

	public void increaseNoticeReadcount(int notice_num) {

		Connection con = getConnection(); 
		AdminDAO adminDAO = AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
		adminDAO.updateNoticeReadcount(notice_num);
		
		commit(con);
		
		close(con);
	}

	public 	ArrayList<NoticeImgFileBean> getNoticeImg(int notice_num) {
		
		ArrayList<NoticeImgFileBean> noticeImgFileList = null;
		
		Connection con = getConnection();
		AdminDAO adminDAO = AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
		noticeImgFileList = adminDAO.getNoticeImg(notice_num);
		
		close(con);
		
		return noticeImgFileList;
	}
	
}
