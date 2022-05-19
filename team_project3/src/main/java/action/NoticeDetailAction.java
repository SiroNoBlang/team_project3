package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.NoticeDetailService;
import vo.ActionForward;
import vo.NoticeBean;
import vo.NoticeImgFileBean;

public class NoticeDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		System.out.println("NoticeDetailAction");
		ActionForward forward = null;
		
		int admin_notice_num = Integer.parseInt(request.getParameter("admin_notice_num"));
		
		NoticeDetailService service = new NoticeDetailService();
		NoticeBean noticeArticle = service.getNoticeArticle(admin_notice_num);
		
		ArrayList<NoticeImgFileBean> noticeImgFileList = service.getNoticeImg(admin_notice_num);
		
		
		if(noticeArticle != null) {
//			System.out.println("조회수 증가");
			service.increaseReadcount(admin_notice_num);
		}
		
		request.setAttribute("noticeArticle", noticeArticle);
		request.setAttribute("noticeImgFileList", noticeImgFileList);
		
		forward = new ActionForward();
		forward.setPath("AdminPage/notice/noticeView.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
