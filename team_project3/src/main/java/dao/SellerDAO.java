package dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.AddressVO;
import vo.MemberBean;
import vo.SellerAddress;
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

	private Connection con;

	public void setConnection(Connection con) {
		this.con = con;
	}
	// 판매글 작성
	public int insertArticle(SellerDTO seller, ArrayList<SellerimgDTO> sellimglist) { 

		int insertCount = 0;
		int num = 0;


		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;

		try {

			String sql = "INSERT INTO sell VALUES (?,?,?,?,?,?,?,?,?,?,REPLACE(now(),'-',''),?,?)";
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
			pstmt.setInt(12, 0);
			pstmt.executeUpdate();
			for (SellerimgDTO sellimg : sellimglist) {
				sql = "INSERT INTO sell_img VALUES ((SELECT MAX(sell_num) FROM sell),?,?,?)";
				pstmt2 = con.prepareStatement(sql);
				pstmt2.setInt(1, sellimg.getSell_img_num());
				pstmt2.setString(2, sellimg.getSell_img_name());
				pstmt2.setString(3, sellimg.getSell_img_real_name());

				pstmt2.executeUpdate();
			}

			sql = "INSERT INTO sell_list VALUES ((SELECT MAX(SELL_NUM) FROM sell),?,NULL, '관리자 작업필요')";// 검수자

			pstmt3 = con.prepareStatement(sql);
			pstmt3.setString(1, seller.getSell_list_item_status());
			insertCount = pstmt3.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt3);
			close(pstmt2);
			close(pstmt);

		}

		return insertCount;
	}

	// shop 클릭시 데이터 뿌리기 작업(검수완료된 데이터 갯수 세기)
	public int selectListCount() {
		int listCount = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT COUNT(sell_list_num) FROM sell_list" + " WHERE sell_list_item_status ='판매중' "; 

			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(pstmt);
			close(rs);
		}

		return listCount;
	}
	
	// 상품리스트 검색 필터
		public ArrayList<SellerProductDTO> searchArticleList(int pageNum, int listLimit, String productSearch) {
			ArrayList<SellerProductDTO> productList = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			// 조회 시작 게시물 번호(행 번호) 계산
			int startRow = (pageNum - 1) * listLimit;

			try {
				String sql = "SELECT a.sell_num, a.sell_size , a.sell_category,a.sell_category_detail, a.sell_title, a.sell_color, a.sell_brand, a.sell_price, a.sell_readcount,"
						+ " b.sell_img_name, b.sell_img_real_name ,b.sell_img_real_num ,b.sell_img_num,b.sell_img_name,b.sell_img_real_name, c.sell_list_num, c.sell_list_item_status"
						+ " FROM sell AS a JOIN sell_img AS b ON a.sell_num = b.sell_img_real_num JOIN sell_list AS c ON a.sell_num = c.sell_list_num"
						+ " WHERE sell_list_item_status='판매중' AND sell_brand Like '%" + productSearch
						+ "%' OR sell_title Like '%" + productSearch + "%' AND "
						+ "(sell_img_real_num,sell_img_num)  in (SELECT sell_img_real_num, MAX(sell_img_num)  FROM sell_img    GROUP BY sell_img_real_num  ORDER BY sell_img_real_num ,sell_img_num DESC  )"
						+ " ORDER BY a.sell_num DESC LIMIT ?,? ";

				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, listLimit);

				rs = pstmt.executeQuery();

				productList = new ArrayList<SellerProductDTO>();

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

					productList.add(article);

				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);

				close(rs);
			}
			return productList;
		}

	// 판매중인 상품 리스트 조회
	public ArrayList<SellerProductDTO> selectArticleList(int pageNum, int listLimit) {
		ArrayList<SellerProductDTO> articleList = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// 조회 시작 게시물 번호(행 번호) 계산
		int startRow = (pageNum - 1) * listLimit;

		try {
			String sql = "SELECT a.sell_num, a.sell_size , a.sell_category, a.sell_category_detail, a.sell_title, a.sell_color, a.sell_brand, a.sell_price, (SELECT COUNT(*) FROM like_list WHERE like_list_item_num= a.sell_num ) AS sell_likecount , a.sell_readcount, a.sell_member_code,"
					+ "	b.sell_img_name, b.sell_img_real_name ,b.sell_img_real_num ,b.sell_img_num,b.sell_img_name,b.sell_img_real_name,"
					+ "	c.sell_list_num, c.sell_list_item_status"
					+ "	FROM sell AS a JOIN sell_img AS b ON a.sell_num = b.sell_img_real_num JOIN sell_list AS c ON a.sell_num = c.sell_list_num"
					+ "	WHERE sell_list_item_status='판매중'  AND"
					+ "	(sell_img_real_num,sell_img_num)  in (SELECT  sell_img_real_num, MAX(sell_img_num)  FROM sell_img    GROUP BY sell_img_real_num  ORDER BY sell_img_real_num ,sell_img_num DESC  )"
					+ "	ORDER BY a.sell_num DESC LIMIT ?,?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, listLimit);

			rs = pstmt.executeQuery();

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
				article.setSell_likecount(rs.getInt("sell_likecount"));

				articleList.add(article);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);

			close(rs);
		}
		return articleList;
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
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

	}

	// sell_num 값을 이용하여 해당 제품 판매관련정보 가져오기(product_detail.jsp)
	public SellerProductDTO selectArticle(int sell_num) { 
		// ->Sellerdto 를 이용하여 상품의 상세정보 가져오기
		SellerProductDTO article = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT a.sell_content,a.sell_num,a.sell_member_code, a.sell_size , a.sell_category, a.sell_category_detail, a.sell_title, a.sell_color, a.sell_brand, (a.sell_price*10000)AS sell_price , a.sell_readcount,(SELECT COUNT(*) FROM like_list WHERE like_list_item_num= a.sell_num ) AS sell_likecount ,"
					+ " b.sell_img_name, b.sell_img_real_name ,b.sell_img_real_num ,b.sell_img_num,b.sell_img_name,b.sell_img_real_name, c.sell_list_num, c.sell_list_item_status"
					+ " FROM sell AS a JOIN sell_img AS b ON a.sell_num = b.sell_img_real_num JOIN sell_list AS c ON a.sell_num = c.sell_list_num"
					+ " WHERE sell_list_num= ? AND"
					+ "(sell_img_real_num,sell_img_num)  in (SELECT  sell_img_real_num, MAX(sell_img_num)  FROM sell_img    GROUP BY sell_img_real_num  ORDER BY sell_img_real_num ,sell_img_num DESC  )";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, sell_num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				article = new SellerProductDTO();
				article.setSell_num(rs.getInt("sell_num"));
				article.setSell_member_code(rs.getString("sell_member_code"));
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
				article.setSell_content(rs.getString("sell_content"));
				article.setSell_likecount(rs.getInt("sell_likecount"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}

		return article;
	}
	// 상세판매 페이지에서 관심상품 뿌리는기능(최대 4장)
	public ArrayList<SellerProductDTO> selectProductRe(String sell_brand, int sell_num) { 
		ArrayList<SellerProductDTO> productarr = new ArrayList<SellerProductDTO>();
		SellerProductDTO ProductRe = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = " SELECT a.sell_num, a.sell_size , a.sell_title, a.sell_brand, a.sell_price, b.sell_img_name, b.sell_img_real_name , c.sell_list_num, c.sell_list_item_status"
					+" FROM sell AS a JOIN sell_img AS b ON a.sell_num = b.sell_img_real_num JOIN sell_list AS c ON a.sell_num = c.sell_list_num WHERE sell_num !=?"
		            +" AND sell_list_item_status='판매중'"
					+" AND sell_brand Like '%" + sell_brand + "%' " + " LIMIT 0,4";
			
		

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, sell_num);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ProductRe = new SellerProductDTO();
				ProductRe.setSell_num(rs.getInt("sell_num"));
				ProductRe.setSell_size(rs.getString("sell_size"));
				ProductRe.setSell_title(rs.getString("sell_title"));
				ProductRe.setSell_brand(rs.getString("sell_brand"));
				ProductRe.setSell_price(rs.getInt("sell_price"));
				ProductRe.setSell_img_name(rs.getString("sell_img_name"));
				ProductRe.setSell_img_real_name(rs.getString("sell_img_real_name"));

				productarr.add(ProductRe);

			}
			System.out.println(productarr);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}

		return productarr;
	}
	// 상세 글에서 (buy)구매하기->memberBean을 이용해 구매자 정보 가져오기

	public MemberBean selectMemberShop(String member_code) { 		
		
		MemberBean memberbean = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT a.member_code, b.member_info_name, b.member_info_phone, b.member_info_post_code, b.member_info_address, b.member_info_address_detail, b.member_info_ship_post_code, b.member_info_ship_address, b.member_info_ship_address_detail,"
					+ "	c.member_info_detail_point, c.member_info_detail_acc_money, d.grade_name,d.discount_rate"
					+ "	FROM member AS a JOIN member_info AS b ON a.member_code = b.member_info_code JOIN member_info_detail AS c ON b.member_info_code = c.member_info_detail_code JOIN grade AS d"
					+ "	ON c.member_info_detail_acc_money  BETWEEN d.lowest_acc_money AND d.highest_acc_money"
					+ "	WHERE a.member_code =  ?";
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
				memberbean.setGrade_name(rs.getString("grade_name"));
				memberbean.setDiscount_rate(rs.getInt("discount_rate"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return memberbean;
	}
	// 상품 디테일에 사진 뿌리기위한 작업
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

				productimg.add(Sellerdetailimg);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}

		return productimg;
	}
	
	// 배송지 주소 찾기
		public ArrayList<SellerAddress> findAddress(String sell_member_code) {
			System.out.println("sellerDAO-findAddress");
			ArrayList<SellerAddress> addressArr = null;
			SellerAddress address = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				String sql = "SELECT  DISTINCT address_code, post_code, member_code,  address_detail, address_name, address_phone"
						 + "  FROM address  WHERE member_code=? ORDER BY address_num DESC LIMIT 0, 5";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, sell_member_code);

				addressArr = new ArrayList<SellerAddress>();
				rs = pstmt.executeQuery();

				while (rs.next()) {
					address = new SellerAddress();
					address.setMember_code(rs.getString("member_code"));
					address.setAddress_code(rs.getString("address_code"));
					address.setAddress_detail(rs.getString("address_detail"));
					address.setPost_code(rs.getString("post_code"));
					address.setAddress_phone(rs.getString("address_phone"));
					address.setAddress_name(rs.getString("address_name"));

					addressArr.add(address);
				}

			} catch (Exception e) {

				e.printStackTrace();
			} finally {
				close(rs);
				close(pstmt);
			}
			return addressArr;
		}
	// 결제 완료 후 MemberBean 객체를 member_codo를 이용하여 action에서 받아온 MemberBean memberBeanIm 정보를 MemberBean 객체에 Update 해주기
	public int updateMemberInfo(MemberBean memberBeanIm) {
		PreparedStatement pstmt = null;
		int updateCount = 0;

		try {
			String sql = "UPDATE   member_info_detail"
					+ "	SET member_info_detail_point= member_info_detail_point - ?, member_info_detail_acc_money= member_info_detail_acc_money + ROUND((?/10000),0) "        
					+ "	WHERE member_info_detail_code= ? ";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, memberBeanIm.getMember_info_detail_point());
			pstmt.setInt(2, memberBeanIm.getMember_info_detail_acc_money());
			pstmt.setString(3, memberBeanIm.getMember_code());

			updateCount = pstmt.executeUpdate();


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);

		}

		return updateCount;
	}

	// 구매자 정보 저장용 DAO -(구매시 구매자 정보 저장 )
	public int insertMemberInfo(SellerProductDTO sellerDTO) {
		int insertCount = 0;
		PreparedStatement pstmt = null;

		try {
			// 현재 service와 dao에서 값이 안넘어옴
			String sql = "INSERT INTO buy VALUES(?,?,ROUND((?/10000),0) ,ROUND((?*0.05),0) ,REPLACE(now(),'-',''),'배송중');";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sellerDTO.getBuy_member_code());
			pstmt.setInt(2, sellerDTO.getBuy_item_num());
			pstmt.setInt(3, sellerDTO.getBuy_price());  
			pstmt.setInt(4, sellerDTO.getBuy_point());

			insertCount = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("구문에러!!!!");
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return insertCount;
	}
	// update 완료 후 update된 내용 가져오기
	public MemberBean selectMemberInfo(String member_code) { 
		MemberBean member = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT a.member_info_name, a.member_info_phone, a.member_info_post_code, a.member_info_address, a.member_info_address_detail,"
					+ " b.member_info_detail_point, b.member_info_detail_acc_money ,c.grade_name"
					+ "	FROM member_info AS a JOIN member_info_detail AS b ON a.member_info_code=b.member_info_detail_code"
					+ "	JOIN grade AS c ON b.member_info_detail_acc_money BETWEEN c.lowest_acc_money AND c.highest_acc_money"
					+ " WHERE member_info_code=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member_code);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				member = new MemberBean();
				member.setMember_info_name(rs.getString("member_info_name"));
				member.setMember_info_phone(rs.getString("member_info_phone"));
				member.setMember_info_post_code(rs.getString("member_info_post_code"));
				member.setMember_info_address(rs.getString("member_info_address"));
				member.setMember_info_address_detail(rs.getString("member_info_address_detail"));
				member.setMember_info_detail_point(rs.getInt("member_info_detail_point"));
				member.setMember_info_detail_acc_money(rs.getInt("member_info_detail_acc_money"));
				member.setGrade_name(rs.getString("grade_name"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return member;
	}

	// sell_num을 이용하여 update해주기 sell_list테이블의 sell_list_status: 판매중 ->판매완료
	public int sellUpdate(int sell_num) {
		PreparedStatement pstmt = null;
		int updateCount = 0;
		try {
			String sql = "UPDATE sell_list" + " SET sell_list_item_status ='판매완료' " + " WHERE sell_list_num =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, sell_num);

			updateCount = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return updateCount;
	}

	public MemberBean getEmail(String code) {
		MemberBean member = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT member_code, member_email, member_nickname FROM member WHERE member_code=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, code);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				member = new MemberBean();
				member.setMember_code(rs.getString(1));
				member.setMember_email(rs.getString(2));
				member.setMember_nickname(rs.getString(3));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return member;
	}
	// 상품리스트 카테고리 나누기
	public ArrayList<SellerProductDTO> CateArticleList(int pageNum, int listLimit, String category) {
		ArrayList<SellerProductDTO> productCateList = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		
		int startRow = (pageNum - 1) * listLimit;
		try {
			String sql = "SELECT a.sell_num, a.sell_size , a.sell_category,a.sell_category_detail, a.sell_title, a.sell_color, a.sell_brand, a.sell_price, a.sell_readcount,"
					+ " b.sell_img_name, b.sell_img_real_name ,b.sell_img_real_num ,b.sell_img_num,b.sell_img_name,b.sell_img_real_name, c.sell_list_num, c.sell_list_item_status"
					+ " FROM sell AS a JOIN sell_img AS b ON a.sell_num = b.sell_img_real_num JOIN sell_list AS c ON a.sell_num = c.sell_list_num"
					+ " WHERE sell_list_item_status='판매중' AND sell_category =? AND "
					+ "(sell_img_real_num,sell_img_num)  in (SELECT sell_img_real_num, MAX(sell_img_num)  FROM sell_img    GROUP BY sell_img_real_num  ORDER BY sell_img_real_num ,sell_img_num DESC  )"
					+ " ORDER BY a.sell_num DESC LIMIT ?,? ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, category);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, listLimit);
				

			rs = pstmt.executeQuery();

			productCateList = new ArrayList<SellerProductDTO>();

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

				productCateList.add(article);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);

			close(rs);
		}
		return productCateList;

	}

	// 결제버튼 클릭시 배송지 Address table에 자동저장 ---> 기본 member에있는 주소는 마이페이지에서 수정가능
	public void insertAddress(SellerAddress address) {
		System.out.println("sellerDAO-insertAddress");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 0;
		try {
			String sql = "SELECT MAX(address_num) FROM address";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				num = rs.getInt(1) + 1;
			}

			sql = "INSERT INTO address VALUES(?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, address.getMember_code());
			pstmt.setString(3, address.getPost_code());
			pstmt.setString(4, address.getAddress_code());
			pstmt.setString(5, address.getAddress_detail());
			pstmt.setString(6, address.getAddress_name());
			pstmt.setString(7, address.getAddress_phone());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	

	// grade table의 모든 컬럼의 데이터를 가져와 등급 알아볼 때 기준으로 사용할 쿼리
	public ArrayList<MemberBean> memberGrade() {
		System.out.println("DAO 작업");
		ArrayList<MemberBean> memberArr = new ArrayList<MemberBean>();
		MemberBean bean = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			String sql = "SELECT * FROM grade LIMIT 0,7";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new MemberBean();
				bean.setGrade_name(rs.getString("grade_name"));
				bean.setLowest_acc_money(rs.getInt("lowest_acc_money"));
				bean.setHighest_acc_money(rs.getInt("highest_acc_money"));
				bean.setDiscount_rate(rs.getInt("discount_rate"));
				memberArr.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			close(rs);
			close(pstmt);
		}

		return memberArr;
	}

	// 기존 회원의 등급정보를 가져옴 (shoping_cart.jsp) 자신의 등급 알아보기 기능
	public MemberBean memberHasGrade(String member_code) {
		MemberBean member = new MemberBean();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT a.member_info_detail_acc_money ,b.grade_name, b.lowest_acc_money, b.highest_acc_money ,b.discount_rate"
					+ " FROM member_info_detail AS a JOIN grade AS b"
					+ " ON a.member_info_detail_acc_money BETWEEN b.lowest_acc_money AND b.highest_acc_money "
					+ " WHERE member_info_detail_code = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member_code);
			rs = pstmt.executeQuery();
			if (rs.next()) {

				member.setMember_info_detail_acc_money(rs.getInt("member_info_detail_acc_money"));
				member.setGrade_name(rs.getString("grade_name"));
				member.setLowest_acc_money(rs.getInt("lowest_acc_money"));
				member.setHighest_acc_money(rs.getInt("highest_acc_money"));
				member.setDiscount_rate(rs.getInt("discount_rate"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return member;
	}

	//구매완료 시, 배송지 정보 뿌려줄 기능
	public AddressVO getAddress() {
		AddressVO post = new AddressVO();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql =" SELECT * FROM address WHERE address_num=(select MAX(address_num) from address) ";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				post.setAddress_code(rs.getString("address_code"));
				post.setAddress_detail(rs.getString("address_detail"));
				post.setPost_code(rs.getString("post_code"));
				post.setAddress_name(rs.getString("address_name"));
				post.setAddress_phone(rs.getString("address_phone"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}

		return post;
	}

	public int recCeck(String sCode, int sell_num) { // 좋아요 확인 (true/false) 매서드
		int selectCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM like_list  " + " WHERE like_list_member_code = ? AND  like_list_item_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sCode);
			pstmt.setInt(2, sell_num);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				selectCount = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return selectCount;
	}

	public void recUpdate(String sCode, int sell_num) { // 좋아요 실행(INSERT)
		PreparedStatement pstmt = null;

		try {
			String sql = "INSERT INTO like_list VALUES(?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sCode);
			pstmt.setInt(2, sell_num);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void reDelete(String sCode, int sell_num) { // 좋아요 취소(DELETE)
		PreparedStatement pstmt = null;

		try {
			String sql = "DELETE FROM like_list " + " WHERE like_list_member_code = ? AND  like_list_item_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sCode);
			pstmt.setInt(2, sell_num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int likeCount(int sell_num) { // 판매번호를 통하여 좋아요 갯수 구하기
		int likeCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT COUNT(*) FROM like_list WHERE like_list_item_num= ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, sell_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				likeCount = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return likeCount;
	}

	
	// 상품 필터 브랜드별로 나누기
	public ArrayList<SellerProductDTO> BrandArticleList(int pageNum, int listLimit, String brand) {
		ArrayList<SellerProductDTO> productBrandList = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// 조회 시작 게시물 번호(행 번호) 계산
		int startRow = (pageNum - 1) * listLimit;
		System.out.println(brand);
		try {
			String sql = "SELECT a.sell_num, a.sell_size , a.sell_category,a.sell_category_detail, a.sell_title, a.sell_color, a.sell_brand, a.sell_price, a.sell_readcount,"
					+ " b.sell_img_name, b.sell_img_real_name ,b.sell_img_real_num ,b.sell_img_num,b.sell_img_name,b.sell_img_real_name, c.sell_list_num, c.sell_list_item_status"
					+ " FROM sell AS a JOIN sell_img AS b ON a.sell_num = b.sell_img_real_num JOIN sell_list AS c ON a.sell_num = c.sell_list_num"
					+ " WHERE sell_list_item_status='판매중' AND sell_brand =? AND "
					+ "(sell_img_real_num,sell_img_num)  in (SELECT sell_img_real_num, MAX(sell_img_num)  FROM sell_img    GROUP BY sell_img_real_num  ORDER BY sell_img_real_num ,sell_img_num DESC  )"
					+ " ORDER BY a.sell_num DESC LIMIT ?,? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, brand);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, listLimit);

			rs = pstmt.executeQuery();

			productBrandList = new ArrayList<SellerProductDTO>();

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

				productBrandList.add(article);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);

			close(rs);
		}
		return productBrandList;

	}
	// 상품 필터 가격별로 나누기
	public ArrayList<SellerProductDTO> PriceArticleList(int pageNum, int listLimit, int begin, int last) {
		ArrayList<SellerProductDTO> productPriceList = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// 조회 시작 게시물 번호(행 번호) 계산
		int startRow = (pageNum - 1) * listLimit;
	
		try {
			String sql = "SELECT a.sell_num, a.sell_size , a.sell_category,a.sell_category_detail, a.sell_title, a.sell_color, a.sell_brand, a.sell_price, a.sell_readcount,"
					+ " b.sell_img_name, b.sell_img_real_name ,b.sell_img_real_num ,b.sell_img_num,b.sell_img_name,b.sell_img_real_name, c.sell_list_num, c.sell_list_item_status"
					+ " FROM sell AS a JOIN sell_img AS b ON a.sell_num = b.sell_img_real_num JOIN sell_list AS c ON a.sell_num = c.sell_list_num"
					+ " WHERE sell_list_item_status='판매중' AND  " 
					+ " sell_price Between ? AND ? AND  "
					+ " (sell_img_real_num,sell_img_num)  in (SELECT sell_img_real_num, MAX(sell_img_num)  FROM sell_img    GROUP BY sell_img_real_num  ORDER BY sell_img_real_num ,sell_img_num DESC  )"
					+ " ORDER BY a.sell_num DESC LIMIT ?,? ";
					
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, begin);
			pstmt.setInt(2, last);
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, listLimit);
				
			rs = pstmt.executeQuery();

			productPriceList = new ArrayList<SellerProductDTO>();

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

				productPriceList.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return productPriceList;
	}
	
	
	// 메인 페이지 최근 상품 뿌리기
	public ArrayList<SellerProductDTO> selectMainArticleList(int pageNum, int listLimit) {
		ArrayList<SellerProductDTO> mainarticleList = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// 조회 시작 게시물 번호(행 번호) 계산
		int startRow = (pageNum - 1) * listLimit;

		try {
			String sql = "SELECT a.sell_num, a.sell_size , a.sell_category, a.sell_category_detail, a.sell_title, a.sell_color, a.sell_brand, a.sell_price, (SELECT COUNT(*) FROM like_list WHERE like_list_item_num= a.sell_num ) AS sell_likecount , a.sell_readcount, a.sell_member_code,"
					+ "	b.sell_img_name, b.sell_img_real_name ,b.sell_img_real_num ,b.sell_img_num,b.sell_img_name,b.sell_img_real_name,"
					+ "	c.sell_list_num, c.sell_list_item_status"
					+ "	FROM sell AS a JOIN sell_img AS b ON a.sell_num = b.sell_img_real_num JOIN sell_list AS c ON a.sell_num = c.sell_list_num"
					+ "	WHERE sell_list_item_status='판매중'  AND"
					+ "	(sell_img_real_num,sell_img_num)  in (SELECT  sell_img_real_num, MAX(sell_img_num)  FROM sell_img    GROUP BY sell_img_real_num  ORDER BY sell_img_real_num ,sell_img_num DESC  )"
					+ "	ORDER BY a.sell_num DESC LIMIT ?,?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, listLimit);

			rs = pstmt.executeQuery();

			mainarticleList = new ArrayList<SellerProductDTO>();

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
				article.setSell_likecount(rs.getInt("sell_likecount"));

				mainarticleList.add(article);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);

			close(rs);
		}
		return mainarticleList;
	}

	public ArrayList<SellerProductDTO> selectLikeArticleList() {
		ArrayList<SellerProductDTO> likearticleList = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "select a.sell_num, a.sell_size , a.sell_category, a.sell_category_detail, a.sell_title, a.sell_color, a.sell_brand, a.sell_price, (SELECT COUNT(*) FROM like_list WHERE like_list_item_num= a.sell_num ) AS sell_likecount , a.sell_readcount, a.sell_member_code,"
				+"	b.sell_img_name, b.sell_img_real_name ,b.sell_img_real_num ,b.sell_img_num,b.sell_img_name,b.sell_img_real_name, "
				+"	c.sell_list_num, c.sell_list_item_status"
				+"	FROM sell AS a JOIN sell_img AS b ON a.sell_num = b.sell_img_real_num JOIN sell_list AS c ON a.sell_num = c.sell_list_num"
				+"	WHERE sell_list_item_status='판매중'  AND (sell_brand) IN ( select b.member_info_detail_like_brand FROM sell AS A join member_info_detail AS b on a.sell_member_code = b. member_info_detail_code )"
				+" AND (sell_img_real_num,sell_img_num)  in (SELECT  sell_img_real_num, MAX(sell_img_num)  FROM sell_img    GROUP BY sell_img_real_num  ORDER BY sell_img_real_num ,sell_img_num DESC  )"
				+ "	ORDER BY a.sell_num DESC LIMIT 0,3";

			pstmt = con.prepareStatement(sql);


			rs = pstmt.executeQuery();

			likearticleList = new ArrayList<SellerProductDTO>();

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
				article.setSell_likecount(rs.getInt("sell_likecount"));

				likearticleList.add(article);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);

			close(rs);
		}
		return likearticleList;
	}

}