package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MemberDetailService;
import vo.ActionForward;
import vo.MemberBean;

public class ClassificationDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String member_code = request.getParameter("member_code");
		String pageNum = request.getParameter("page");
		String value = request.getParameter("value");
		
		// 해당 멤버의 상세정보 가져오기
		MemberDetailService service = new MemberDetailService();
		MemberBean memberDetail = service.getMemberDetail(member_code);
		
		if(memberDetail != null) {
			request.setAttribute("memberDetail", memberDetail);
			request.setAttribute("member_code", member_code);
			forward = new ActionForward("AdminPage/member_management/classification_detail.jsp?page=" + pageNum + "&value=" + value, false);
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
