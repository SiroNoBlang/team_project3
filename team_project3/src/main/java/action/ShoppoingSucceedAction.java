package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.InsertBuyMemberService;
import svc.SellRecentPostAddressService;
import svc.SellerBuyInsert;
import vo.ActionForward;
import vo.MemberBean;
import vo.SellerAddress;
import vo.SellerProductDTO;

public class ShoppoingSucceedAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ShoppoingSucceedAction- UPDATE 작업 진행");
		ActionForward forward = null;
		
		int updateCount =0;
		int insertCount =0;
		/*
		 * 판매제품 && 구매 회원 정보(주소,이름,전화번호 등 ) && 구매자 정보(Buy_list table)   3가지 작업 필요!
		 * */
		String member_code = request.getParameter("member_code");
		int sell_num = Integer.parseInt(request.getParameter("sell_num"));
		
		int sell_price =Integer.parseInt(request.getParameter("sell_price"));
		
		//sellerAddress 에 업데이트 해주기.
		String address1= request.getParameter("address1");	//구매자 정보
		String postcode=request.getParameter("postcode");	//구매자 정보	
		String address2 =request.getParameter("address2");	//구매자 정보
		String name =request.getParameter("name");			//구매자 정보
		String phone =request.getParameter("phone");		//구매자 정보
		
		int usePoint = Integer.parseInt(request.getParameter("point"));
		//int savePoint =  Integer.parseInt(request.getParameter("member_point"))-usePoint; //실제로 사용하고 남은 포인트 저장하는 변수
		
		int member_info_detail_acc_money=Integer.parseInt(request.getParameter("member_info_detail_acc_money")) ;
		
	//	address1&postcode&address2&name&phone&name=4800
//		System.out.println("address1" +address1);
//		System.out.println("postcode" +postcode);
//		System.out.println("address2" +address2);
//		System.out.println("name" +name);
//		System.out.println("phone" +phone);
//		System.out.println("savePoint" +usePoint);          //원래 가격 -usePoint 해야됨.
////		System.err.println("member_code:"+member_code);
//		System.out.println("member_info_detail_acc_money" +member_info_detail_acc_money);  //sell_price 값 += 추가해야됨.
		
//--------------------------------------------------------------------------------------------------------------------------------------------		
		MemberBean memberBean = new MemberBean(); //값 담기(구매 회원 정보)	
		//articleList 
		
		SellerBuyInsert service = new SellerBuyInsert();
		//------------------------------------ member_info 테이블
		memberBean.setMember_info_address(address1);	//업데이트 할 컬럼들
		memberBean.setMember_info_phone(phone);			
		memberBean.setMember_info_post_code(postcode);
		memberBean.setMember_info_address_detail(address2);
		memberBean.setMember_info_name(name); 		
		memberBean.setMember_info_detail_point(usePoint);  
		memberBean.setMember_info_detail_acc_money(member_info_detail_acc_money);
		memberBean.setMember_code(member_code);
		System.out.println("memberBean ===>"+memberBean);
		//------------------------------------ address  테이블
		SellerAddress address = new SellerAddress(); //배송지 정보 담기(최근 배송지 찾기 기능) ㅡ
		address.setMember_code(member_code); //구매자 코드
		address.setAddress_code(address1); //구매자 주소지
		address.setAddress_detail(address2);  //구매자 상세 주소
		address.setPost_code(postcode);		  //구매자 우편번호
		address.setAddress_name(name);
		address.setAddress_phone(phone);
			
		SellRecentPostAddressService addressService  = new SellRecentPostAddressService();
		//파라미터:address 리턴받을 값:void
		
		//------------------------------------ Buy 테이블
		SellerProductDTO sellerDTO = new SellerProductDTO();    //구매자가 구매한 판매제품 상세하게 뿌리기 (jsp file 로 가져감)
		sellerDTO.setBuy_member_code(member_code);
		sellerDTO.setBuy_item_num(sell_num);
		sellerDTO.setBuy_price(sell_price);
		int addpoint= (int) (sell_price*0.05);// point => 판매가격의 0.05퍼 추가
		sellerDTO.setBuy_point(addpoint);
		//System.out.println("action에서"+sellerDTO);
		InsertBuyMemberService service1 = new InsertBuyMemberService();
		
		
//----------------------------------------------------------------------------------------------------------------		
		insertCount = service1.insertBuyMember(sellerDTO);//구매회원정보 insert 해줄 곳
		addressService.insertAddress(address);    // address객체에 담아 insert하기(최근배송지 기능) 
		sellerDTO = service.getShoping(sell_num);  		//판매제품 가져오는 곳
		updateCount = service.updateMemberInfo(memberBean);  //멤버정보값들 update 해주는 곳 
			
		service.sellUpdate(sell_num);   //받을값 없음  ->구매시 sell_list ->status 값 판매중 -> 판매완료로 변경
		//----------------------------------------------------------------------------------------------------------------	
		MemberBean memberBean1 = new MemberBean();
		
		
		memberBean1 = service.getArticleMemberInfo(member_code);
//		System.out.println("ㅂㅈㄷㄱㄱㅈㄷ"+memberBean1);
//		System.out.println("ㅂㅈㄷㄱㄱㅈㄷ"+sellerDTO);
		request.setAttribute("memberBean", memberBean1);    //업데이트한 구매자 데이터
		request.setAttribute("sellerDTO", sellerDTO);		//판매제품 데이터
		
		forward = new ActionForward();
		forward.setPath("SucceedProduct.pr");
		forward.setRedirect(false);
		
		return forward;
	}

}
