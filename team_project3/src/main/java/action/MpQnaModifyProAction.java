package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.QnaModifyProService;
import vo.ActionForward;
import vo.QnaBean;

public class MpQnaModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		System.out.println("MpQnaModifyProAction");
		ActionForward forward = null;
		
		int qna_num = Integer.parseInt(request.getParameter("qna_num"));
		String qna_title = request.getParameter("qna_title");
		String qna_nickname = request.getParameter("qna_nickname");
		String qna_date = request.getParameter("qna_date");
		String qna_content = request.getParameter("qna_content");
		
			
		QnaBean qna = new QnaBean();
		qna.setQna_num(qna_num);
		qna.setQna_title(qna_title);
		qna.setQna_nickname(qna_nickname);
		qna.setQna_write_date(qna_date);
		qna.setQna_content(qna_content);
		
//		System.out.println(qna);
		
		QnaModifyProService service = new QnaModifyProService();
		boolean isQnaModifySuccess = service.qnaModifyArticle(qna);
		
		if(!isQnaModifySuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정 실패!')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			forward = new ActionForward();
			forward.setPath("CommunityQna.ma?page=" + request.getParameter("page"));
			forward.setRedirect(true);
		}
		
		return forward;
	}

}
