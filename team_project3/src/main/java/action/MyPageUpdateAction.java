package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ModifyUpdateService;
import vo.ActionForward;
import vo.MemberBean;

public class MyPageUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		System.out.println("MyPageUpdateAction");
		ActionForward forward = null;
		
		
		String grade_code = request.getParameter("grade_code");
		String grade_name = request.getParameter("grade_name");
		String member_num = request.getParameter("member_num");
		String member_code = request.getParameter("member_code");
		String member_nickname = request.getParameter("member_nickname");
		String member_id = request.getParameter("member_id");
		String member_passwd = request.getParameter("member_passwd");
		String member_email1 = request.getParameter("member_email1");
		String member_info_code = request.getParameter("member_info_code");
		String member_info_name = request.getParameter("member_info_name");
		String member_info_gender = request.getParameter("member_info_gender");
		String member_info_phone = request.getParameter("member_info_phone");
		String member_info_age = request.getParameter("member_info_age");
		String member_info_post_code = request.getParameter("member_info_post_code");
		String member_info_address = request.getParameter("member_info_address");
		String member_info_address_detail = request.getParameter("member_info_address_detail");
		String member_info_ship_post_code = request.getParameter("member_info_ship_post_code");
		String member_info_ship_address = request.getParameter("member_info_ship_address");
		String member_info_ship_address_detail = request.getParameter("member_info_ship_address_detail");
		String member_info_grade_code = request.getParameter("member_info_grade_code");
		String member_info_detail_code = request.getParameter("member_info_detail_code");
		String member_info_detail_like_style = request.getParameter("member_info_detail_like_style");
		String member_info_detail_like_brand = request.getParameter("member_info_detail_like_brand");
		String member_info_detail_like_category = request.getParameter("member_info_detail_like_category");
		int member_info_detail_point = Integer.parseInt(request.getParameter("member_info_detail_point"));
		int member_info_detail_acc_money = Integer.parseInt(request.getParameter("member_info_detail_acc_money"));
		String member_service_log_code = request.getParameter("member_service_log_code");
		String member_service_log_status = request.getParameter("member_service_log_status");
		String member_service_log_join_date = request.getParameter("member_service_log_join_date");
		String member_service_log_passwd_change_date = request.getParameter("member_service_log_passwd_change_date");
		String member_service_log_grade_change_date = request.getParameter("member_service_log_grade_change_date");
		String member_service_log_login_date = request.getParameter("member_service_log_login_date");
		int member_service_log_order_count = Integer.parseInt(request.getParameter("member_service_log_order_count"));
		
		
		
		MemberBean member = new MemberBean();
		member.setGrade_code(grade_code);
		member.setGrade_name(grade_name);
		member.setMember_num(member_num);
		member.setMember_nickname(member_nickname);
		member.setMember_info_code(member_info_code);
		member.setMember_info_gender(member_info_gender);
		member.setMember_info_age(member_info_age);
		member.setMember_info_grade_code(member_info_grade_code);
		member.setMember_info_detail_code(member_info_detail_code);
		member.setMember_info_detail_like_style(member_info_detail_like_style);
		member.setMember_info_detail_like_brand(member_info_detail_like_brand);
		member.setMember_info_detail_like_category(member_info_detail_like_category);
		member.setMember_info_detail_point(member_info_detail_point);
		member.setMember_info_detail_acc_money(member_info_detail_acc_money);
		member.setMember_service_log_code(member_service_log_code);
		member.setMember_service_log_status(member_service_log_status);
		member.setMember_service_log_join_date(member_service_log_join_date);
		member.setMember_service_log_passwd_change_date(member_service_log_passwd_change_date);
		member.setMember_service_log_grade_change_date(member_service_log_grade_change_date);
		member.setMember_service_log_login_date(member_service_log_login_date);
		member.setMember_service_log_order_count(member_service_log_order_count);
		member.setMember_code(member_code);
		member.setMember_id(member_id);
		member.setMember_passwd(member_passwd);
		member.setMember_email(member_email1);
		member.setMember_info_name(member_info_name);
		member.setMember_info_phone(member_info_phone);
		member.setMember_info_post_code(member_info_post_code);
		member.setMember_info_address(member_info_address);
		member.setMember_info_address_detail(member_info_address_detail);
		member.setMember_info_ship_post_code(member_info_ship_post_code);
		member.setMember_info_ship_address(member_info_ship_address);
		member.setMember_info_ship_address_detail(member_info_ship_address_detail);
		
		
//		System.out.println("업데이트Action에서 =" + grade_code);
//		System.out.println("업데이트Action에서 = " + member);
		
		ModifyUpdateService service = new ModifyUpdateService();
		boolean getUpdateSuccess = service.getUpdateSuccess(member);
		
		
		if(!getUpdateSuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정 실패!')");
			out.println("history.back()");
			out.println("</script>");
			
		} else {
			forward = new ActionForward();
			forward.setPath("./index.jsp");
			forward.setRedirect(true);
		}
		
		return forward;
	}

}
