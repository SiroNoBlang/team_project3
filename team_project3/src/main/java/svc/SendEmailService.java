package svc;

import static db.JdbcUtil.*;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.http.HttpServletRequest;

import dao.MemberDAO;
import googleSMTPAuthenticator.GenerateAuthenticationCode;
import googleSMTPAuthenticator.GoogleSMTPAuthenticator;

public class SendEmailService {

	public boolean isSendEmail(String email, HttpServletRequest request) {
		boolean isSendEmail = false;

		GenerateAuthenticationCode genCode = new GenerateAuthenticationCode();
		String code = genCode.getAuthenticationCode();
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		String sender = "qhrud200265@gmail.com";
		String title = "";
		String receiver = email;
		String content = "";

		content = "<h3>인증하려면 아래 링크를 클릭하세요 </h3><a href='http://localhost:8080/team_project3/HomePage/first_page/member_authentication.jsp?email=" + email + "&code=" + code + "'>인증하기</a>";
		title = "본인인증입니다.";
		
		Connection con = getConnection();
		
		MemberDAO memberDAO = MemberDAO.getInstance();
		
		memberDAO.setConnection(con);
		
		isSendEmail = memberDAO.insertAuthInfo(receiver, code);
		
		commit(con);
		
		close(con);

		if (isSendEmail) {
			try {
				// ------- 메일 전송에 필요한 설정 작업 -------
				// 메일 전송 프로토콜 : SMTP(Simple Mail Tranfer Protocol)
				Properties properties = System.getProperties();

				// 메일 전송에 필요한 정보를 가져온 서버 속성 정보에 추가 - put() 메서드 활용
				// TLS(Transport Layer Security) 인증 관련 설정
				properties.put("mail.smtp.starttls.enable", "true");
				properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
				// 메일 전송에 사용할 메일 서버 지정(Gmail, 네이버, 네이트, 아웃룩 등)
				properties.put("mail.smtp.host", "smtp.gmail.com");
				properties.put("mail.smtp.auth", "true"); // 인증 여부 설정(로그인 필요시)
				properties.put("mail.smtp.port", "587"); // 메일 전송에 사용될 서비스 포트 설정(smtp 포트)

				// 메일 서버에 대한 인증 정보를 관리하는 사용자 정의 클래스의 인스턴스 생성()
				Authenticator authenticator = new GoogleSMTPAuthenticator(request); // 업캐스팅

				// Session 클래스의 getDefaultInstance() 메서드를 호출하여 파라미터로 서버 정보, 인증 정보 전달
				Session mailSession = Session.getDefaultInstance(properties, authenticator);

				// MimeMessage 객체를 사용하여 전송할 메일에 대한 정보 설정 가능
				Message mailMessage = new MimeMessage(mailSession);

				// 전송할 메일에 대한 정보 설정
				// 1. 발신자 정보 설정(발신자 정보를 관리하기 위한 InternetAddress 객체 생성)
				// => 단, 스팸 메일 정책으로 인해 상용 메일 사이트(구글 등)는 발신자 주소 변경 불가능
				Address sender_address = new InternetAddress(sender, "TRUST");

				// 2. 수신자 정보 설정
				Address receiver_address = new InternetAddress(receiver);

				// 3. Message 객체를 통해 전송할 메세지 정보 설정
				// 1) 메일 헤더 정보 설정
				mailMessage.setHeader("contetn_type", "text/html; charset=UTF-8");

				// 2) 발신자 정보 설정
				mailMessage.setFrom(sender_address);

				// 3) 수신자 정보 설정(RecipientType 클래스의 TO 상수와 수신자 주소 전달)
				mailMessage.addRecipient(RecipientType.TO, receiver_address);

				// 4) 메일 제목 설정
				mailMessage.setSubject(title);

				// 5) 메일 본문 설정(본문과 본문의 컨텐츠타입 전달)
				mailMessage.setContent(content, "text/html; charset=UTF-8");

				// 6) 메일 전송 날짜 정보 설정(java.util.Date 객체를 활용하여 현재 시스템의 시각 정보 전달)
				mailMessage.setSentDate(new Date());

				// 4.메일 전송
				// javax.mail.Transport 클래스의 static 메서드 send() 메서드 호출
				Transport.send(mailMessage);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return isSendEmail;

	}
}
