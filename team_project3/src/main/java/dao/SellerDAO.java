package dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.MemberBean;
import vo.SellerDTO;
import vo.SellerProductDTO;
import vo.SellerimgDTO;

public class SellerDAO {

	private static SellerDAO instance = new SellerDAO();

	public SellerDAO() {
	}

	public static SellerDAO getInstance() {
		return instance;
	};

	static Connection con;

	public void setConnection(Connection con) {
		this.con = con;
	}

	public int insertArticle(SellerDTO seller, ArrayList<SellerimgDTO> sellimglist) { // 판매글 작성
		System.out.println("====================");
		System.out.println(seller);
		System.out.println("====================");
		System.out.println(sellimglist);
		// INSERT 작업 결과를 리턴받아 저장할 변수 선언
		int insertCount = 0;
		int num = 0;
//      int img_num=0;

		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;

		try {

			String sql = "INSERT INTO sell VALUES (?,?,?,?,?,?,?,?,?,?,REPLACE(now(),'-',''),?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, seller.getSell_member_code());
			pstmt.setString(3, seller.getSell_title()); // 새 글 번호
			pstmt.setString(4, seller.getSell_category());
			pstmt.setString(5, seller.getSell_category_detail());
			pstmt.setString(6, seller.getSell_content());
			pstmt.setInt(7, seller.getSell_price());
			pstmt.setString(8, seller.getSell_color());
			pstmt.setString(9, seller.getSell_size());
			pstmt.setString(10, seller.getSell_brand());
			pstmt.setInt(11, 0); // 조회수 컬럼

			pstmt.executeUpdate();
			System.out.println("INSERT -SELL");
			for (SellerimgDTO sellimg : sellimglist) {
				sql = "INSERT INTO sell_img VALUES ((SELECT MAX(sell_num) FROM sell),?,?,?)";
				pstmt2 = con.prepareStatement(sql);
				// int sell_img_num =;
				pstmt2.setInt(1, sellimg.getSell_img_num());
				pstmt2.setString(2, sellimg.getSell_img_name());
				pstmt2.setString(3, sellimg.getSell_img_real_name());

				pstmt2.executeUpdate();
//               close(pstmt);
				System.out.println("INSERT -SELL_IMG");
			}

			sql = "INSERT INTO sell_list VALUES ((SELECT MAX(SELL_NUM) FROM sell), '판매중', ?, '관리자 작업필요')";// 검수자
			// 승인날짜,닉네임
			// 업데이트작업필요
			pstmt3 = con.prepareStatement(sql);
			pstmt3.setString(1, seller.getSell_list_approve_date());
			insertCount = pstmt3.executeUpdate();
			System.out.println("INSERT -SELL_LIST");

			System.out.println("SellerDAO - insertArticle()");
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - insertArticle()");
			e.printStackTrace();
		} finally {
			// DB 자원 반환(주의! Connection 객체 반환 금지!)
			close(pstmt3);
			close(pstmt2);
			close(pstmt);

		}

		// INSERT 작업 결과 리턴
		return insertCount;
	}

