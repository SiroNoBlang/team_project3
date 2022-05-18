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
//		System.out.println("MyPageAction 나오나?");
		
		ActionForward forward = null;
		
		
		
		String member_code = request.getParameter("member_code");
//		System.out.println("액션쪽 : "+member_id);
//		System.out.println("액션쪽 : " + member_code);
		
		MemberDetailService service = new MemberDetailService();
		MemberBean memberDetail = service.getMemberDetail(member_code);
		
		if(memberDetail != null) {
			request.setAttribute("memberDetail", memberDetail);
			forward = new ActionForward("mypage.jsp", false);
			
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
