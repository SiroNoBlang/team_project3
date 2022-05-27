package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.EventDeleteService;
import svc.NoticeDeleteService;
import vo.ActionForward;

public class EventDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		System.out.println("EventDeleteAction");
		
		ActionForward forward = null;
		
		int event_num = Integer.parseInt(request.getParameter("event_num"));
		String pageNum = request.getParameter("page");
		
		EventDeleteService service = new EventDeleteService();
		boolean isDeleteSuccess = service.deleteEventArticle(event_num);
			
		
		if(!isDeleteSuccess) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제 실패!')");
				out.println("history.back()");
				out.println("</script>");
			} else {
				forward = new ActionForward();
				forward.setPath("EventList.co?page=" + pageNum);
				forward.setRedirect(true);
			}
		
		return forward;
	}

}
