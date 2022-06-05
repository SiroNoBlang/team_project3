package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.SellerDAO;
import vo.SellerDTO;
import vo.SellerimgDTO;

public class SellerWriteProService {

	public boolean registArticle(SellerDTO seller, ArrayList<SellerimgDTO> sellimglist) {
		// 글쓰기 작업 요청 처리 결과를 판별
		boolean isWriteSuccess = false;
		
		// JdbcUtil 클래스로부터 Connection Pool 에 저장된 Connection 객체 가져오기
		Connection con = getConnection(); // static import 적용되어 있을 경우
		
		//  BoardDAO 객체의 (getInstance() 메서드를 호출하여 싱글톤 패턴으로 생성된 인스턴스 리턴
		SellerDAO sellerDAO = SellerDAO.getInstance();
		
		// BoardDAO 객체의 (setConnection() 메서드를 호출
		sellerDAO.setConnection(con);
		
		// BoardDAO 객체의 insertArticle() 메서드를 호출
		int insertCount = sellerDAO.insertArticle(seller,sellimglist);
		
		if(insertCount > 0) { // 작업 성공 시
			commit(con);
			
			isWriteSuccess = true;
		} else { // 작업 실패 시
			
			rollback(con);
		}
		
		close(con);
		
		return isWriteSuccess;
	}

}
