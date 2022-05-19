package action;

import java.io.PrintWriter;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MemberUpdateService;
import vo.ActionForward;

public class MemberUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;

		String member_code = request.getParameter("member_code");
		String member_status = request.getParameter("member_status");
		String pageNum = request.getParameter("page");
		
//		System.out.println(member_code + ", " + member_status); // 이미지 파일 있을때도 확인용이므로 잠시 킵해두겠습니다.
		
		MemberUpdateService service = new MemberUpdateService();
		boolean isMemberUpdate = service.getMemberUpdate(member_code, member_status);
		
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
