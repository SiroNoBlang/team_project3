package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.EventSearchListService;
import vo.ActionForward;
import vo.EventBean;
import vo.PageInfo;

public class MpEventSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		System.out.println("MpEventSearchAction");
		ActionForward forward = null;
		
		request.setCharacterEncoding("UTF-8");
		
		String search = request.getParameter("search");
		String searchType = request.getParameter("searchType");
		
		String tableName = "event";
		
//		System.out.println(search);
//		System.out.println(searchType);
//		
		//검색어에 해당하는 게시물 목록 갯수를 조회
		EventSearchListService service = new EventSearchListService();
		int listCount = service.selectSearchListCount(tableName,search, searchType);
		
		//검색어에 해당하는 게시물 목록 담아오기(검색어 페이징)
		int pageNum = 1; // 현재 페이지 번호
		int listLimit = 10; // 한 페이지 당 표시할 게시물 목록 갯수
		int pageLimit = 10; // 한 페이지 당 표시할 페이지 목록 갯수
		
		if(request.getParameter("page") != null) {
			pageNum = Integer.parseInt(request.getParameter("page"));
		}
		
		int maxPage = (int)Math.ceil((double)listCount / listLimit);
		int startPage = ((int)((double)pageNum / pageLimit + 0.9) - 1) * pageLimit + 1;
		int endPage = startPage + pageLimit - 1;

		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pageInfo = new PageInfo(pageNum, maxPage, startPage, endPage, listCount,listLimit);
		ArrayList<EventBean> eventSearchList = service.selectEventSearchList(pageNum, listLimit, search, searchType);
		
		
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("eventSearchList", eventSearchList); 
		
		
		forward = new ActionForward();
		forward.setPath("./MainPage/community/communityEventSearch.jsp");
		forward.setRedirect(false); 
		
		return forward;
	}

}
