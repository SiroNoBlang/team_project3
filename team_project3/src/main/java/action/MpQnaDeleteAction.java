package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.QnaDeleteProService;
import vo.ActionForward;

public class MpQnaDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MpQnaDeleteAction");
		
		ActionForward forward = null;
		
		int qna_num = Integer.parseInt(request.getParameter("qna_num"));
		String pageNum = request.getParameter("page");
		String qna_delete = request.getParameter("qna_delete");
		
		QnaDeleteProService service = new QnaDeleteProService();
		boolean isArticleWriter = service.isArticleWriter(qna_num, qna_delete);
		
		
		if(!isArticleWriter) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제 권한이 없습니다!')");
			out.println("history.back()");
			out.println("</script>");
		} else { 
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
				forward.setPath("CommunityQna.ma?page=" + pageNum);
				forward.setRedirect(true);
			}
		}
		
		return forward;
	}
}
