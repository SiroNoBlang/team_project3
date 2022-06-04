package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.SellRecentPostAddressService;
import vo.ActionForward;
import vo.MemberBean;
import vo.SellerAddress;

public class SellRecentPostAddressAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("SellRecentPostAddressAction -배송지 주소 찾기");
		ActionForward forward = null;
		
		String sell_member_code =request.getParameter("member_code");
		SellRecentPostAddressService service = new SellRecentPostAddressService();
		//MemberBean memberBean = new MemberBean();
		SellerAddress address = new SellerAddress();
		ArrayList< SellerAddress> addressArr = new ArrayList<SellerAddress>();
		addressArr =service.findAddress(sell_member_code);
		//System.out.println(addressArr);
		request.setAttribute("arrAddressDTO", addressArr);		//배송지 저장 데이터
		
		forward = new ActionForward();
		forward.setPath("SellRecentPostAddress.pr");
		forward.setRedirect(false);
		
		
		
		
		
		return forward;
	}

}
