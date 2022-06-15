package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MemberSecessionService;
import svc.ModifyUpdateService;
import vo.ActionForward;
import vo.MemberBean;

public class MemberSecessionAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("회원탈퇴 액션");
		ActionForward forward = null;
		String member_code = request.getParameter("member_code");
		String member_service_log_status = request.getParameter("member_service_log_status");
		
		System.out.println("액션에서 멤버코드" + member_code);
		System.out.println("액션에서 로그인상태" + member_service_log_status);
		
		MemberBean member = new MemberBean();
		member.setMember_code(member_code);
		member.setMember_service_log_status(member_service_log_status);
		
		MemberSecessionService service = new MemberSecessionService();
		boolean getMemberSecession = service.getUpdateSuccess(member);
		
		if(!getMemberSecession) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('탈퇴 실패!')");
			out.println("history.back()");
			out.println("</script>");
			
		} else {
			forward = new ActionForward();
			forward.setPath("./");
			forward.setRedirect(true);
		}
		
		return forward;
	}

}
