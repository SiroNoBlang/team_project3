package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import googleSMTPAuthenticator.MyMessageDigest;
import svc.LoginProService;
import vo.ActionForward;
import vo.MemberBean;

public class LoginProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//단방향 암호화된 비밀번호 
		ActionForward forward = null;
		
		String algorithm = "SHA-256"; // 암호화 코드(MessageDigest)의 길이가 256 비트인 암호화 알고리즘을 지정
		
		String passwd = request.getParameter("login_member_passwd");; // 평문

		MyMessageDigest mmd2 = new MyMessageDigest(algorithm, passwd);
		String result2 = mmd2.getHashedData(); // 입력받은 패스워드 암호화 결과 리턴받기
		
		String member_id = request.getParameter("login_member_id");
		
		LoginProService service = new LoginProService();
		MemberBean isLogin = service.isLogin(member_id, result2);
		
		if(isLogin != null) {
			if(isLogin.getGrade_name().equals("Admin")) {
				HttpSession session = request.getSession();
				session.setAttribute("sCode", isLogin.getMember_code());
				session.setAttribute("sNickname", isLogin.getMember_nickname());
				session.setAttribute("sGradeName", isLogin.getGrade_name());
				forward = new ActionForward();
				forward.setPath("MemberManagement.co");
				forward.setRedirect(true);
				
			} else if(isLogin.getMember_service_log_status().equals("정상")) {
				HttpSession session = request.getSession();
				session.setAttribute("sCode", isLogin.getMember_code());
				session.setAttribute("sNickname", isLogin.getMember_nickname());
				forward = new ActionForward();
				forward.setPath("MainPagelogin.pr");
				forward.setRedirect(true);
				
			} else if(isLogin.getMember_service_log_status().equals("정지")) {
				request.setAttribute("sLoginDate", isLogin.getMember_service_log_login_date());
				request.setAttribute("sReasonContent", isLogin.getReason_content());
				forward = new ActionForward();
				forward.setPath("Suspension.ma");
				forward.setRedirect(false);
				
			}else if(isLogin.getMember_service_log_status().equals("탈퇴")) {
				forward = new ActionForward();
				forward.setPath("Withdrawal.ma");
				forward.setRedirect(true);
				
			}
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인 실패!')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}

}


	
