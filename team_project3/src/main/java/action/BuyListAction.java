package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BuyListService;
import svc.ProductConfirmListService;
import vo.ActionForward;
import vo.BuyDTO;
import vo.PageInfo;
import vo.SellerDTO;
import vo.SellerProductDTO;

public class BuyListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BuyListAction");
		ActionForward forward = null;
		
		String code = request.getParameter("member_code");
		System.out.println("멤버 코드 " + code);
		
		// 페이징 처리를 위한 변수 선언
		int pageNum = 1; // 현재 페이지 번호
		int listLimit = 10; // 한 페이지 당 표시할 게시물 목록 갯수
		int pageLimit = 10; // 한 페이지 당 표시할 페이지 목록 갯수
		
		
		if(request.getParameter("page") != null) {
			pageNum = Integer.parseInt(request.getParameter("page"));
		}
		
		//총 구매목록 수 조회
		BuyListService service = new BuyListService();
		String tableName = "buy";
		
		int listCount = service.getListCount(tableName);
		
		
		//구매리스트 목록 담아오기 
		ArrayList<SellerProductDTO> buyList = service.getBuyList(pageNum, listLimit, code);
//		ArrayList<SellerProductDTO> sellList = service.getBuySellList(pageNum, listLimit, code);
		
		//구매리스트 목록에 필요한 사진
//		ArrayList<SellerProductDTO> buyImgFileList = service.getBuyListImg(code);
		
		int maxPage = (int)Math.ceil((double)listCount / listLimit);
		int startPage = ((int)((double)pageNum / pageLimit + 0.9) - 1) * pageLimit + 1;
		int endPage = startPage + pageLimit - 1;

		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pageInfo = new PageInfo(pageNum, maxPage, startPage, endPage, listCount,listLimit);
		
		request.setAttribute("pageInfo", pageInfo); 
		request.setAttribute("buyList", buyList); 
//		request.setAttribute("sellList", sellList);
//		request.setAttribute("buyImgFileList", buyImgFileList);
		
		forward = new ActionForward();
		forward.setPath("MainPage/my_page/buy_list.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
