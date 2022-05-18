package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.AdminDAO;
import vo.EventBean;
import vo.EventImgFileBean;
import vo.NoticeBean;
import vo.NoticeImgFileBean;

public class CommunityBoardWriteProService {

	public boolean noticeRegistArticle(NoticeBean notice, NoticeImgFileBean noticeImg) {  //공지사항 게시물 등록
		System.out.println("CommunityBoardWriteProService - noticeRegistArticle()");
		boolean isWriteSuccess = false;
		
		
		Connection con = getConnection();
		AdminDAO adminDAO = AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
				
		int insertCount = adminDAO.insertNoticeArticle(notice,noticeImg);
		
		if(insertCount > 0) {
			commit(con);
			isWriteSuccess = true;
		} else { // 작업 실패 시
			rollback(con);
		}
		close(con);
		
		return isWriteSuccess;
	}

	public boolean eventRegistArticle(EventBean event, EventImgFileBean eventImg) { //이벤트 게시물 등록
		System.out.println("CommunityBoardWriteProService - eventRegistArticle()");
		boolean isWriteSuccess = false;
		
		Connection con = getConnection();
		AdminDAO adminDAO = AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
		int insertCount = adminDAO.insertEventArticle(event,eventImg);
		
		if(insertCount > 0) {
			commit(con);
			isWriteSuccess = true;
		} else { // 작업 실패 시
			rollback(con);
		}
		close(con);
		
		return isWriteSuccess;
		
	}

	
	
	
}
