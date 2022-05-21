package dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import db.JdbcUtil.*;

import vo.SellerDTO;

import static db.JdbcUtil.*;

public class SellerDAO {
	
	private static SellerDAO instance = new SellerDAO();
	
	public SellerDAO() {}

	public static SellerDAO getInstance() {
		return instance;
	};

	static Connection con;

	public void setConnection(Connection con) {
		this.con = con;
	}

	public  int insertArticle(SellerDTO seller) {  //판매글 작성
		
		
		
		// INSERT 작업 결과를 리턴받아 저장할 변수 선언
		int insertCount = 0;
		int num =0;
		
		PreparedStatement pstmt= null;
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
				pstmt.setInt(11, 0); //조회수 컬럼
			
				sql = "INSERT INTO sell_img VALUES ((SELECT MAX(SELL_NUM) FROM sell),?,?)";
				pstmt2 = con.prepareStatement(sql);
//				pstmt2.setInt(1, seller.getSell_img_num()); 
//				pstmt2.setInt(2, seller.getSell_img_num_detail()); 
				pstmt2.setString(1, seller.getSell_img_name()); 
				pstmt2.setString(2, seller.getSell_img_real_name()); 
				        
				sql = "INSERT INTO sell_list VALUES ((SELECT MAX(SELL_NUM) FROM sell), '판매중', ?, 'ㅎㅇ')";
				pstmt3 = con.prepareStatement(sql);
				pstmt3.setString(1, seller.getSell_list_approve_date());
			
				insertCount =pstmt.executeUpdate();
				insertCount =pstmt2.executeUpdate();
				insertCount = pstmt3.executeUpdate();
				System.out.println("BoardDAO - insertArticle()1");
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
	//shop 클릭시 데이터 뿌리기 작업(검수완료된 데이터 갯수 세기)
	public int selectListCount() {
		System.out.println("SellerDAO - selectListCount()");
		
		int listCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT COUNT(sell_list_num) FROM sell_list"
					+ " WHERE sell_list_item_status ='판매중' ";   //나중엔  sell_list_item_status ='판매중'로 바꿔야됨 
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
			System.out.println("판매가능 상품 갯수:"+listCount);
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

	public static ArrayList<SellerDTO> selectArticleList(int pageNum, int listLimit) {
		System.out.println("selectArticleList()ArrayList 객체 가져오기");
		
		ArrayList<SellerDTO> articleList = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		
		// 조회 시작 게시물 번호(행 번호) 계산
		int startRow = (pageNum - 1) * listLimit;
		
		
			// 게시물 목록 조회
			// => 답글에 대한 처리
			//    참조글번호(board_re_ref) 기준 내림차순, 
			//    순서번호(board_re_seq) 기준 오름차순 정렬
			// => 조회 시작 게시물 번호(startRow) 부터 목록의 게시물 수(listLimit) 만큼 조회
			try {
				
				String sql = "SELECT a.sell_num, a.sell_size , a.sell_category,a.sell_category_detail, a.sell_title, a.sell_color, a.sell_brand, a.sell_price, a.sell_readcount, b.sell_img_name, b.sell_img_real_name, c.sell_list_num, c.sell_list_item_status "
		                     + "FROM sell AS a JOIN sell_img AS b ON a.sell_num = b.sell_img_num JOIN sell_list AS c ON a.sell_num = c.sell_list_num WHERE sell_list_item_status='판매중' "
		                     + "ORDER BY a.sell_num DESC "
		                     + "LIMIT ?,? ";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, listLimit);
			
				rs = pstmt.executeQuery();
				
				
			
				
//				sql = "SELECT a.sell_title, a.sell_brand, a.sell_price, a.sell_color, a.sell_readcount, b.sell_img_name, c.sell_list_num, c.sell_list_item_status, b.sell_img_real_name"
//							+ "FROM sell AS a JOIN sell_img AS b ON a.sell_num = b.sell_img_num JOIN sell_list AS c ON a.sell_num = c.sell_list_num "
//							+ "WHERE sell_list_item_status='판매중'";
							//join을 사용하자
							//sell   ->	조회수, 타이틀,브랜드
							//sell_list -> 
							//sell_img -> 일단 사진은 다 사용해야됨
							//이외에 좋아요 까지
				
				articleList = new ArrayList<SellerDTO>();
				
				while(rs.next()) {
					SellerDTO article = new SellerDTO();
					   article.setSell_num(rs.getInt("sell_num"));
					   article.setSell_size(rs.getString("sell_size"));
		               article.setSell_title(rs.getString("sell_title"));
		               article.setSell_price(rs.getInt("sell_price"));
		               article.setSell_color(rs.getString("sell_color"));
		               article.setSell_brand(rs.getString("sell_brand"));
		               article.setSell_readcount(rs.getInt("sell_readcount"));
		               article.setSell_list_num(rs.getInt("sell_list_num"));
		               article.setSell_list_item_status(rs.getString("sell_list_item_status"));
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

	public  SellerDTO selectArticle(int sell_num) {
		SellerDTO article = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT  a.sell_category, a.sell_category_detail, a.sell_size, a.sell_title, a.sell_color, a.sell_brand, a.sell_price, a.sell_readcount, b.sell_img_name, b.sell_img_real_name, c.sell_list_num ,c.sell_list_item_status, c.sell_list_approve_nickname "
			        + " FROM sell AS a JOIN sell_img AS b ON a.sell_num = b.sell_img_num JOIN sell_list AS c ON a.sell_num = c.sell_list_num WHERE c.sell_list_num =? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, sell_num);
			rs = pstmt.executeQuery();
			
			
//			SELECT a.sell_num, a.sell_category, a.sell_category_detail, a.sell_title, a.sell_color, 
//			a.sell_brand, a.sell_price, a.sell_readcount, 
//			b.sell_img_name, b.sell_img_real_name, c.sell_list_num

			if(rs.next()) {
				article = new SellerDTO();
				
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
				article.setSell_list_approve_nickname(rs.getString("sell_list_approve_nickname"));
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
	
	
	//조회수 증가 위한 작업
	
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

	public ArrayList<SellerDTO> selectProductRe(String sell_brand,int sell_num) {
		ArrayList<SellerDTO> productarr = new ArrayList<SellerDTO>();
			SellerDTO ProductRe = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
		
		try {
			String sql = "SELECT a.sell_num, a.sell_size ,  a.sell_title, a.sell_brand, a.sell_price, b.sell_img_name, b.sell_img_real_name "
                    + "FROM sell AS a JOIN sell_img AS b ON a.sell_num = b.sell_img_num  WHERE sell_num != "+sell_num+" AND sell_brand Like '%"+sell_brand+"%' ";
                    
			pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, sell_brand);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ProductRe = new SellerDTO();
				ProductRe.setSell_num(rs.getInt("sell_num"));
				ProductRe.setSell_size(rs.getString("sell_size"));
				ProductRe.setSell_title(rs.getString("sell_title"));
				ProductRe.setSell_brand(rs.getString("sell_brand"));
				ProductRe.setSell_price(rs.getInt("sell_price"));
				ProductRe.setSell_img_name(rs.getString("sell_img_name"));
				ProductRe.setSell_img_real_name(rs.getString("sell_img_real_name"));
				
				productarr.add(ProductRe);
			}
		
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! - updateReadcount()");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return productarr;
	}
}
