package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberDetailService;

import vo.ActionForward;
import vo.MemberBean;

public class MyPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		
		String member_code = request.getParameter("member_code");
		
		MemberDetailService service = new MemberDetailService();
		MemberBean memberDetail = service.getMemberDetail(member_code);
		
		if(memberDetail != null) {
			request.setAttribute("memberDetail", memberDetail);
			forward = new ActionForward("MainPage/my_page/about_mypage.jsp", false);
			
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
