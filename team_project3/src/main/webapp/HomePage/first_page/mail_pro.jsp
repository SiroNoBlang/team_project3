<%@page import="java.sql.Connection"%>
<%@page import="static db.JdbcUtil.*"%>
<%@page import="dao.MemberDAO"%>
<%@page import="org.apache.ibatis.reflection.SystemMetaObject"%>
<%@page import="googleSMTPAuthenticator.GenerateAuthenticationCode"%>
<%@page import="googleSMTPAuthenticator.GoogleSMTPAuthenticator"%>
<%@page import="javax.mail.Transport"%>
<%@page import="java.util.Date"%>
<%@page import="javax.mail.internet.MimeMessage.RecipientType"%>
<%@page import="javax.mail.internet.InternetAddress"%>
<%@page import="javax.mail.Address"%>
<%@page import="javax.mail.internet.MimeMessage"%>
<%@page import="javax.mail.Message"%>
<%@page import="javax.mail.Session"%>
<%@page import="javax.mail.Authenticator"%>
<%@page import="java.util.Properties"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");  
GenerateAuthenticationCode genCode = new GenerateAuthenticationCode();
String code = genCode.getAuthenticationCode();

String sender = "qhrud200265@gmail.com";
String title = "";
String receiver = "";
String content = "";

if(request.getParameter("find_passwd_member_id") != null ){
 	content = "임시비밀번호는 " + code + "입니다";
	receiver = request.getParameter("find_passwd_member_email");
	title = request.getParameter("find_passwd_member_id") + "님의 임시 비밀번호입니다.";
} else {
	content = "<h3>인증하려면 아래 링크를 클릭하세요 </h3><a href='http://localhost:8080/team_project3/HomePage/first_page/mail_pro.jsp?code="+code+"'>인증하기</a>";
	receiver = request.getParameter("email");
	title = "본인인증입니다.";
	Connection con = getConnection();
	MemberDAO memberDAO = MemberDAO.getInstance();
	memberDAO.setConnection(con);
	memberDAO.insertAuthInfo(receiver,code);
	commit(con);
	close(con);
	

}


try{
	// ------- 메일 전송에 필요한 설정 작업 -------
	// 메일 전송 프로토콜 : SMTP(Simple Mail Tranfer Protocol) 
	// (참고. 메일 수신 프로토콜 : POP3)
	// 시스템(서버)의 속성 정보(서버 정보)를 java.util.Properties 객체로 리턴받기
	Properties properties = System.getProperties();
	
	// 메일 전송에 필요한 정보를 가져온 서버 속성 정보에 추가 - put() 메서드 활용
	// TLS(Transport Layer Security) 인증 관련 설정
	properties.put("mail.smtp.starttls.enable",	"true");
	properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
	// 메일 전송에 사용할 메일 서버 지정(Gmail, 네이버, 네이트, 아웃룩 등)
	properties.put("mail.smtp.host", "smtp.gmail.com");
	properties.put("mail.smtp.auth", "true"); //인증 여부 설정(로그인 필요시)
	properties.put("mail.smtp.port", "587"); //메일 전송에 사용될 서비스 포트 설정(smtp 포트)

	//메일 서버에 대한 인증 정보를 관리하는 사용자 정의 클래스의 인스턴스 생성()
	Authenticator authenticator = new GoogleSMTPAuthenticator(request); //업캐스팅
	

	// 자바 메일의 기본 전송 단위를 javax.mail.Session 객체 단위로 관리
	// => Session 클래스의 getDefaultInstance() 메서드를 호출하여 파라미터로 서버 정보, 인증 정보 전달
//	    (주의! 변수명은 session 외의 다른 이름 사용 필수! => HttpSession 내장객체 변수명이 존재함)
	Session mailSession = Session.getDefaultInstance(properties, authenticator);

	// Message 정보를 관리할 javax.mail.internet.MimeMessage 객체 생성
	// => 파라미터로 서버 정보와 인증 정보를 관리하는 javax.mail.Session 객체 전달
	// => MimeMessage 객체를 사용하여 전송할 메일에 대한 정보 설정 가능	
	Message mailMessage = new MimeMessage(mailSession);

	// 전송할 메일에 대한 정보 설정
	// 1. 발신자 정보 설정(발신자 정보를 관리하기 위한 InternetAddress 객체 생성)
	// => 단, 스팸 메일 정책으로 인해 상용 메일 사이트(구글 등)는 발신자 주소 변경 불가능
	Address sender_address = new InternetAddress(sender,"TRUST");

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
// 	out.println("<h3>메일이 정상적으로 전송되었습니다!</h3>");
	out.println("<script>");
	out.println("alert('메일이 정상적으로 전송되었습니다!')");
	out.println("history.back()");
	out.println("</script>");
	
}catch(Exception e){
	e.printStackTrace();
	out.println("<h3>SMTP 서버 설정 또는 서비스 문제 발생!</h3>");
}
%>    
















