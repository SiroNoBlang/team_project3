package googleSMTPAuthenticator;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSACipher {

Cipher cipher; // 암호화 및 복호화 작업을 수행할 javax.crypto.Cipher 타입 변수 선언
	
	// 정보를 저장할 멤버변수 선언
	private String strPlainText; // 평문 저장용
	private PublicKey publicKey; // 공개키 저장용
	private PrivateKey privateKey; // 개인키 저장용
	
	// 파라미터 생성자를 정의하여 평문, 공개키, 개인키를 전달받아 초기화
	public RSACipher(String strPlainText, PublicKey publicKey, PrivateKey privateKey) 
											throws NoSuchAlgorithmException, NoSuchPaddingException {
		this.strPlainText = strPlainText;
		this.publicKey = publicKey;
		this.privateKey = privateKey;
		
		// Cipher 클래스의 getInstance() 메서드를 호출하여 Cipher 객체 얻어오기(암호화 알고리즘 선택)
		this.cipher = Cipher.getInstance("RSA");
	}
	
	// 암호화 작업을 수행하는 encrypt() 메서드 정의
	// => 파라미터 : 없음    리턴타입 : String
	public String encrypt() throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		// Cipher 객체의 init() 메서드를 호출하여 암호화 작업 준비(초기화)
		// => 파라미터로 ENCRYPT_MODE 상수와 공개키 전달
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		
		// Cipher 객체의 doFinal() 메서드를 호출하여 암호화 작업 수행
		// => 파라미터 : 평문에 대한 byte[] 타입, 리턴타입 ; byte[]
		byte[] encryptedBytes = cipher.doFinal(strPlainText.getBytes());
		
		String encryptedText = null;
		
		// 암호화 된 byte[] 타입 데이터를 BigInteger 객체를 통해 정수 데이터로 변환한 후 다시 문자열로 변환하여 리턴
		BigInteger bi = new BigInteger(encryptedBytes);
		encryptedText = bi.toString();
		
//		System.out.println("암호화 결과 : " + encryptedText);
//		System.out.println("암호화 된 문자열 길이 : " + encryptedText.length());
		
		return encryptedText; // 암호화 문자열 리턴
	}
	
	
	// 복호화 작업을 수행하는 decrypt() 메서드 정의
	// => 파라미터 : 암호화 문자열, 리턴타입 : String
	public String decrypt(String encryptedText) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		// 암호화 과정과 초기화 작업을 제외한 나머지 과정은 동일
		// 초기화 과정에서 ENCRYPT_MODE 대신 DECRYPT_MODE, 공개키 대신 개인키 전달
		cipher.init(Cipher.DECRYPT_MODE, privateKey); // 개인키를 사용하여 복호화 작업 준비
		
		byte[] decryptedBytes = cipher.doFinal(new BigInteger(encryptedText).toByteArray()); // 복호화
		
		// 복호화 된 데이터를 문자열로 변환
		String decryptedText = null;
		decryptedText = new String(decryptedBytes);
		
		return decryptedText;
	}
	
	
}
