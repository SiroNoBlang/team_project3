package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.NoticeDetailService;
import vo.ActionForward;
import vo.NoticeBean;
import vo.NoticeImgFileBean;

public class NoticeModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		System.out.println("NoticeModifyFormAction");
		
		ActionForward forward = null;
		
		// request 객체를 통해 전달받은 파라미터 가져오기
		int notice_num = Integer.parseInt(request.getParameter("notice_num"));
//		System.out.println(notice_num);
		
		NoticeDetailService service = new NoticeDetailService();
		NoticeBean noticeArticle = service.getNoticeArticle(notice_num);
		ArrayList<NoticeImgFileBean> noticeImgFileList = service.getNoticeImg(notice_num);
		
		request.setAttribute("noticeArticle", noticeArticle);
		request.setAttribute("noticeImgFileList", noticeImgFileList);
		
		forward = new ActionForward();
		forward.setPath("./AdminPage/notice/noticeModify.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
