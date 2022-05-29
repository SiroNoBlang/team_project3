package googleSMTPAuthenticator;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

public class RSAKeyGenerator {
	
private KeyPair keyPair;
	
	public RSAKeyGenerator() {
		try {
			// RSA 알고리즘을 사용하여 공개키와 개인키 한 쌍(KeyPair) 생성
			// KeyPairGenerator 클래스의 getInstance() 메서드를 호출하여 객체 얻어오기
			// => 파라미터로 암호화 알고리즘명 전달("RSA" 전달)
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			
			// 암호키의 길이를 설정하기 위한 작업
			SecureRandom secureRandom = new SecureRandom();
			// KeyPairGenerator 객체의 initialize() 메서드를 호출하여 코드길이와 난수 객체(SecureRandom) 전달
			keyPairGenerator.initialize(2048, secureRandom); // 4096 bit 코드 설정(기본값 2048 bit)
			
			// KeyPairGenerator 객체의 generateKeyPair() 또는 genKeyPair() 메서드를 호출하여 암호키(공개, 개인) 생성
			keyPair = keyPairGenerator.generateKeyPair(); // 공개키와 개인키 생성됨
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			System.out.println("입력한 암호화 알고리즘이 존재하지 않습니다!");
		}
	}
	
	// 공개키와 개인키를 각각 리턴하는 Getter 정의
	// => 공개키는 PublicKey 타입 리턴, 개인키는 PrivateKey 타입 리턴
	public PublicKey getPublicKey() {
		// 이미 생성되어 있는 KeyPair 객체의 getPublic() 메서드를 호출하여 공개키 리턴받기
//		PublicKey publicKey = keyPair.getPublic();
		return keyPair.getPublic();
	}
	
	public PrivateKey getPrivateKey() {
		return keyPair.getPrivate();
	}
	

}
