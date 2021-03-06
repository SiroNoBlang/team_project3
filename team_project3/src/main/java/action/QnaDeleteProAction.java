package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.QnaDeleteProService;
import vo.ActionForward;

public class QnaDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		System.out.println("QnaDeleteProAction");
		
		ActionForward forward = null;
		
		int qna_num = Integer.parseInt(request.getParameter("qna_num"));
		String pageNum = request.getParameter("page");
		
		QnaDeleteProService service = new QnaDeleteProService();
		boolean isDeleteSuccess = service.deleteQnaArticle(qna_num);
			
		
		if(!isDeleteSuccess) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제 실패!')");
				out.println("history.back()");
				out.println("</script>");
			} else {
				forward = new ActionForward();
				forward.setPath("QusetionList.co?page=" + pageNum);
				forward.setRedirect(true);
			}
		
		return forward;
	}

}
