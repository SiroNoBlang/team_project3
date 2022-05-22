package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.AdminDAO;
import vo.EventBean;
import vo.EventImgFileBean;
import vo.NoticeBean;
import vo.NoticeImgFileBean;

public class CommunityModifyProService {

	
	// 공지사항 수정 작업을 요청하는 noticeModifyArticle() 메서드 정의
	public boolean noticeModifyArticle(NoticeBean notice, ArrayList<NoticeImgFileBean> noticeImgList) {
		System.out.println("CommunityModifyProService - noticeModifyArticle");
		boolean isNoticeModifySuccess = false;
		
		Connection con = getConnection();
		AdminDAO adminDAO = AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
//				
//		int updateCount = adminDAO.updateNoticeArticle(notice,noticeImgList);
//		
//		if(updateCount > 0) {
//			commit(con);
//			isNoticeModifySuccess = true;
//		} else { // 작업 실패 시
//			rollback(con);
//		}
//		close(con);
//		
		return isNoticeModifySuccess;
	}

	// 이벤트 수정 작업을 요청하는 eventModifyArticle() 메서드 정의
	public boolean eventModifyArticle(EventBean event, ArrayList<EventImgFileBean> eventImgList) {
		System.out.println("CommunityModifyProService - eventModifyArticle");

		boolean isEventModifySuccess = false;
		
		Connection con = getConnection();
		AdminDAO adminDAO = AdminDAO.getInstance();
		
		adminDAO.setConnection(con);
		
//		int updateCount = adminDAO.updateEventArticle(event,eventImgList);
//		
//		if(updateCount > 0) {
//			commit(con);
//			isEventModifySuccess = true;
//		} else { // 작업 실패 시
//			rollback(con);
//		}
//		close(con);
//		
		return isEventModifySuccess;
	}

}
