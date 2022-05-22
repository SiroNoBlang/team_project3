package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.QnaReplyProService;
import vo.ActionForward;
import vo.QnaBean;

public class QnaReplyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("QnaReplyProAction");
		
		ActionForward forward = null;
		
		QnaBean qnaArticle = new QnaBean();
		qnaArticle.setQna_num(Integer.parseInt(request.getParameter("qna_num")));
		qnaArticle.setQna_nickname(request.getParameter("qna_nickname"));
		qnaArticle.setQna_title(request.getParameter("qna_title"));
		qnaArticle.setQna_content(request.getParameter("qna_content"));
		qnaArticle.setQna_re_ref(Integer.parseInt(request.getParameter("qna_re_ref")));
		qnaArticle.setQna_re_lev(Integer.parseInt(request.getParameter("qna_re_lev")));
		qnaArticle.setQna_re_seq(Integer.parseInt(request.getParameter("qna_re_seq")));
		
		
		
		QnaReplyProService service = new QnaReplyProService();
		boolean isQnaReplySuccess = service.replyQnaArticle(qnaArticle);
		
		if(!isQnaReplySuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('답글 등록 실패!')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			forward = new ActionForward();
			forward.setPath("QusetionList.co?page=" + request.getParameter("page"));
			forward.setRedirect(true); // Redirect 방식
		}
		
		return forward;
	}

}
