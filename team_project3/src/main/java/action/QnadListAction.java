package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.QnaListService;
import vo.ActionForward;
import vo.PageInfo;
import vo.QnaBean;

public class QnadListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		System.out.println("QnadListAction");
		
		ActionForward forward = null;
		
		// 페이징 처리를 위한 변수 선언
		int pageNum = 1; // 현재 페이지 번호
		int listLimit = 10; // 한 페이지 당 표시할 게시물 목록 갯수
		int pageLimit = 10; // 한 페이지 당 표시할 페이지 목록 갯수
		
		
		if(request.getParameter("page") != null) {
			pageNum = Integer.parseInt(request.getParameter("page"));
		}
		
		//qna 총 게시물 수 조회
		QnaListService service = new QnaListService();
		String tableName = "qna";
		
		int listCount = service.getListCount(tableName);
		
		
		//게시물 목록 담아오기 
		ArrayList<QnaBean> qnaList = service.getQnaList(pageNum, listLimit);
		
		
		int maxPage = (int)Math.ceil((double)listCount / listLimit);
		int startPage = ((int)((double)pageNum / pageLimit + 0.9) - 1) * pageLimit + 1;
		int endPage = startPage + pageLimit - 1;

		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pageInfo = new PageInfo(pageNum, maxPage, startPage, endPage, listCount,listLimit);

		request.setAttribute("pageInfo", pageInfo); // 페이징 처리 정보 객체
		request.setAttribute("qnaList", qnaList); // 게시물 목록 객체
		
		forward = new ActionForward();
		forward.setPath("./AdminPage/qna/questionList.jsp");
		forward.setRedirect(false); 
		
		return forward;
	}

}
