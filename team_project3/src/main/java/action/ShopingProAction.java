package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.SellerShopingService;
import vo.ActionForward;
import vo.MemberBean;
import vo.SellerDTO;

public class ShopingProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ShopingProAction - 쇼핑카트 작업 ");
		ActionForward forward = null;
		
		String sell_member_code = request.getParameter("sell_member_code");
		String sell_brand = request.getParameter("sell_brand");
		int sell_list_num = Integer.parseInt(request.getParameter("sell_list_num"));
		System.out.println(sell_list_num);
		System.out.println(sell_brand);
		System.out.println(sell_member_code);
		
		SellerShopingService service = new SellerShopingService();
		SellerDTO sellerDTO = new SellerDTO();
		MemberBean memberBean = new MemberBean();
		
		sellerDTO = service.getShoping(sell_list_num);
		memberBean = service.getMemberShop(sell_member_code);
//		memberBean = service.getMember(sell_list_num, sell_brand);
		request.setAttribute("sellerDTO", sellerDTO);
		
		forward = new ActionForward();
		forward.setPath("ShopingForm.pr");
		forward.setRedirect(false);
		
		
		return forward;
	}

}
