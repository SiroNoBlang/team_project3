package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.productSearchProService;
import vo.ActionForward;
import vo.PageInfo;
import vo.SellerProductDTO;

public class ProductSearchProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;

		int pageNum = 1; // 현재 페이지 번호
		int listLimit = 12; // 한 페이지 당 표시할 게시물 목록 갯수

		int pageLimit = 12; // 한 페이지 당 표시할 페이지 목록 갯수

		String ProductSearch = request.getParameter("ProductSearch");
		if (request.getParameter("page") != null) {
			pageNum = Integer.parseInt(request.getParameter("page"));
		}

		productSearchProService service = new productSearchProService();
		int listCount = service.getSearchListCount();

		ArrayList<SellerProductDTO> ProductList = service.getProductList(pageNum, listLimit, ProductSearch);

		int maxPage = (int) Math.ceil((double) listCount / listLimit);
		int startPage = ((pageNum-1) /listLimit) * listLimit + 1;
		int endPage = startPage + pageLimit - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		PageInfo pageInfo = new PageInfo(pageNum, maxPage, startPage, endPage, listCount);

		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("articleList", ProductList);

		forward = new ActionForward();
		forward.setPath("Product_list.pr");
		forward.setRedirect(false); // Dispatcher 방식(생략 가능)

		return forward;
	}

}