	// shop 클릭시 데이터 뿌리기 작업(검수완료된 데이터 갯수 세기)
	public int selectListCount() {
		System.out.println("SellerDAO - selectListCount()");

		int listCount = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT COUNT(sell_list_num) FROM sell_list" + " WHERE sell_list_item_status ='판매중' "; // 나중엔
			// sell_list_item_status
			// ='판매중'로
			// 바꿔야됨
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				listCount = rs.getInt(1);
			}
			System.out.println("판매가능 상품 갯수:" + listCount);
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - selectListCount()");
			e.printStackTrace();
		} finally {
			// DB 자원 반환(주의! Connection 객체 반환 금지!)
			close(pstmt);
			close(rs);
		}

		return listCount;
	}

	public ArrayList<SellerProductDTO> selectArticleList(int pageNum, int listLimit) {
		System.out.println("selectArticleList()ArrayList 객체 가져오기");

		ArrayList<SellerProductDTO> articleList = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// 조회 시작 게시물 번호(행 번호) 계산
		int startRow = (pageNum - 1) * listLimit;

		// 게시물 목록 조회
		// => 답글에 대한 처리
		// 참조글번호(board_re_ref) 기준 내림차순,
		// 순서번호(board_re_seq) 기준 오름차순 정렬
		// => 조회 시작 게시물 번호(startRow) 부터 목록의 게시물 수(listLimit) 만큼 조회
		try {
			String sql = "SELECT a.sell_num, a.sell_size , a.sell_category,a.sell_category_detail, a.sell_title, a.sell_color, a.sell_brand, a.sell_price, a.sell_readcount,"
					+ " b.sell_img_name, b.sell_img_real_name ,b.sell_img_real_num ,b.sell_img_num,b.sell_img_name,b.sell_img_real_name, c.sell_list_num, c.sell_list_item_status"
					+ " FROM sell AS a JOIN sell_img AS b ON a.sell_num = b.sell_img_real_num JOIN sell_list AS c ON a.sell_num = c.sell_list_num"
					+ " WHERE sell_list_item_status='판매중' AND"
					+ "(sell_img_real_num,sell_img_num)  in (SELECT  sell_img_real_num, MAX(sell_img_num)  FROM sell_img    GROUP BY sell_img_real_num  ORDER BY sell_img_real_num ,sell_img_num DESC  )"
					+ " ORDER BY a.sell_num DESC LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, listLimit);

			rs = pstmt.executeQuery();

//            sql = "SELECT a.sell_title, a.sell_brand, a.sell_price, a.sell_color, a.sell_readcount, b.sell_img_name, c.sell_list_num, c.sell_list_item_status, b.sell_img_real_name"
//                     + "FROM sell AS a JOIN sell_img AS b ON a.sell_num = b.sell_img_num JOIN sell_list AS c ON a.sell_num = c.sell_list_num "
//                     + "WHERE sell_list_item_status='판매중'";
			// join을 사용하자
			// sell -> 조회수, 타이틀,브랜드
			// sell_list ->
			// sell_img -> 일단 사진은 다 사용해야됨
			// 이외에 좋아요 까지

			articleList = new ArrayList<SellerProductDTO>();

			while (rs.next()) {
				SellerProductDTO article = new SellerProductDTO();
				article.setSell_num(rs.getInt("sell_num"));
				article.setSell_size(rs.getString("sell_size"));
				article.setSell_title(rs.getString("sell_title"));
				article.setSell_price(rs.getInt("sell_price"));
				article.setSell_color(rs.getString("sell_color"));
				article.setSell_brand(rs.getString("sell_brand"));
				article.setSell_readcount(rs.getInt("sell_readcount"));
				article.setSell_list_num(rs.getInt("sell_list_num"));
				article.setSell_list_item_status(rs.getString("sell_list_item_status"));
				article.setSell_img_num(rs.getInt("Sell_img_num"));
				article.setSell_img_real_num(rs.getInt("Sell_img_real_num"));
				article.setSell_img_name(rs.getString("sell_img_name"));
				article.setSell_img_real_name(rs.getString("sell_img_real_name"));
				article.setSell_category(rs.getString("Sell_category"));
				article.setSell_category_detail(rs.getString("Sell_category_detail"));

				articleList.add(article);

			}
		} catch (SQLException e) {
			System.out.println("구문오류");
			e.printStackTrace();
		} finally {
			close(pstmt);

			close(rs);
		}
		return articleList;
	}

	public SellerProductDTO selectArticle(int sell_num) { // sell_num 값을 이용하여 해당 제품 판매관련정보 가져오기 &(코드 활용)상세글에서 (buy) 구매하기
		// ->Sellerdto 를 이용하여 상품의 상세정보 가져오기
		SellerProductDTO article = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT a.sell_num, a.sell_size , a.sell_category, a.sell_category_detail, a.sell_title, a.sell_color, a.sell_brand, a.sell_price, a.sell_readcount,"
					+" b.sell_img_name, b.sell_img_real_name ,b.sell_img_real_num ,b.sell_img_num,b.sell_img_name,b.sell_img_real_name, c.sell_list_num, c.sell_list_item_status"
					+" FROM sell AS a JOIN sell_img AS b ON a.sell_num = b.sell_img_real_num JOIN sell_list AS c ON a.sell_num = c.sell_list_num"
					+" WHERE sell_list_num= ? AND"
					+"(sell_img_real_num,sell_img_num)  in (SELECT  sell_img_real_num, MAX(sell_img_num)  FROM sell_img    GROUP BY sell_img_real_num  ORDER BY sell_img_real_num ,sell_img_num DESC  )";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, sell_num);
			rs = pstmt.executeQuery();

