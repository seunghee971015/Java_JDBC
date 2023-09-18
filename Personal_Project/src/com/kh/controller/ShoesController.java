package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.service.ShoesService;
import com.kh.model.vo.Shoes;
import com.kh.model.vo.ShoesDetail;
import com.kh.view.ShoesMenu;

//Controller : View를 통해서 사용자가 요청한 기능에 대해서 처리하는 담당
//				해당 메서드로 전달된 데이터 [가공처리후] dao로 전달하며 호출
//				dao로부터 반환받은 결과에 따라서 성공인지 실패인지 판단 후 응답화면 결정(View 메서드 호출)
public class ShoesController {
	

	public void insertShoes(String pName, String brand, int shoeSize ,int price, int stock) {
		
		Shoes s = new Shoes(pName, brand, shoeSize, price, stock);
		
		int result = new ShoesService().insertShoes(s);
		
		if(result > 0) {
			new ShoesMenu().displaySuccess("성공적으로 신발 추가를 완료하였습니다.");
		} else {
			new ShoesMenu().displayFail("신발 추가에 실패하였습니다.");
		}
		
	}
	
	/**
	 * 사용자의 회원 전체 조회 요청을 처리해주는 메소드
	 */
	public void selectList() {
		ArrayList<Shoes> list = new ShoesService().selectList();
		
		if(list.isEmpty()) {
			new ShoesMenu().displayNoData("전체 조회 결과가 업습니다.");
		} else {
			new ShoesMenu().displayMemberList(list);
		}
		
	}
//
//	/**
//	 * 사용자의 아이디로 회원 검색 요청을 처리해주는 메소드
//	 * @param userId : 사용자가 입력한 검색하고자하는 회원 아이디
//	 */
	public void selectBypCode(int pCode) {
		Shoes s = new ShoesService().selectBypCode(pCode);
		
		if(s == null) {
			new ShoesMenu().displayNoData(pCode + "에 해당하는 조회 결과가 없습니다.");
		} else {
			new ShoesMenu().displayShoes(s);
		}
	}
//
//	/**
//	 * 이름으로 키워드 검색 요청시 처리해주는 메소드
//	 * @param keyword : 사용자가 입력한 검색할 키워드명
//	 */
	public void selectByBrand(String keyword) {
	      ArrayList<Shoes> list = new ShoesService().selectByBrand(keyword);
	      
	      if(list.isEmpty()) {
	    	  new ShoesMenu().displayNoData(keyword + "에 해당하는 검색 결과가 없습니다.");
	      } else {
	    	  new ShoesMenu().displayMemberList(list);
	      }
	   }
//	
	public void updateShoes(int pCode, String pName,
			String brand, int shoeSize, int price) {
		Shoes s = new Shoes();
		s.setpCode(pCode);
		s.setpName(pName);
		s.setBrand(brand);
		s.setShoeSize(shoeSize);
		s.setPrice(price);
		
		int result = new ShoesService().updateShoes(s);
		
		if(result > 0) {
			new ShoesMenu().displaySuccess("성공적으로 수정하였습니다.");
		} else {
			new ShoesMenu().displayFail("변경 실패");
		}
	}

	public void deleteShoes(int pCode) {
		int result = new ShoesService().deleteShoes(pCode);
		
		if(result > 0) {
			new ShoesMenu().displaySuccess(pCode + "신발 삭제 성공");
		} else {
			new ShoesMenu().displayFail("신발 삭제 실패");
		}
	}
	
	public void selectByStock(int pCode) {
		Shoes s = new ShoesService().selectBypCode(pCode);
		
		if(s == null) {
			new ShoesMenu().displayNoData(pCode + "에 해당하는 조회 결과가 없습니다.");
		} else {
			new ShoesMenu().displayShoesStock(s);
		}
	} 
	
	public void updateStroe(int pCode, int stock, String status) {
		
		ShoesDetail sd = new ShoesDetail(pCode, stock, status);
		
		int result = new ShoesService().updateStore(sd);
		
		if(result > 0) {
			new ShoesMenu().displayStoreSuccess("성공적으로 입고를 완료하였습니다.");
		} else {
			new ShoesMenu().displayStoreFail("입고에 실패하였습니다.");
		}
		
	}
//	
//	public void loginMember(String userId, String userPwd) {
//		Member m = new ShoesService().loginMember(userId, userPwd);
//		
//		if(m != null) {
//			new ShoesMenu().displayLoginSuccess("로그인 성공");
//
//		} else {
//			new ShoesMenu().displayLoginFail("로그인 실패");
//		}
//	}

}