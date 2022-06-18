package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.io.PrintWriter;


import java.sql.Connection;

import dao.SellerDAO;
import vo.MemberBean;
import vo.SellerProductDTO;

public class SellerBuyInsert {

	public SellerProductDTO getShoping(int sell_num) {
		System.out.println("SellerUpdateService - SellerProductDTO()");
		
		
		SellerProductDTO shoping = null;
		
		
		Connection con = getConnection();
		
		
		SellerDAO sellerDAO = SellerDAO.getInstance();
		

		
		sellerDAO.setConnection(con);
		
		
		shoping = sellerDAO.selectArticle(sell_num);    //SellerDAO 에서   <190행selectArticle(int sell_num)매서드 활용>
		
		
		close(con);
		
		
		return shoping;
	
	}

	public int  updateMemberInfo( MemberBean memberBeanIm) {
		System.out.println("SellerUpdateService - updateMemberInfo()");
		
		int updateCount=0;
		Connection con = getConnection(); 
		
		
		SellerDAO sellerDAO = SellerDAO.getInstance();
		

		
		sellerDAO.setConnection(con);
		
	
		updateCount = sellerDAO.updateMemberInfo(memberBeanIm);   
	
		 //System.out.println("dao 작업 수행 후 service로 리턴 ->(insertUpdate:)"+updateCount);
		
		 if(updateCount>0) {  //update(dao) 작업 성공여부 결과
			commit(con); 
			
		} else {
			rollback(con);
		}
		
	
		close(con);
		
		
		
		return updateCount;
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

	public void sellUpdate(int sell_num) { //받을값 없음  ->구매시 sell_list ->status 값 판매중 -> 판매완료로 변경
		
		Connection con = getConnection();
		int updateCount =0;
		SellerDAO sellerDAO = SellerDAO.getInstance();

		sellerDAO.setConnection(con);
		
		
		 updateCount = sellerDAO.sellUpdate(sell_num);    //sell_num을 이용하여 update해주기 sell_list테이블의 sell_list_status: 판매중 ->판매완료
		 if(updateCount>0) {
			 commit(con);   
		 }else {
			 System.out.println("실패");
		 }
		close(con);
		
		
	}

}
