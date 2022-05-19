package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.NoticeListService;
import vo.ActionForward;
import vo.NoticeBean;
import vo.PageInfo;

public class NoticeListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		System.out.println("NoticeListAction");
		
		ActionForward forward = null;
		
		// 페이징 처리를 위한 변수 선언
		int pageNum = 1; // 현재 페이지 번호
		int listLimit = 10; // 한 페이지 당 표시할 게시물 목록 갯수
		int pageLimit = 10; // 한 페이지 당 표시할 페이지 목록 갯수
		
		
		if(request.getParameter("page") != null) {
			pageNum = Integer.parseInt(request.getParameter("page"));
		}
		
		//공지사항 총 게시물 수 조회
		NoticeListService service = new NoticeListService();
		String tableName = "admin_notice";
		
		int listCount = service.getListCount(tableName);
		
		
		//게시물 목록 담아오기 
		ArrayList<NoticeBean> noticeList = service.getNoticeList(pageNum, listLimit);
		
		
		int maxPage = (int)Math.ceil((double)listCount / listLimit);
		int startPage = ((int)((double)pageNum / pageLimit + 0.9) - 1) * pageLimit + 1;
		int endPage = startPage + pageLimit - 1;

		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pageInfo = new PageInfo(pageNum, maxPage, startPage, endPage, listCount,listLimit);

		request.setAttribute("pageInfo", pageInfo); // 페이징 처리 정보 객체
		request.setAttribute("noticeList", noticeList); // 게시물 목록 객체
		
		forward = new ActionForward();
		forward.setPath("./AdminPage/notice/noticeList.jsp");
		forward.setRedirect(false); 
		
		return forward;
	}

}
