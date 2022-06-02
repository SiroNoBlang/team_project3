package action;

import java.io.PrintWriter;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MemberManagementUpdateService;
import svc.MemberUpdateService;
import vo.ActionForward;

public class MemberUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;

		String member_code = request.getParameter("member_code");
		String member_status = request.getParameter("member_status");
		String reason = request.getParameter("reason");
		String pageNum = request.getParameter("page");
		
		MemberManagementUpdateService service = new MemberManagementUpdateService();
		boolean isMemberUpdate = service.getMemberUpdate(member_code, member_status, reason);
		if(isMemberUpdate) {
			request.setAttribute("member_code", member_code);
			forward = new ActionForward("MemberDetail.co?member_code=" + member_code +"&page=" + pageNum, true);
			
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('이게 왜 뜸?')");
			out.println("history.back()");
			out.println("</script>");
		}
		return forward;
	}

}
