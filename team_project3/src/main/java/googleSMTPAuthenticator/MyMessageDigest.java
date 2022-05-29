package googleSMTPAuthenticator;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MyMessageDigest {
private String strHashedData = ""; // 암호화 된 암호문을 저장할 변수
	
	// 해시알고리즘명과 원문(평문)을 전달받을 파라미터 생성자 정의 
	public MyMessageDigest(String algorithm, String strPlaintext) {
		// 암호화를 수행할 hashing() 메서드 호출
		hashing(algorithm, strPlaintext);  
	}
	
	// 해싱(암호화) 작업을 수행하는 hashing() 메서드 정의
	// => 파라미터 : 암호화 알고리즘명(algorithm), 원문(strPlainText)
	// => 생성자를 통해서만 호출 가능하도록 접근 불가능하게 설정(private)
	private void hashing(String algorithm, String strPlaintext) {
		try {
			// 1. MessageDigest 클래스의 static 메서드 getInstance() 메서드를 호출하여 객체 얻어오기
			// => 파라미터로 해시알고리즘명을 문자열로 전달
			// => NoSuchAlgorithmException 예외 처리 필요
			MessageDigest md = MessageDigest.getInstance(algorithm);
			
			// 2. 전달받은 평문 암호를 byte[] 타입으로 변환(암호화를 위한 준비 과정) 
			// => String 클래스의 getBytes() 메서드 활용
			// => 문자열을 각각 분리하여 해당 문자에 대한 코드값으로 변환하는 과정
			//    ex) "1234" 를 1, 2, 3, 4 로 각각 분리하려 byte 타입의 코드로 변환 시 49, 50, 51, 52 로 변환됨
			byte[] byteText = strPlaintext.getBytes(); 
//			System.out.println(Arrays.toString(byteText)); // [49, 50, 51, 52]
			
			// 3. MessageDigest 객체의 update() 메서드를 호출하여 평문의 byte[] 타입 변환 결과를 파라미터로 전달
			md.update(byteText);
			
			// 4. MessageDigest 객체의 digest 메서드를 호출하여 암호화 수행
			// => 암호화 결과를 다시 byte[] 타입으로 리턴
			byte[] digest = md.digest();
//			System.out.println(Arrays.toString(digest));
			// [-127, -36, -101, -37, 82, -48, 77, -62, 0, 54, -37, -40, 49, 62, -48, 85]
			
			// 암호화 된 byte[] 값을 16진수 FF 와 AND 연산을 통해 원래 값(양수값) 변환 후
			// 다시 16진수 문자열로 변환하여 암호문 변수에 결합 => 모든 영문자를 대문자로 변환하여 표기
			for(byte b : digest) {
				strHashedData += Integer.toHexString(b & 0xFF).toUpperCase(); 
				// ex) 81DC9BDB52D04DC2036DBD8313ED055
			}
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			System.out.println("입력한 암호화 알고리즘이 존재하지 않습니다!");
		}
	}

	// 암호문을 외부로 리턴할 getHashedData() 메서드 정의
	public String getHashedData() {
		return strHashedData;
	}
	
}



