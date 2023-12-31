package com.kh.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.controller.ShoesController;
import com.kh.model.vo.Shoes;
import com.kh.model.vo.ShoesDetail;

//view : 사용자가 보게될 시각적인 요소(화면) 출력 및 입력
public class ShoesMenu {
	
	//Scanner 객체 생성(전역적으로 다 입력 받을 수 있도록)
	private Scanner sc = new Scanner(System.in);
	
	//ShoesController 객체 생성(전역에서 바로 요청할 수 있도록)
	private ShoesController sct = new ShoesController();
	
	
	
	public void mainMenu() {

		
		while(true) {
			
			System.out.println("\n==신발 관리 프로그램==");
			System.out.println("1. 입고된 신발 추가");
			System.out.println("2. 신발 전체 조회");
			System.out.println("3. 상품번호로 신발 조회");
			System.out.println("4. 브랜드 이름으로 신발 검색");
			System.out.println("5. 신발 삭제");
			System.out.println("6. 신발 정보 변경");
			System.out.println("7. 재고 수량 확인");
			System.out.println("8. 입고 업로드");
			System.out.println("9. 출고 업로드");
			System.out.println("10. 입출고 현황");
			System.out.println("0. 프로그램 종료");
			
			System.out.print(">> 메뉴 선택 : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) {
			case 1 : //신발 추가
				inputShoes();
				break;
			case 2 : //신발 전체 조회
				sct.selectList();
				break;
			case 3 : //상품 번호 조회
				//int pCode = inputpCode();
				//sct.selectBypCode(pCode);
				sct.selectBypCode(inputpCode());
				break;
			case 4 : // 브랜드 이름으로 키워드 검색
				//String brand = inputShoeBrand();
				//sct.selectByUserName(brand);
				sct.selectByBrand(inputShoeBrand());
				break;
			case 5 : // 신발 삭제
				
				//int pCode = inputpCode();
				//mc.deleteMember(pCode);
				sct.deleteShoes(inputpCode());
				break;
			case 6 : // 신발 정보 변경
				//pCode
				//상품명, 브랜드, 사이즈, 가격
				updateShoes();
				break;
			case 7 : //재고확인
				sct.selectByStock(inputpCode());
				break;
			case 8 : //입고 업로드
				updateProductStore();
				break;
			case 9 : //출고 업로드
				updateProductRelease();
				break;
			case 10 : //입출고 상태 확인
				sct.storeStatus();
				break;
			case 0 : // 프로그램 종료(메서드 빠져나감)
				System.out.println("신발 관리 프로그램을 종료합니다.");
				return;
			
			default :
				System.out.println("메뉴를 잘못입력하셨습니다. 다시 입력해주세요.");
			}
				
		}
	}

	public void inputShoes() {
		
		System.out.println("\n=== 새로운 신발 추가 ===");
		
		System.out.print("상품명(한글로 입력) : ");
		String pName = sc.nextLine();
		
		System.out.print("브랜드 명(한글로 입력) : ");
		String barnd = sc.nextLine();
		
		System.out.print("사이즈 : ");
		int shoeSize = sc.nextInt();
		sc.nextLine();
		
		System.out.print("가격(숫자만 입력) : ");
		int price = sc.nextInt();
		sc.nextLine();
		
		System.out.print("수량 : ");
		int stock = sc.nextInt();
		sc.nextLine();
		
		
		// 신발 추가 요청 == Controller메서드 요청
		sct.insertShoes(pName, barnd, shoeSize, price, stock);
		
		
	}

	public int inputpCode() {
		System.out.println("\n상품 번호 입력");
		return sc.nextInt();
	}

	public String inputShoeBrand() {	
		System.out.println("\n브랜드 명 입력");
		return sc.nextLine();
	}
	
