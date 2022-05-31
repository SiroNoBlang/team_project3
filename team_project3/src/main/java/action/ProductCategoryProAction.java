package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.productSearchProService;
import vo.ActionForward;
import vo.PageInfo;
import vo.SellerProductDTO;

public class ProductCategoryProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ProductCategoryProAction- 카테고리 분류");
		ActionForward forward = null;
		
		int pageNum = 1; // 현재 페이지 번호
		int listLimit = 10; // 한 페이지 당 표시할 게시물 목록 갯수
		
		int pageLimit = 10; // 한 페이지 당 표시할 페이지 목록 갯수
		
		String Category = request.getParameter("Category");
		if(request.getParameter("page") != null) {
			pageNum = Integer.parseInt(request.getParameter("page"));
		}
		
	
		productSearchProService service = new productSearchProService();
		int listCount = service.getSearchListCount();
		
		ArrayList<SellerProductDTO> ProductList = service.getProductList(pageNum, listLimit,Category);

		
		int maxPage = (int)Math.ceil((double)listCount / listLimit);
		int startPage = ((int)((double)pageNum / pageLimit + 0.9) - 1) * pageLimit + 1;
		int endPage = startPage + pageLimit - 1;
		
		if(endPage > maxPage) {
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
