package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;
import java.sql.Connection;

import dao.SellerDAO;

import vo.MemberBean;
import vo.SellerProductDTO;

public class SellerUpdateService {

	public SellerProductDTO getShoping(int sell_num) {
		System.out.println("SellerUpdateService - SellerProductDTO()");
		
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

	public int  updateMemberInfo( MemberBean memberBeanIm) {
		System.out.println("SellerUpdateService - updateMemberInfo()");
		
		int updateCount=0;
		Connection con = getConnection(); // static import 적용되어 있을 경우
		
		// 3. BoardDAO 클래스로부터 BoardDAO 인스턴스 가져오기 - 공통
		SellerDAO sellerDAO = SellerDAO.getInstance();
		

		// 4. BoardDAO 객체에 Connection 객체 전달하기 - 공통
		sellerDAO.setConnection(con);
		
		// 5. BoardDAO 객체의 selectArticle() 메서드를 호출하여 게시물 상세 정보 조회
		// => 파라미터 : board_num    리턴타입 : BoardDTO(article)
		updateCount = sellerDAO.updateMemberInfo(memberBeanIm);   
	
		 System.out.println("dao 작업 수행 후 service로 리턴 ->(insertUpdate:)"+updateCount);
		
		 if(updateCount>0) {  //update(dao) 작업 성공여부 결과
			commit(con); 
			System.out.println("성공");
			
		} else {
			rollback(con);
		}
		
	

		// 6. JdbcUtil 클래스로부터 가져온 Connection 객체를 반환 - 공통
		close(con);
		
		
		
		return updateCount;
	}

	public int insertBuyMember(SellerProductDTO sellerDTO) {  //구매자 구매시, 구매 정보 저장용 -(buy Table)
		int insertCount =0;
		
		
		
		Connection con = getConnection(); // static import 적용되어 있을 경우
		
		// 3. BoardDAO 클래스로부터 BoardDAO 인스턴스 가져오기 - 공통
		SellerDAO sellerDAO = SellerDAO.getInstance();
		

		// 4. BoardDAO 객체에 Connection 객체 전달하기 - 공통
		sellerDAO.setConnection(con);
		
		// 5. BoardDAO 객체의 selectArticle() 메서드를 호출하여 게시물 상세 정보 조회
		// => 파라미터 : board_num    리턴타입 : BoardDTO(article)
		insertCount = sellerDAO.insertMemberInfo(sellerDTO);   
	
		 System.out.println("(insertUpdate:)"+insertCount);
		
		 if(insertCount>0) {  //update(dao) 작업 성공여부 결과
			commit(con); 
			System.out.println("insert 성공");
			
		} else {
			rollback(con);
		}
		close(con);

		return insertCount;
	}

	public MemberBean getArticleMemberInfo(String member_code) {
		MemberBean bean = null;
		
		Connection con = getConnection();
		
		SellerDAO sellerDAO = SellerDAO.getInstance();

		sellerDAO.setConnection(con);
		
		
		bean = sellerDAO.selectMemberInfo(member_code);    //member_code를 이용하여 UPDATE 된 memberBean 객체 가져오기
	
		close(con);
		
		
		return bean;
	}

}
