package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.FindPasswdService;
import svc.SendEmailService;
import vo.ActionForward;

public class SendEmailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		
		String email = request.getParameter("member_email1") + request.getParameter("member_email2") ;
		
		SendEmailService service = new SendEmailService();
		boolean isSendEmail = service.isSendEmail(email, request);
		
		if(isSendEmail) {
			response.setContentType("text/html; charset=UTF-8");
			forward = new ActionForward("/JoinPro.ma", false);
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('중복된 이메일입니다!')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}

}
