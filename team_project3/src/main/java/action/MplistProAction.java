package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.productListProService;
import vo.ActionForward;
import vo.PageInfo;
import vo.SellerProductDTO;

public class MplistProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
			ActionForward forward = null;

			int pageNum = 1; // 현재 페이지 번호
			int listLimit = 12; // 한 페이지 당 표시할 게시물 목록 갯수

			int pageLimit = 12; // 한 페이지 당 표시할 페이지 목록 갯수

			// URL 로 전달받은 파라미터 중 "page" 파라미터가 있을 경우 해당 값을 pageNum 값으로 저장
			if (request.getParameter("page") != null) {
				pageNum = Integer.parseInt(request.getParameter("page"));
			}

			productListProService service = new productListProService();
			int listCount = service.getListCount();

			ArrayList<SellerProductDTO> mainarticleList = service.getMainArticleList(pageNum, listLimit);  //상품 갯수(12)한페이지에 뿌리는 작업
			ArrayList<SellerProductDTO> likearticleList = service.getLikeArticleList(pageNum, listLimit);
			// 페이징 처리를 위한 계산 작업
			// java.lang.Math 클래스의 ceil() 메서드를 사용하여 반올림 가능
			int maxPage = (int) Math.ceil((double) listCount / listLimit);

			// 현재 페이지에서 보여줄 시작 페이지 번호(1, 11, 21 등의 시작 번호) 계산
			int startPage = ((pageNum-1) /listLimit) * listLimit + 1;

			// 현재 페이지에서 보여줄 끝 페이지 번호(10, 20, 30 등의 끝 번호) 계산
			int endPage = startPage + pageLimit - 3;

			// 만약, 끝 페이지(endPage)가 현재 페이지에서 표시할 총 페이지 수(maxPage)보다 클 경우 - 끝 페이지 번호를 총 페이지 수로
			// 대체
			if (endPage > maxPage) {
				endPage = maxPage;
			}

			// 페이징 처리 정보를 PageInfo 객체에 저장
			PageInfo pageInfo = new PageInfo(pageNum, maxPage, startPage, endPage, listCount);

			// 뷰페이지(jsp)로 객체 전달을 위해 request 객체의 setAttribute() 메서드를 호출하여 객체 저장
			request.setAttribute("pageInfo", pageInfo); // 페이징 처리 정보 객체
			request.setAttribute("mainarticleList", mainarticleList); // 게시물 목록 객체
			request.setAttribute("likearticleList", likearticleList);

			forward = new ActionForward();
			forward.setPath("./MainPage/first_page/index.jsp");
			forward.setRedirect(false); // Dispatcher 방식(생략 가능)

			return forward;
	}

}
