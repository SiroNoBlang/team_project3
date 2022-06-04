package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.SendAdminService;
import vo.ActionForward;

public class SendAdminAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String code = request.getParameter("member_code");
		String email = request.getParameter("email");
		String nickname = request.getParameter("member_nickname");
		String msg = request.getParameter("msg");
		
		SendAdminService service = new SendAdminService();
		boolean isSendAdmin = service.isSendAdmin(email, msg, nickname, request);
		
		if(isSendAdmin) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('메일이 정상적으로 전송되었습니다!')");
//			out.println("location.href='Contact.pr?member_code=" + code + "'"); // 이걸보여줄까 고민중인거 뿐임.
			out.println("</script>");
			forward = new ActionForward("Contact.pr?member_code=" + code, true);
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('오류가 발생했습니다. 다시 작성 후 보내주세요!')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}

}
