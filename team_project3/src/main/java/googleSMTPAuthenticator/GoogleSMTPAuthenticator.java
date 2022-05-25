package googleSMTPAuthenticator;
import java.io.FileReader;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

//메일 서버 인증을 위해 javax.mail.Authenticator 클래스를 상속받는 서브클래스 정의
public class GoogleSMTPAuthenticator extends Authenticator {
	//인증정보(아이디,패스워드)를 관리할 
	PasswordAuthentication passwordAuthentication;
	
//	public GoogleSMTPAuthenticator() throws Exception{
////		passwordAuthentication = new PasswordAuthentication("qhrud200265","zpbfpsmexxshzyhc" );
//		
//		Properties properties = new Properties();
//		String realpath = 
//		
//	}
	
	public GoogleSMTPAuthenticator(HttpServletRequest request) throws Exception {
		Properties properties = new Properties();
		ServletContext context = request.getServletContext(); //톰캣 객체 가져오기
		String realPath = context.getRealPath("WEB-INF"); //루트기준 WEB-INF 폴더의 실제 경로 알아
//		System.out.println(realPath);
//		D:\workspace_jsp3\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\StudyJSP\WEB-INF
		
		
		//2-2 
		properties.load(new FileReader(realPath + "/application-data.properties")); //후에 throws Exception 처리 필요
		
		
		//3.
		String id = properties.getProperty("id");
		String passwd = properties.getProperty("passwd");
//		System.out.println(id + "," + passwd);
		
		passwordAuthentication = new PasswordAuthentication(id,passwd);
		
	}
	
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return passwordAuthentication;
	}
	
	
	
}
