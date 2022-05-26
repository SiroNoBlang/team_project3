package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.NoticeDeleteService;
import vo.ActionForward;

public class NoticeDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		System.out.println("NoticeDeleteAction");
		
		ActionForward forward = null;
		
		int notice_num = Integer.parseInt(request.getParameter("notice_num"));
		String pageNum = request.getParameter("page");
		
		NoticeDeleteService service = new NoticeDeleteService();
		boolean isDeleteSuccess = service.deleteNoticeArticle(notice_num);
			
		
		if(!isDeleteSuccess) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제 실패!')");
				out.println("history.back()");
				out.println("</script>");
			} else {
				forward = new ActionForward();
				forward.setPath("NoticeList.co?page=" + pageNum);
				forward.setRedirect(true);
			}
		
		return forward;

	
	
	
	
	}

}
