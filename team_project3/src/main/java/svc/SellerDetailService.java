package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.SellerDAO;
import vo.SellerDTO;
import vo.SellerProductDTO;
import vo.SellerimgDTO;

public class SellerDetailService {

	public SellerProductDTO getArticle(int sell_num) {
		System.out.println("SellerProductDTO - getArticle()");
		
		// 1. 리턴할 데이터를 저장할 변수 선언
		SellerProductDTO article = null;
		
		// 2. JdbcUtil 클래스로부터 Connection Pool 에 저장된 Connection 객체 가져오기 - 공통
		Connection con = getConnection(); // static import 적용되어 있을 경우
		
		// 3. BoardDAO 클래스로부터 BoardDAO 인스턴스 가져오기 - 공통
		SellerDAO sellerDAO = SellerDAO.getInstance();
		

		// 4. BoardDAO 객체에 Connection 객체 전달하기 - 공통
		sellerDAO.setConnection(con);
		
		// 5. BoardDAO 객체의 selectArticle() 메서드를 호출하여 게시물 상세 정보 조회
		// => 파라미터 : board_num    리턴타입 : BoardDTO(article)
		article = sellerDAO.selectArticle(sell_num);
		
		// 6. JdbcUtil 클래스로부터 가져온 Connection 객체를 반환 - 공통
		close(con);
		
		// 7. 조회 결과 리턴
		return article;
	
	}

	public void increaseReadcount(int sell_num) {
		Connection con = getConnection(); // static import 적용되어 있을 경우
		
		// 3. BoardDAO 클래스로부터 BoardDAO 인스턴스 가져오기 - 공통
		SellerDAO sellerDAO = SellerDAO.getInstance();
		
		// 4. BoardDAO 객체에 Connection 객체 전달하기 - 공통
		sellerDAO.setConnection(con);
		
		// 5. BoardDAO 객체의 updateReadcount() 메서드를 호출하여 게시물 조회수 증가
		// => 파라미터 : board_num
		sellerDAO.updateReadcount(sell_num);
		
		// 6. commit 작업 수행
		commit(con);
		
		// 7. JdbcUtil 클래스로부터 가져온 Connection 객체를 반환 - 공통
		close(con);
		
	}
	
	public ArrayList<SellerDTO> getProductRe(String sell_brand,int sell_num) {
		System.out.println("SellerProductReService - get()");
		
		// 1. 리턴할 데이터를 저장할 변수 선언
		ArrayList<SellerDTO> ProductRe = null;
		
		// 2. JdbcUtil 클래스로부터 Connection Pool 에 저장된 Connection 객체 가져오기 - 공통
		Connection con = getConnection(); // static import 적용되어 있을 경우
		
		// 3. BoardDAO 클래스로부터 BoardDAO 인스턴스 가져오기 - 공통
		SellerDAO sellerDAO = SellerDAO.getInstance();
		

		// 4. BoardDAO 객체에 Connection 객체 전달하기 - 공통
		sellerDAO.setConnection(con);
		
		// 5. BoardDAO 객체의 selectArticle() 메서드를 호출하여 게시물 상세 정보 조회
		// => 파라미터 : sell_brand    리턴타입 : SellerDTO 
		ProductRe = sellerDAO.selectProductRe(sell_brand,sell_num);
		
		// 6. JdbcUtil 클래스로부터 가져온 Connection 객체를 반환 - 공통
		close(con);
		
		// 7. 조회 결과 리턴
		return ProductRe;

	}

	public ArrayList<SellerimgDTO> getimgArticle(int sell_num) {
System.out.println("SellerProductReService - get()");
		
		// 1. 리턴할 데이터를 저장할 변수 선언
		ArrayList<SellerimgDTO> Sellerdetailimg = null;
		
		// 2. JdbcUtil 클래스로부터 Connection Pool 에 저장된 Connection 객체 가져오기 - 공통
		Connection con = getConnection(); // static import 적용되어 있을 경우
		
		// 3. BoardDAO 클래스로부터 BoardDAO 인스턴스 가져오기 - 공통
		SellerDAO sellerDAO = SellerDAO.getInstance();
		

		// 4. BoardDAO 객체에 Connection 객체 전달하기 - 공통
		sellerDAO.setConnection(con);
		
		// 5. BoardDAO 객체의 selectArticle() 메서드를 호출하여 게시물 상세 정보 조회
		// => 파라미터 : sell_brand    리턴타입 : SellerDTO 
		Sellerdetailimg = sellerDAO.selectProductimg(sell_num);
		
		// 6. JdbcUtil 클래스로부터 가져온 Connection 객체를 반환 - 공통
		close(con);
		
		// 7. 조회 결과 리턴
		return Sellerdetailimg;

	}

}