//         SELECT a.sell_num, a.sell_category, a.sell_category_detail, a.sell_title, a.sell_color, 
//         a.sell_brand, a.sell_price, a.sell_readcount, 
//         b.sell_img_name, b.sell_img_real_name, c.sell_list_num

			if (rs.next()) {
				article = new SellerProductDTO();
				article.setSell_num(rs.getInt("sell_num"));
				article.setSell_category(rs.getString("sell_category"));
				article.setSell_size(rs.getString("sell_size"));
				article.setSell_category_detail(rs.getString("sell_category_detail"));
				article.setSell_title(rs.getString("sell_title"));
				article.setSell_color(rs.getString("sell_color"));
				article.setSell_brand(rs.getString("sell_brand"));
				article.setSell_price(rs.getInt("sell_price"));
				article.setSell_readcount(rs.getInt("sell_readcount"));
				article.setSell_img_name(rs.getString("sell_img_name"));
				article.setSell_img_real_name(rs.getString("sell_img_real_name"));
				article.setSell_list_num(rs.getInt("sell_list_num"));
				article.setSell_list_item_status(rs.getString("sell_list_item_status"));
				System.out.println(article);

			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - selectArticle()");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}

		return article;
	}

	// 조회수 증가 위한 작업

	public void updateReadcount(int sell_num) {
		PreparedStatement pstmt = null;

		try {
			String sql = "UPDATE sell SET sell_readcount=sell_readcount+1 WHERE sell_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, sell_num);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - updateReadcount()");
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

	}

	public ArrayList<SellerDTO> selectProductRe(String sell_brand, int sell_num) {
		ArrayList<SellerDTO> productarr = new ArrayList<SellerDTO>();
		SellerDTO ProductRe = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT a.sell_num, a.sell_size ,  a.sell_title, a.sell_brand, a.sell_price, b.sell_img_name, b.sell_img_real_name "
					+ "FROM sell AS a JOIN sell_img AS b ON a.sell_num = b.sell_img_num  WHERE sell_num != " + sell_num
					+ " AND sell_brand Like '%" + sell_brand + "%' ";

			pstmt = con.prepareStatement(sql);
//         pstmt.setString(1, sell_brand);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ProductRe = new SellerDTO();
				ProductRe.setSell_num(rs.getInt("sell_num"));
				ProductRe.setSell_size(rs.getString("sell_size"));
				ProductRe.setSell_title(rs.getString("sell_title"));
				ProductRe.setSell_brand(rs.getString("sell_brand"));
				ProductRe.setSell_price(rs.getInt("sell_price"));

				productarr.add(ProductRe);
			}

		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - selectProductRe()");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}

		return productarr;
	}

	public MemberBean selectMemberShop(String member_code) { // 상세 글에서 (buy)구매하기->memberBean을 이용해 구매자 정보 가져오기
		System.out.println("selectMemberShop-구매DAO(member_code)");
		MemberBean memberbean = null;
		/*
		 * member_info테이블 fk=> member_info_code <-> member_code
		 * --------------------------------------------------------------------------
		 * member_info_name,member_info_phone,
		 * member_info_post_code,member_info_address,member_info_address_detail
		 * member_info_ship_post_code, member_info_ship_address,
		 * member_info_ship_address_detail,
		 * 
		 * 
		 * ----------------------------------------------------------------------------
		 * member_info_detail 테이블 fk => member_info_detail_code <-> member_code
		 * -----------------------------------------------------------------------------
		 * -- member_info_detail_point(포인트 적립금), member_info_detail_acc_money(누적 금액)
		 * -----------------------------------------------------------------------------
		 * -
		 * 
		 */
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = " SELECT a.member_code, b.member_info_name, b.member_info_phone, b.member_info_post_code, b.member_info_address, b.member_info_address_detail, b.member_info_ship_post_code, b.member_info_ship_address, b.member_info_ship_address_detail,"
					+ " c.member_info_detail_point, c.member_info_detail_acc_money"
					+ " FROM member AS a JOIN member_info AS b ON a.member_code = b.member_info_code JOIN member_info_detail AS c ON b.member_info_code = c.member_info_detail_code WHERE a.member_code = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member_code);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				memberbean = new MemberBean();
				memberbean.setMember_code(rs.getString("member_code"));
				memberbean.setMember_info_name(rs.getString("member_info_name"));
				memberbean.setMember_info_phone(rs.getString("member_info_phone"));
				memberbean.setMember_info_post_code(rs.getString("member_info_post_code"));
				memberbean.setMember_info_address(rs.getString("member_info_address"));
				memberbean.setMember_info_address_detail(rs.getString("member_info_address_detail"));
				memberbean.setMember_info_ship_post_code(rs.getString("member_info_ship_post_code"));
				memberbean.setMember_info_ship_address(rs.getString("member_info_ship_address"));
				memberbean.setMember_info_ship_address_detail(rs.getString("member_info_ship_address_detail"));
				memberbean.setMember_info_detail_point(rs.getInt("member_info_detail_point"));
				memberbean.setMember_info_detail_acc_money(rs.getInt("member_info_detail_acc_money"));

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return memberbean;
	}

	public ArrayList<SellerimgDTO> selectProductimg(int sell_num) {
		ArrayList<SellerimgDTO> productimg = new ArrayList<SellerimgDTO>();
		SellerimgDTO Sellerdetailimg = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT sell_img_real_num, sell_img_name, sell_img_real_name "
					+ "FROM sell_img  WHERE sell_img_real_num=? ORDER BY sell_img_num DESC ";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, sell_num);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Sellerdetailimg = new SellerimgDTO();
				Sellerdetailimg.setSell_img_real_num(rs.getInt("sell_img_real_num"));
				Sellerdetailimg.setSell_img_name(rs.getString("sell_img_name"));
				Sellerdetailimg.setSell_img_real_name(rs.getString("sell_img_real_name"));
//            ProductRe.setSell_img_name(rs.getString("sell_img_name"));
//            ProductRe.setSell_img_real_name(rs.getString("sell_img_real_name"));

				productimg.add(Sellerdetailimg);
			}

			System.out.println(productimg);

		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - updateReadcount()");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}

		return productimg;
	}

}