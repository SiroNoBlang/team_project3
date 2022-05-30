package action;

import java.io.PrintWriter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import googleSMTPAuthenticator.MyMessageDigest;
import googleSMTPAuthenticator.RSACipher;
import googleSMTPAuthenticator.RSAKeyGenerator;
import svc.LoginProService;
import vo.ActionForward;
import vo.MemberBean;

public class LoginProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("LoginProAction");
		
		
		//단방향 암호화된 비밀번호 
		ActionForward forward = null;
		
		String algorithm = "SHA-256"; // 암호화 코드(MessageDigest)의 길이가 256 비트인 암호화 알고리즘을 지정
		
		String passwd = request.getParameter("login_member_passwd");; // 평문

		MyMessageDigest mmd2 = new MyMessageDigest(algorithm, passwd);
		String result2 = mmd2.getHashedData(); // 입력받은 패스워드 암호화 결과 리턴받기
		
//		//================================================================================================
//		//아이디 암호화 시켜서 비교
//		//
//		/*
//		 * RSA 알고리즘
//		 * - 공개키 방식의 암호화 알고리즘(공개키와 개인키를 사용하여 암호화 및 복호화 수행)
//		 * - 인수분해를 기반으로 암호 생성하는 방식
//		 * - Rivest, Shamir, Adleman 이라는 사람의 성을 한글자씩 따서 RSA 라고 지음
//		 * ---------------------------------------------------------------------------
//		 */
//		
//		// RSAKeyGenerator 객체를 통해 공개키와 개인키를 생성한 후 리턴받아 출력
//		RSAKeyGenerator keyGenerator = new RSAKeyGenerator();
//		PublicKey publicKey = keyGenerator.getPublicKey();
//		PrivateKey privateKey = keyGenerator.getPrivateKey();
//		System.out.println(publicKey);
//		System.out.println(privateKey);
//		
//		
//		System.out.println("==============================================");
//		
//		String id = "";
//		try {
//			RSACipher rsaCipher = new RSACipher(request.getParameter("login_member_id"), publicKey, privateKey);
////			String encryptedText = rsaCipher.encrypt();
////			id = encryptedText;
//			id = rsaCipher.encrypt();
//			System.out.println("로그인 아이디 암호화 결과 : " + id);
////			System.out.println("암호화 된 문자열 길이 : " + encryptedText.length());
//			
//			System.out.println("--------------------------------------------------");
//			
//			String decryptedText = rsaCipher.decrypt(id);
//			System.out.println("로그인 아이디 복호화 결과 : " + decryptedText);
//			
//			
//		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
//				| BadPaddingException e) {
//			e.printStackTrace();
//		}
//		//==============================================================================================================
		
		String member_id = request.getParameter("login_member_id");
//		String member_passwd = request.getParameter("login_member_passwd");
		
		
		LoginProService service = new LoginProService();
		MemberBean isLogin = service.isLogin(member_id, result2);
//		MemberBean isLogin = service.isLogin(id, result2);
		System.out.println("액션이다" + isLogin);
		
		if(isLogin != null) {
			if(isLogin.getGrade_name().equals("Admin")) {
				HttpSession session = request.getSession();
				session.setAttribute("sCode", isLogin.getMember_code());
				session.setAttribute("sNickname", isLogin.getMember_nickname());
				
				forward = new ActionForward();
				forward.setPath("MemberManagement.co?value=0");
				forward.setRedirect(true);
			} else if(isLogin.getMember_service_log_status().equals("정상")) {
				HttpSession session = request.getSession();
				session.setAttribute("sCode", isLogin.getMember_code());
				session.setAttribute("sNickname", isLogin.getMember_nickname());
				forward = new ActionForward();
				forward.setPath("MainPage.pr");
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


	
