package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.NoticeDetailService;
import vo.ActionForward;
import vo.NoticeBean;
import vo.NoticeImgFileBean;

public class NoticeBoardDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("NoticeBoardDetailAction");
		ActionForward forward = null;
		
		int admin_notice_num = Integer.parseInt(request.getParameter("admin_notice_num"));
		
		NoticeDetailService service = new NoticeDetailService();
		NoticeBean noticeArticle = service.getNoticeArticle(admin_notice_num);
		
		NoticeImgFileBean noticeImgFile = service.getNoticeImg(admin_notice_num);
		
		
		if(noticeArticle != null) {
			System.out.println("조회수 증가");
			service.increaseReadcount(admin_notice_num);
		}
		
		request.setAttribute("noticeArticle", noticeArticle);
		request.setAttribute("noticeImgFile", noticeImgFile);
		
		forward = new ActionForward();
		forward.setPath("AdminPage/noticeView.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
