package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import googleSMTPAuthenticator.MyMessageDigest;
import googleSMTPAuthenticator.RSACipher;
import googleSMTPAuthenticator.RSAKeyGenerator;
import svc.JoinProService;
import vo.ActionForward;
import vo.MemberBean;



import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class JoinProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		System.out.println("JoinProAction");
		ActionForward forward = null;
		
		String val = "";
		String bnd = "";
		String ctgy = "";
		String email = request.getParameter("member_email1") + request.getParameter("member_email2");
		
		String [] style = request.getParameterValues("style");
		String [] brand = request.getParameterValues("brand");
		String [] category = request.getParameterValues("category");
		
		for(String val3 : style) {
			val += val3 + ",";
		}
		
		for(String b : brand) {
			bnd += b + ",";
		}
		
		for(String c : category) {
			ctgy += c + ",";
		}
		
		//비밀번호 암호화
		String algorithm = "SHA-256";
		
		String strPlainText = request.getParameter("join_member_passwd"); // 평문 암호
		
		
		// MyMessageDigest 객체 생성 시 생성자에 암호알고리즘명과 평문암호 전달하여 암호화 수행
		MyMessageDigest mmd = new MyMessageDigest(algorithm, strPlainText);
		
		// MyMessageDigest 객체의 getHashedData() 메서드를 호출하여 암호화 된 암호문을 리턴받아 출력
		String result = mmd.getHashedData();
		System.out.println("비밀번호 암호화 결과 : " + result);
		
//		//=====================================================================================================
//		//아이디
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
//			RSACipher rsaCipher = new RSACipher(request.getParameter("join_member_id"), publicKey, privateKey);
////			String encryptedText = rsaCipher.encrypt();
////			id = encryptedText;
//			id = rsaCipher.encrypt();
//			System.out.println("아이디 암호화 결과 : " + id);
////			System.out.println("암호화 된 문자열 길이 : " + encryptedText.length());
//			
//			System.out.println("--------------------------------------------------");
//			
//			String decryptedText = rsaCipher.decrypt(id);
//			System.out.println("아이디 복호화 결과 : " + decryptedText);
//			
//			
//		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
//				| BadPaddingException e) {
//			e.printStackTrace();
//		}
//		//=================================================================================================================
		
		
		MemberBean memberBean = new MemberBean();
		memberBean.setMember_nickname(request.getParameter("member_nickname"));
//		memberBean.setMember_id(id);
		memberBean.setMember_id(request.getParameter("join_member_id"));
		memberBean.setMember_passwd(result);
		memberBean.setMember_email(request.getParameter("member_email1") + request.getParameter("member_email2"));
		memberBean.setMember_info_gender(request.getParameter("member_info_gender"));
		memberBean.setMember_info_age(request.getParameter("member_info_age"));
		memberBean.setMember_info_detail_like_style(val);
		memberBean.setMember_info_detail_like_brand(bnd);
		memberBean.setMember_info_detail_like_category(ctgy);
		
		JoinProService service = new JoinProService();
		boolean isJoinsuccess = service.joinSuccess(memberBean);
		
		if(isJoinsuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('메일이 정상적으로 전송되었습니다!<br> 인증을 완료해주세요')");
			out.println("</script>");
			forward = new ActionForward("./", true);
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원가입 실패!')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}

}
