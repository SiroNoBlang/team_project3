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
		
		String val = "";
		String bnd = "";
		String ctgy = "";
		
		String [] style = request.getParameterValues("style");
		String [] brand = request.getParameterValues("brand");
		String [] category = request.getParameterValues("category");
		
		for(String val3 : style) {
			val += val3 + ",";
		}
		
		for(String b : brand) {
			bnd += b + ",";
		}
		
		for(String c : category) {
			ctgy += c + ",";
		}
		
		MemberBean memberBean = new MemberBean();
		memberBean.setMember_nickname(request.getParameter("member_nickname"));
		memberBean.setMember_id(request.getParameter("join_member_id"));
		memberBean.setMember_passwd(request.getParameter("join_member_passwd"));
		memberBean.setMember_email(request.getParameter("member_email1") + request.getParameter("member_email2"));
		memberBean.setMember_info_gender(request.getParameter("member_info_gender"));
		memberBean.setMember_info_age(request.getParameter("member_info_age"));
		memberBean.setMember_info_detail_like_style(val);
		memberBean.setMember_info_detail_like_brand(bnd);
		memberBean.setMember_info_detail_like_category(ctgy);
		
		System.out.println(val);
		System.out.println(bnd);
		System.out.println(ctgy);
		
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
