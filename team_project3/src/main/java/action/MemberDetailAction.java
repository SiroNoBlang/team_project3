package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MemberDetailService;
import svc.MemberManagementDetailService;
import vo.ActionForward;
import vo.MemberBean;

public class MemberDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String member_code = request.getParameter("member_code");
		String pageNum = request.getParameter("page");
		
		MemberManagementDetailService service = new MemberManagementDetailService();
		MemberBean memberDetail = service.getMemberDetail(member_code);
		if(memberDetail != null) {
			request.setAttribute("memberDetail", memberDetail);
			request.setAttribute("member_code", member_code);
			forward = new ActionForward("AdminPage/member_management/member_detail.jsp?page=" + pageNum, false);
			
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
