package action;

import java.util.ArrayList;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MemberManagementListService;
import vo.ActionForward;
import vo.MemberBean;
import vo.PageInfo;

public class MemberManagementListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		// 페이징 처리를 위한 변수 선언 _이효민 06.12 수정
		int pageNum = 1; // 현재 페이지 번호
		int listLimit = 10; // 한 페이지 당 표시할 게시물 목록 갯수
		int pageLimit = 10; // 한 페이지 당 표시할 페이지 목록 갯수
		
		// URL 로 전달받은 파라미터 중 "page" 파라미터가 있을 경우 해당 값을 pageNum 값으로 저장
		if(request.getParameter("page") != null) {
			pageNum = Integer.parseInt(request.getParameter("page"));
		}
		
		// 해당 개시물의 총 수 가져오기 _이효민 06.12 수정
		MemberManagementListService service = new MemberManagementListService();
		int listCount = service.getListCount();
		
		// 게시물 목록 담아오기 _이효민 06.12 수정
		ArrayList<MemberBean> memberManagementList = service.getMemberManagementList(pageNum, listLimit);
		
		// 상태에 따른 회원 수 정보 _이효민 06.12 수정
		MemberBean bean = new MemberBean();
		bean = service.getStatusCount();
		
		int maxPage = (int)Math.ceil((double)listCount / listLimit);
		int startPage = ((int)((double)pageNum / pageLimit + 0.9) - 1) * pageLimit + 1;
		int endPage = startPage + pageLimit - 1;

		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		// 페이징처리에 쓰기 위한 값 저장 _이효민 06.12 수정
		PageInfo pageInfo = new PageInfo(pageNum, maxPage, startPage, endPage, listCount);
		
		request.setAttribute("pageInfo", pageInfo); // 페이징 처리 정보 객체
		request.setAttribute("bean", bean);
		request.setAttribute("memberManagementList", memberManagementList); // 게시물 목록 객체
		
		forward = new ActionForward();
		forward.setPath("AdminPage/member_management/memberManagement.jsp");
		forward.setRedirect(false); 
		
		return forward;
	}

}
