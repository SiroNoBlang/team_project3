package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MemberDetailService;
import svc.MemberUpdateService;
import vo.ActionForward;
import vo.MemberBean;

public class MemberUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;

		String member_code = request.getParameter("member_code");
		String pageNum = request.getParameter("page");
		String grade_name = request.getParameter("member_info_detail_like_category");
		
		MemberUpdateService service = new MemberUpdateService();
		boolean isMemberUpdate = service.getMemberUpdate(member_code, grade_name);
		
		if(isMemberUpdate) {
			request.setAttribute("member_code", member_code);
			forward = new ActionForward("MemberDetail.co?page=" + pageNum, false);
			
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
