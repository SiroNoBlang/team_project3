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
		ActionForward forward = null;
		
		String sell_member_code =request.getParameter("member_code");
		SellRecentPostAddressService service = new SellRecentPostAddressService();
		//MemberBean memberBean = new MemberBean();
		// 여기 addree 변수명은 있는데 사용처가 없습니다. 필요 없으면 삭제해주세요.
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
