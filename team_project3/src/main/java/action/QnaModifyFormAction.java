package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.QnaDetailService;
import vo.ActionForward;
import vo.QnaBean;

public class QnaModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//System.out.println("QnaModifyFormAction");
		
		ActionForward forward = null;
		
		int qna_num = Integer.parseInt(request.getParameter("qna_num"));
		
		
		QnaDetailService service = new QnaDetailService();
		QnaBean qnaArticle = service.getQnaArticle(qna_num);
		
		request.setAttribute("qnaArticle", qnaArticle);
		
		forward = new ActionForward();
		forward.setPath("AdminPage/qna/qnaModify.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
