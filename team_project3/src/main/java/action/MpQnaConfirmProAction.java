package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.QnaDeleteProService;
import vo.ActionForward;

public class MpQnaConfirmProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		System.out.println("MpQnaConfirmProAction");
		
		ActionForward forward = null;
		
		int qna_num = Integer.parseInt(request.getParameter("qna_num"));
		String pageNum = request.getParameter("page");
		String qna_confirm = request.getParameter("qna_confirm");
		
		QnaDeleteProService service = new QnaDeleteProService();
		boolean isArticleWriter = service.isArticleWriter(qna_num, qna_confirm);
		
		if(!isArticleWriter) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('게시물 확인 권한이 없습니다!')");
			out.println("history.back()");
			out.println("</script>");
		} else{
			forward = new ActionForward();
			forward.setPath("CommunityQnaModifyForm.ma?page=" + pageNum + "&qna_num="+qna_num);
			forward.setRedirect(true);
		}
	
		return forward;
	}

}
