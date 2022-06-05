package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.SellMemberGradeService;
import svc.SellRecentPostAddressService;
import vo.ActionForward;
import vo.MemberBean;
import vo.SellerAddress;

public class SellMemberGradeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("SellMemberGradeAction");
		ActionForward forward =null;
		String member_code = request.getParameter("sell_member_code");
		
		SellMemberGradeService service = new SellMemberGradeService();
		//MemberBean memberBean = new MemberBean();
		// 여기 addree 변수명은 있는데 사용처가 없습니다. 필요 없으면 삭제해주세요.
		MemberBean memberbean = new MemberBean();
		
		ArrayList< MemberBean> gradeArr = new ArrayList<MemberBean>();
		gradeArr =service.MemberGrade(member_code);
		//System.out.println(addressArr);
		request.setAttribute("gradeArr", gradeArr);		//배송지 저장 데이터
		
		forward = new ActionForward();
		forward.setPath("SellMemberGradeForm.pr");
		forward.setRedirect(false);
		
		return forward;
	}

}
