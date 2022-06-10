package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import googleSMTPAuthenticator.MyMessageDigest;
import svc.ModifyUpdateService;
import vo.ActionForward;
import vo.MemberBean;

public class MyPageUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		String val = "";
		String bnd = "";
		String ctgy = "";
		
		String grade_name = request.getParameter("grade_name");
		String member_num = request.getParameter("member_num");
		String member_code = request.getParameter("member_code");
		String member_nickname = request.getParameter("member_nickname");
		String member_id = request.getParameter("member_id");
		String member_email1 = request.getParameter("member_email1");
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
		int member_info_detail_point = Integer.parseInt(request.getParameter("member_info_detail_point"));
		int member_info_detail_acc_money = Integer.parseInt(request.getParameter("member_info_detail_acc_money"));
		String member_service_log_status = request.getParameter("member_service_log_status");
		String member_service_log_join_date = request.getParameter("member_service_log_join_date");
		String member_service_log_passwd_change_date = request.getParameter("member_service_log_passwd_change_date");
		String member_service_log_login_date = request.getParameter("member_service_log_login_date");
		int member_service_log_order_count = Integer.parseInt(request.getParameter("member_service_log_order_count"));
		// 스타일,브랜드,카테고리 배열로 받아오기
		String [] style = request.getParameterValues("style");
		String [] brand = request.getParameterValues("brand");
		String [] category = request.getParameterValues("category");
		
		for(String a : style) {
			val += a + "/";
		}
		
		for(String b : brand) {
			bnd += b + "/";
		}
		
		for(String c : category) {
			ctgy += c + "/";
		}
		
		//============================================================================================================
		//비밀번호 암호화
			String algorithm = "SHA-256";
			
			String strPlainText = request.getParameter("member_passwd"); // 평문 암호
			
			
			// MyMessageDigest 객체 생성 시 생성자에 암호알고리즘명과 평문암호 전달하여 암호화 수행
			MyMessageDigest mmd = new MyMessageDigest(algorithm, strPlainText);
			
			// MyMessageDigest 객체의 getHashedData() 메서드를 호출하여 암호화 된 암호문을 리턴받아 출력
			String result = mmd.getHashedData();
			System.out.println("비밀번호 암호화 결과 : " + result);
		//============================================================================================================
		
		MemberBean member = new MemberBean();
		member.setGrade_name(grade_name);
		member.setMember_num(member_num);
		member.setMember_nickname(member_nickname);
		member.setMember_info_gender(member_info_gender);
		member.setMember_info_age(member_info_age);
		member.setMember_info_detail_like_style(val);
		member.setMember_info_detail_like_brand(bnd);
		member.setMember_info_detail_like_category(ctgy);
		member.setMember_info_detail_point(member_info_detail_point);
		member.setMember_info_detail_acc_money(member_info_detail_acc_money);
		member.setMember_service_log_status(member_service_log_status);
		member.setMember_service_log_join_date(member_service_log_join_date);
		member.setMember_service_log_passwd_change_date(member_service_log_passwd_change_date);
		member.setMember_service_log_login_date(member_service_log_login_date);
		member.setMember_service_log_order_count(member_service_log_order_count);
		member.setMember_code(member_code);
		member.setMember_id(member_id);
		member.setMember_passwd(result); // 암호화된 비밀번호 값
		member.setMember_email(member_email1);
		member.setMember_info_name(member_info_name);
		member.setMember_info_phone(member_info_phone);
		member.setMember_info_post_code(member_info_post_code);
		member.setMember_info_address(member_info_address);
		member.setMember_info_address_detail(member_info_address_detail);
		member.setMember_info_ship_post_code(member_info_ship_post_code);
		member.setMember_info_ship_address(member_info_ship_address);
		member.setMember_info_ship_address_detail(member_info_ship_address_detail);
		
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
			forward.setPath("Mypage.ma?member_code=" + member_code);
			forward.setRedirect(true);
		}
		
		return forward;
	}

}
