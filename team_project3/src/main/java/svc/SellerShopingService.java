package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.SellerDAO;
import db.JdbcUtil;
import vo.MemberBean;
import vo.SellerProductDTO;
public class SellerShopingService {
	
	public SellerProductDTO getShoping(int sell_num) {
		System.out.println("SellerShopingService - getShoping()");
		
		// 1. 리턴할 데이터를 저장할 변수 선언
		SellerProductDTO shoping = null;
		
		// 2. JdbcUtil 클래스로부터 Connection Pool 에 저장된 Connection 객체 가져오기 - 공통
		Connection con = getConnection(); // static import 적용되어 있을 경우
		
		// 3. BoardDAO 클래스로부터 BoardDAO 인스턴스 가져오기 - 공통
		SellerDAO sellerDAO = SellerDAO.getInstance();
		

		// 4. BoardDAO 객체에 Connection 객체 전달하기 - 공통
		sellerDAO.setConnection(con);
		
		// 5. BoardDAO 객체의 selectArticle() 메서드를 호출하여 게시물 상세 정보 조회
		// => 파라미터 : board_num    리턴타입 : BoardDTO(article)
		shoping = sellerDAO.selectArticle(sell_num);    //SellerDAO 에서   <190행selectArticle(int sell_num)매서드 활용>
		
		// 6. JdbcUtil 클래스로부터 가져온 Connection 객체를 반환 - 공통
		close(con);
		
		// 7. 조회 결과 리턴
		return shoping;
	
	}

	public MemberBean getMemberShop(String member_code) {
		
		System.out.println("SellerShopingService - getMemberShop()");
		
		// 1. 리턴할 데이터를 저장할 변수 선언
		MemberBean MemberShop = null;
		
		// 2. JdbcUtil 클래스로부터 Connection Pool 에 저장된 Connection 객체 가져오기 - 공통
		Connection con = getConnection(); // static import 적용되어 있을 경우
		
		// 3. BoardDAO 클래스로부터 BoardDAO 인스턴스 가져오기 - 공통
	//	MemberDAO memberDAO = MemberDAO.getInstance();
		
		SellerDAO sellerDAO = SellerDAO.getInstance();
		// 4. BoardDAO 객체에 Connection 객체 전달하기 - 공통
		sellerDAO.setConnection(con);
		
		// 5. BoardDAO 객체의 selectArticle() 메서드를 호출하여 게시물 상세 정보 조회
		// => 파라미터 : board_num    리턴타입 : BoardDTO(article)
		MemberShop = sellerDAO.selectMemberShop(member_code);
		
		// 6. JdbcUtil 클래스로부터 가져온 Connection 객체를 반환 - 공통
		close(con);
		
		// 7. 조회 결과 리턴
		return MemberShop;
	}

	



}