	public void updateShoes() {
		System.out.println("\n===신발 정보 변경 ===");
		
		//(찾기위한)코드, 상품명, 브랜드, 사이즈, 가격
		int pCode = inputpCode();
		
		System.out.println("변경할 상품명");
		String pName = sc.next();
		
		System.out.println("변경할 브랜드명");
		String brand = sc.next();
		
		System.out.println("변경할 사이즈");
		int shoeSize = sc.nextInt();
		sc.nextLine();
		
		System.out.println("변경할 가격");
		int price = sc.nextInt();
		sc.nextLine();
		
		sct.updateShoes(pCode, pName, brand, shoeSize, price);
	}

public void updateProductStore() {
		
		System.out.println("\n=== 입고 업로드 ===");
		
		System.out.print("신발 코드 : ");
		int pCode = sc.nextInt();
		sc.nextLine();
		
		System.out.print("수량 : ");
		int amount = sc.nextInt();
		sc.nextLine();
		
		System.out.print("입고를 원하시면 '입고'를 입력해주세요 : ");
		String status = sc.next();	
		
		// 입고 추가 요청 == Controller메서드 요청
		sct.updateProductStroe(pCode, amount, status);
		
		
	}

public void updateProductRelease() {
	
	System.out.println("\n=== 출고 업로드 ===");
	// 신발 코드, 수량, 상태 입력받기
	
	System.out.print("신발 코드 : ");
	int pCode = sc.nextInt();
	sc.nextLine();
	
	System.out.print("수량 : ");
	int amount = sc.nextInt();
	sc.nextLine();
	
	System.out.print("출고를 원하시면 '출고'를 입력해주세요 : ");
	String status = sc.next();	
	
	// 출고 요청 == Controller메서드 요청
	sct.updateProductRelease(pCode, amount, status);
	
}

//	//------------------------------응답화면-------------------------------------------
//	/**
//	 *  서비스 요청 처리 후 성공했을 경우 사용자가 보게될 응답화면
//	 * @param message : 객체별 성공메세지
//	 */
	public void displaySuccess(String message) {
		System.out.println("\n서비스 요청 성공 : " + message);
	}
	
	/**
	 * 서비스 요청 처리 후 실패했을 경우 사용자가 보게될 응답화면
	 * @param message : 객체별 실패메세지
	 */
	public void displayFail(String message) {
		System.out.println("\n서비스 요청 실패 : " + message );
	}
//	                                       
//	/**
//	 * 조회 서비스 요청시 조회결과가 없을 때 사용자가 보게될 응답화면
//	 * @param message : 조회결과에대한 응답메세지
//	 */
	public void displayNoData(String message) {
		System.out.println("\n" + message);
	}
//	/**
//	 * 조회 서비스 요청시 조회 결과가 여러행일 경우 사용자가 보게될 응답화면
//	 * @param list : 출력할 shoes들이 담겨있는 list
//	 */
	public void displayShoesList(ArrayList<Shoes> list) {
		System.out.println("\n조회된 데이터는 다음과 같습니다");
		
		for(Shoes s : list) {
			System.out.println(s);
		}
	}
	         
	public void displayShoes(Shoes s) {
		System.out.println("\n조회된 데이터는 다음과 같습니다.");
		System.out.println(s);
	}
	
	public void displayLoginFail(String message) {
		System.out.println(message);
	}
//	
	public void displayLoginSuccess(String message) {
		System.out.println(message);
	}
	
	public void displayShoesStock(Shoes s) {
		System.out.println("재고 : " + s.getStock() + "개 남아있습니다.");
	}
	
	public void displayStoreSuccess(String message) {
		System.out.println(message);
	}
	public void displayStoreFail(String message) {
		System.out.println(message);
	}
	
	public void displayReleaseSuccess(String message) {
		System.out.println(message);
	}
	public void displayReleaseFail(String message) {
		System.out.println(message);
	}
	
	public void displayStoreList(ArrayList<ShoesDetail> list) {
		System.out.println("\n요일별 입고, 출고 현황");
		
		for(ShoesDetail sd : list) {
			System.out.println(sd);
		}
	}
}
