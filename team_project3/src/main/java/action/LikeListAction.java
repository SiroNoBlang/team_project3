package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.LikeListService;
import vo.ActionForward;
import vo.LikeListBean;
import vo.MemberBean;
import vo.PageInfo;
import vo.SellerDTO;

public class LikeListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String member_code = request.getParameter("member_code");
		// 페이징 처리를 위한 변수 선언
		int pageNum = 1; //현재 페이지 번호
		int listLimit = 10; //한 페이지 당 표시할 게시물 목록 갯수
		int pageLimit = 10;
		
		if(request.getParameter("page") != null) {
			pageNum = Integer.parseInt(request.getParameter("page"));
		}
		
		LikeListService service = new LikeListService();
		int listCount = service.getListCount(member_code);
		
		//==================================================================================
		
		ArrayList<LikeListBean> articleList = service.getArticleList(pageNum, listLimit, member_code);
		
		int maxPage = (int)Math.ceil((double)listCount / listLimit);
		int startPage = ((int)((double)pageNum / pageLimit + 0.9) - 1) * pageLimit + 1;
		int endPage = startPage + pageLimit - 1;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pageInfo = new PageInfo(pageNum, maxPage, startPage, endPage, listCount);
//
		HttpSession session = request.getSession(); // articleList 객체 생성 
		session.setAttribute("articleList", articleList);
		
		request.setAttribute("pageInfo", pageInfo); // 페이징 처리 정보 객체
		request.setAttribute("articleList", articleList); //게시물 목록 객체
		
		forward = new ActionForward("./MainPage/my_page/like_list.jsp", false);
		
		return forward;
	}

}
