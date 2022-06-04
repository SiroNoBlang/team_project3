package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.SellerDAO;
import vo.SellerProductDTO;

public class InsertBuyMemberService {
	public int insertBuyMember(SellerProductDTO sellerDTO) { // 구매자 구매시, 구매 정보 저장용 -(buy Table)
		int insertCount = 0;

		Connection con = getConnection();

		SellerDAO sellerDAO = SellerDAO.getInstance();

		sellerDAO.setConnection(con);

		insertCount = sellerDAO.insertMemberInfo(sellerDTO);

		if (insertCount > 0) { // update(dao) 작업 성공여부 결과
			commit(con);
			System.out.println("insert 성공");

		} else {
			rollback(con);
		}
		close(con);

		return insertCount;
	}
}
