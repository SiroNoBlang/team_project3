package action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.QnaDetailService;
import vo.ActionForward;
import vo.QnaBean;

public class QnaDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		System.out.println("QnaDetailAction");
		ActionForward forward = null;
		
		int qna_num = Integer.parseInt(request.getParameter("qna_num"));
		
		QnaDetailService service = new QnaDetailService();
		QnaBean qnaArticle = service.getQnaArticle(qna_num);
		
		if(qnaArticle != null) {
//			System.out.println("조회수 증가");
			service.increaseQnaReadcount(qna_num);
		}
		
		request.setAttribute("qnaArticle", qnaArticle);
		
		forward = new ActionForward();
		forward.setPath("AdminPage/qna/qnaView.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
