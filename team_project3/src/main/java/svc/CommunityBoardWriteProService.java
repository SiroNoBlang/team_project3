package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.AdminDAO;
import vo.EventBean;
import vo.EventImgFileBean;
import vo.NoticeBean;
import vo.NoticeImgFileBean;

public class CommunityBoardWriteProService {

	public boolean noticeRegistArticle(NoticeBean notice, ArrayList<NoticeImgFileBean>  noticeImgList) {  //공지사항 게시물 등록
//		System.out.println("CommunityBoardWriteProService - noticeRegistArticle()");
		boolean isNoticeWriteSuccess = false;
		
		
		Connection con = getConnection();
		AdminDAO adminDAO = AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
				
		int insertCount = adminDAO.insertNoticeArticle(notice,noticeImgList);
		
		if(insertCount > 0) {
			commit(con);
			isNoticeWriteSuccess = true;
		} else { // 작업 실패 시
			rollback(con);
		}
		close(con);
		
		return isNoticeWriteSuccess;
	}

	public boolean eventRegistArticle(EventBean event, ArrayList<EventImgFileBean> eventImgList) { //이벤트 게시물 등록
//		System.out.println("CommunityBoardWriteProService - eventRegistArticle()");
		boolean isEventWriteSuccess = false;
		
		Connection con = getConnection();
		AdminDAO adminDAO = AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
		int insertCount = adminDAO.insertEventArticle(event,eventImgList);
		
		if(insertCount > 0) {
			commit(con);
			isEventWriteSuccess = true;
		} else { // 작업 실패 시
			rollback(con);
		}
		close(con);
		
		return isEventWriteSuccess;
		
	}

	
	
	
}
