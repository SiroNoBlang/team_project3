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
		String member_code = request.getParameter("member_code");
	
		SellMemberGradeService service = new SellMemberGradeService();
		//MemberBean memberBean = new MemberBean();
		// 여기 addree 변수명은 있는데 사용처가 없습니다. 필요 없으면 삭제해주세요.
		MemberBean memberbean = new MemberBean();
		
		ArrayList< MemberBean> gradeArr = new ArrayList<MemberBean>();  //모든 grade 테이블의 정보를 받아옴
		gradeArr =service.MemberGrade();
//		System.out.println("배열"+gradeArr);
		memberbean = service.memberHasGrade(member_code);  //회원의 등급정보
//		System.out.println("객체"+memberbean);
		
//		request.setAttribute("memberbean", memberbean);	
//		request.setAttribute("gradeArr", gradeArr);		
		
		request.setAttribute("gradeArr", gradeArr);		//등급별 정보 값가져오기
		request.setAttribute("memberbean", memberbean);	//기존 회원의 등급을 알아보기 위한 정보를 담은 객체
		forward = new ActionForward();
		forward.setPath("SellMemberGradeForm.pr");
		forward.setRedirect(false);
		
		return forward;
	}

}
