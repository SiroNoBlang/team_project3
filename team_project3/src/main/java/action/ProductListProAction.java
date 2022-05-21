package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.productListProService;
import vo.ActionForward;
import vo.PageInfo;
import vo.SellerDTO;

public class ProductListProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
				System.out.println("productListProAction- ArrayList<> 이미지뿌리기작업");
				ActionForward forward = null;
				
				int pageNum = 1; // 현재 페이지 번호
				int listLimit = 10; // 한 페이지 당 표시할 게시물 목록 갯수
				
				int pageLimit = 10; // 한 페이지 당 표시할 페이지 목록 갯수
				
				// URL 로 전달받은 파라미터 중 "page" 파라미터가 있을 경우 해당 값을 pageNum 값으로 저장
				if(request.getParameter("page") != null) {
					pageNum = Integer.parseInt(request.getParameter("page"));
				}
				
			
				productListProService service = new productListProService();
				int listCount = service.getListCount();
				
				ArrayList<SellerDTO> articleList = service.getArticleList(pageNum, listLimit);
				
				//-----------------------------------------------------------------------------
				// 페이징 처리를 위한 계산 작업
				// 1. 현재 페이지에서 표시할 전체 페이지 수 계산
				// => 총 게시물 수 / 페이지 당 표시할 게시물 수 + 0.9
				// => 주의! 총 게시물 수(int) / 페이지 당 표시할 게시물 수(int) 연산 시 double 타입 연산 필요
				// => 계산된 결과값을 다시 int 타입으로 변환 필요
				// int maxPage = (int)((double)listCount / listLimit + 0.9);

				// java.lang.Math 클래스의 ceil() 메서드를 사용하여 반올림 가능
				int maxPage = (int)Math.ceil((double)listCount / listLimit);

				// 2. 현재 페이지에서 보여줄 시작 페이지 번호(1, 11, 21 등의 시작 번호) 계산
				int startPage = ((int)((double)pageNum / pageLimit + 0.9) - 1) * pageLimit + 1;

				// 3. 현재 페이지에서 보여줄 끝 페이지 번호(10, 20, 30 등의 끝 번호) 계산
				int endPage = startPage + pageLimit - 1;

				// 4. 만약, 끝 페이지(endPage)가 현재 페이지에서 표시할 총 페이지 수(maxPage)보다 클 경우
				//    끝 페이지 번호를 총 페이지 수로 대체
				if(endPage > maxPage) {
					endPage = maxPage;
				}
				
				// 페이징 처리 정보를 PageInfo 객체에 저장
				PageInfo pageInfo = new PageInfo(pageNum, maxPage, startPage, endPage, listCount);

				// 뷰페이지(jsp)로 객체 전달을 위해 request 객체의 setAttribute() 메서드를 호출하여 객체 저장
				request.setAttribute("pageInfo", pageInfo); // 페이징 처리 정보 객체
				request.setAttribute("articleList", articleList); // 게시물 목록 객체
				
				// ActionForward 객체를 생성하여 포워딩 정보 저장
				// => board 폴더 내의 qna_board_list.jsp 페이지
				// => request 객체를 유지한 채 포워딩 해야하므로 Dispatcher 방식 포워딩
				forward = new ActionForward();
				forward.setPath("Product_list.pr");
				forward.setRedirect(false); // Dispatcher 방식(생략 가능)
				
		return forward;
	}

}
