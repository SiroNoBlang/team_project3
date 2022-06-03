package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ClassificationDeleteService;
import svc.NoticeDeleteService;
import vo.ActionForward;

public class ClassificationDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String member_code = request.getParameter("member_code");
		String pageNum = request.getParameter("page");
		String value = request.getParameter("value");
		
		ClassificationDeleteService service = new ClassificationDeleteService();
		boolean isDeleteSuccess = service.deleteNoticeArticle(member_code);
			
		
		if(!isDeleteSuccess) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제 실패!')");
				out.println("history.back()");
				out.println("</script>");
			} else {
				forward = new ActionForward();
				forward.setPath("Classification.co?page=" + pageNum + "&value=" + value);
				forward.setRedirect(true);
			}
		
		return forward;
	
	}

}
