package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.FindPasswdService;
import svc.LoginProService;
import vo.ActionForward;
import vo.MemberBean;

public class FindPasswdAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("FindPasswdAction");
		
		ActionForward forward = null;
		
		String id = request.getParameter("find_passwd_member_id");
		String email = request.getParameter("find_passwd_member_email");
		
		FindPasswdService service = new FindPasswdService();
		boolean isFindPasswd = service.isFindPasswd(id, email, request);
		
		if(isFindPasswd) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('이메일로 임시 비밀번호를 전송하였습니다!')");
			out.println("history.back()");
			out.println("</script>");
//			forward = new ActionForward("/HomePage/first_page/mail_pro.jsp", false);
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('계정이 없습니다!')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}

}
