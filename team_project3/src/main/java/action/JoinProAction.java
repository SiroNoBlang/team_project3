package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.JoinProService;
import vo.ActionForward;
import vo.MemberBean;

public class JoinProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		System.out.println("JoinProAction");
		ActionForward forward = null;
		
		
		MemberBean memberBean = new MemberBean();
		memberBean.setMember_nickname(request.getParameter("member_nickname"));
		memberBean.setMember_id(request.getParameter("member_id"));
		memberBean.setMember_passwd(request.getParameter("member_passwd"));
		memberBean.setMember_email(request.getParameter("member_email1") + request.getParameter("member_email2"));
		memberBean.setMember_info_gender(request.getParameter("member_info_gender"));
		memberBean.setMember_info_age(request.getParameter("member_info_age"));
		memberBean.setMember_info_grade_code(request.getParameter("member_info_grade_code"));
		memberBean.setMember_info_detail_like_style(request.getParameter("member_info_detail_like_style"));
		memberBean.setMember_info_detail_like_brand(request.getParameter("member_info_detail_like_brand"));
		memberBean.setMember_info_detail_like_category(request.getParameter("member_info_detail_like_category"));
		memberBean.setAgreement_name(request.getParameter("agree1"));
		memberBean.setAgreement_content(request.getParameter("agreement_content"));
		
//		System.out.println(memberBean);
		
		JoinProService service = new JoinProService();
		boolean isJoinsuccess = service.joinSuccess(memberBean);
		
		if(isJoinsuccess) {
			forward = new ActionForward("./", true);
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원가입 실패!')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}

}
