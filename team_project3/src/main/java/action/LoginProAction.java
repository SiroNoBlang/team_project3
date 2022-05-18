package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.LoginProService;
import vo.ActionForward;
import vo.MemberBean;

public class LoginProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		System.out.println("LoginProAction");
		
		ActionForward forward = null;
		
		String member_id = request.getParameter("member_id");
		String member_passwd = request.getParameter("member_passwd");
		
		
		LoginProService service = new LoginProService();
		MemberBean isLogin = service.isLogin(member_id, member_passwd);
		
		if(isLogin != null) {
			if(isLogin.getGrade_name().equals("Admin")) {
				HttpSession session = request.getSession();
				session.setAttribute("sCode", isLogin.getMember_code());
				session.setAttribute("sNickname", isLogin.getMember_nickname());
				
				forward = new ActionForward();
				forward.setPath("MemberManagement.co");
				forward.setRedirect(true);
				
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("sCode", isLogin.getMember_code());
				session.setAttribute("sNickname", isLogin.getMember_nickname());
				forward = new ActionForward();
				forward.setPath("MainPage.pr");
				forward.setRedirect(true);
			}
			
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인 실패!')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		
		return forward;
	}

}
