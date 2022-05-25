package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.NoticeDetailService;
import svc.ProductConfirmDetailService;
import vo.ActionForward;
import vo.NoticeBean;
import vo.NoticeImgFileBean;
import vo.SellerDTO;

public class ProductConfirmDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		System.out.println("ProductConfirmDetailAction");
		ActionForward forward = null;
		
		int sell_num = Integer.parseInt(request.getParameter("sell_num"));
		
		ProductConfirmDetailService service = new ProductConfirmDetailService();
		SellerDTO confirmArticle = service.getProductConfirmArticle(sell_num);
		
		ArrayList<SellerDTO> confirmImgFileList = service.getProductConfirmImg(sell_num);
		
		
		request.setAttribute("confirmArticle", confirmArticle);
		request.setAttribute("confirmImgFileList", confirmImgFileList);
		
		forward = new ActionForward();
		forward.setPath("AdminPage/confirm/productConfirmView.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
