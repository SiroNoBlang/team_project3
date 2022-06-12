package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MemberManagementUpdateService;
import vo.ActionForward;

public class ClassificationUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;

		String member_code = request.getParameter("member_code");
		String member_status = request.getParameter("member_status");
		String reason = request.getParameter("reason");
		String pageNum = request.getParameter("page");
		String value = request.getParameter("value");
		
		// 해당 멤버의 상태와 상태에 대한 이유 변경의 위한 getMemberUpdate() 작성 _이효민 06.12 확인
		MemberManagementUpdateService service = new MemberManagementUpdateService();
		boolean isMemberUpdate = service.getMemberUpdate(member_code, member_status, reason);
		
		if(isMemberUpdate) {
			request.setAttribute("member_code", member_code);
			value = java.net.URLEncoder.encode(value, "UTF-8");
			forward = new ActionForward("ClassificationDetail.co?member_code=" + member_code + "&page=" + pageNum + "&value=" + value, true);
			
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
