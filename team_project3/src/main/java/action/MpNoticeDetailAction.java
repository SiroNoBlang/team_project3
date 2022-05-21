package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.NoticeDetailService;
import vo.ActionForward;
import vo.NoticeBean;
import vo.NoticeImgFileBean;

public class MpNoticeDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		System.out.println("MpNoticeDetailAction");
		ActionForward forward = null;
		
		int notice_num = Integer.parseInt(request.getParameter("notice_num"));
		
		NoticeDetailService service = new NoticeDetailService();
		NoticeBean noticeArticle = service.getNoticeArticle(notice_num);
		
		ArrayList<NoticeImgFileBean> noticeImgFileList = service.getNoticeImg(notice_num);
		
		
		if(noticeArticle != null) {
//			System.out.println("조회수 증가");
			service.increaseNoticeReadcount(notice_num);
		}
		
		request.setAttribute("noticeArticle", noticeArticle);
		request.setAttribute("noticeImgFileList", noticeImgFileList);
		
		forward = new ActionForward();
		forward.setPath("MainPage/community/communityNoticeView.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
