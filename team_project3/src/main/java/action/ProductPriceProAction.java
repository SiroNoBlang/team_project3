package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.productSearchProService;
import vo.ActionForward;
import vo.PageInfo;
import vo.SellerProductDTO;

public class ProductPriceProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;		

		int pageNum = 1; // 현재 페이지 번호
		int listLimit = 12; // 한 페이지 당 표시할 게시물 목록 갯수

		int pageLimit = 12; // 한 페이지 당 표시할 페이지 목록 갯수
		
		
		int Price = Integer.parseInt(request.getParameter("sell_price"));
		
		int begin = 0;
		int last = 0;
		
		if(Price ==100000) {
			 begin = 0; 
			 last = 10;
		} else if (Price == 500000) { 
			 begin = 10; 
			 last = 50;
		} else if (Price == 1000000) { 
			 begin = 50; 
			 last = 100;
		} else if (Price == 5000000) {
			 begin = 100; 
			 last = 500;
		} else if (Price == 10000000) { 
			 begin = 500; 
			 last = 1000; 
		} else if (Price == 20000000) {
			begin = 1000;
			last = 9999;
		}
		
		
		if (request.getParameter("page") != null) {
			pageNum = Integer.parseInt(request.getParameter("page"));
		}

		productSearchProService service = new productSearchProService();
		int listCount = service.getSearchListCount();
		

		ArrayList<SellerProductDTO> ProductPriceList = service.getProductPriceList(pageNum, listLimit, begin, last);

		int maxPage = (int) Math.ceil((double) listCount / listLimit);
		int startPage = ((pageNum-1) /listLimit) * listLimit + 1;
		int endPage = startPage + pageLimit - 3;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		PageInfo pageInfo = new PageInfo(pageNum, maxPage, startPage, endPage, listCount);

		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("articleList", ProductPriceList);

		forward = new ActionForward();
		forward.setPath("Product_list.pr");
		forward.setRedirect(false); // Dispatcher 방식(생략 가능)

		return forward;
	}

}
