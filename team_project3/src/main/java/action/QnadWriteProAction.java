package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.QnaWriteProService;
import vo.ActionForward;
import vo.QnaBean;

public class QnadWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("QnadWriteProAction");
		
		ActionForward forward = null;
		request.setCharacterEncoding("UTF-8");

		
		QnaBean qna = new QnaBean();
		qna.setQna_title(request.getParameter("qna_title"));
		qna.setQna_nickname(request.getParameter("qna_nickname"));
		qna.setQna_content(request.getParameter("qna_content"));
		
		QnaWriteProService service = new QnaWriteProService();
		boolean isQnaWriteSuccess = service.registQnaArticle(qna);
		

		
		if(!isQnaWriteSuccess) { // 글쓰기 실패
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('QnA 글쓰기실패!')");
			out.println("history.back()");
			out.println("</script>");
		} else { // 글쓰기 성공
			forward = new ActionForward();
			forward.setPath("QnaList.co");
			forward.setRedirect(true);
		}
		
		return forward;
	}

}
