package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.service.ShoesService;
import com.kh.model.vo.Shoes;
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
	public void selectBypCode(String pCode) {
		new ShoesService().selectBypCode(pCode);
		
		if(m == null) {
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
//	public void selectByUserName(String keyword) {
//	      ArrayList<Member> list = new ShoesService().selectByUserName(keyword);
//	      
//	      if(list.isEmpty()) {
//	    	  new ShoesMenu().displayNoData(keyword + "에 해당하는 검색 결과가 없습니다.");
//	      } else {
//	    	  new ShoesMenu().displayMemberList(list);
//	      }
//	   }
//	
//	public void updateMember(String userId, String userPwd,
//			String email, String phone, String address) {
//		Member m = new Member();
//		m.setUserId(userId);
//		m.setUserPwd(userPwd);
//		m.setEmail(email);
//		m.setPhone(phone);
//		m.setAddress(address);
//		
//		int result = new ShoesService().updateMember(m);
//		
//		if(result > 0) {
//			new ShoesMenu().displaySuccess("성공적으로 수정하였습니다.");
//		} else {
//			new ShoesMenu().displayFail("변경 실패");
//		}
//	}
//
//	public void deleteMember(String userId) {
//		int result = new ShoesService().deleteMember(userId);
//		
//		if(result > 0) {
//			new ShoesMenu().displaySuccess(userId + "회원 삭제 성공");
//		} else {
//			new ShoesMenu().displayFail("회원 삭제 실패");
//		}
//	}
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