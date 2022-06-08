package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.SellerDetailService;
import vo.ActionForward;
import vo.SellerDTO;
import vo.SellerProductDTO;
import vo.SellerimgDTO;

public class ProductDetailProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward =null;
		String pageNum = request.getParameter("page");
		
		String sell_brand = request.getParameter("sell_brand");
		int sell_num = Integer.parseInt(request.getParameter("sell_num"));
		
		SellerDetailService service = new SellerDetailService();
		SellerProductDTO sellerdto = new SellerProductDTO(); 
		
		sellerdto = service.getArticle(sell_num);   //상세정보를 위한 sellerdto 객체 꺼내오기     <request.getparameter sell_list_num 꺼내지는지 확인하기>
		
		ArrayList<SellerProductDTO> Relationdto = service.getProductRe(sell_brand,sell_num);
		ArrayList<SellerimgDTO>  Sellerdetailimg = service.getimgArticle(sell_num);
		// 조회수 증가 작업 요청(단, 게시물 조회 성공 시에만 수행)
		
		if(sellerdto != null) {
			// BoardDetailService 객체의 increaseReadcount() 메서드 호출
			service.increaseReadcount(sell_num);
		}

		request.setAttribute("sellerdto", sellerdto);
		request.setAttribute("Relationdto", Relationdto);
		request.setAttribute("Sellerdetailimg", Sellerdetailimg);
		
		forward = new ActionForward();
		forward.setPath("ProductDetailForm.pr");
		forward.setRedirect(false);
		
		return forward;
	
	}

}
