package googleSMTPAuthenticator;

import java.util.Random;

public class GenerateAuthenticationCode {

	private String authenticationCode;
	
	private char[] codeTable = {
			'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
			'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
			'0','1','2','3','4','5','6','7','8','9'
	};

	//생성자 정의
	public GenerateAuthenticationCode() {
		int codeLength = 16; //생성할 난수 길이 코드(60자리)
		Random r = new Random();
		StringBuffer buffer = new StringBuffer();
		
		// for문을 사용하여 코드 길이만큼 반복
		// => 배열 인덱스() 사이의 난수 1개 생성하여
		//    해당 난수를 배열 인덱스로 사용하여 1글자 추출 후 버퍼에 추가
		for(int i = 0; i<codeLength; i++) {
			int rNum = r.nextInt(codeTable.length);
			buffer.append(codeTable[rNum]);
		}
				
		//반복 종료 후 버퍼에 저장된 문자열을 String 타입 변수에 
		authenticationCode = buffer.toString();
		
		
	}

	public String getAuthenticationCode() {
		return authenticationCode;
	}

	
	
}
