package com.kh.model.vo;

/*
 * * VO(value object)
 * 	 한 명의 회원(DB테이블의 한 행의 데이터)에 대한 데이터를 기록할 수 있는 저장용 객체
 * */
public class Shoes {
	//필드
	//필드는 기본적으로 DB컬럼명과 유사하게 작업
	private int pCode; //상품번호
	private String pName; //상품명
	private String brand; //브랜드
	private int shoeSize; //사이즈
	private int price; //가격
	private int stock; //재고
	
	//생성자
	public Shoes() {
		super();
	}
	
	public Shoes(int pCode, String pName, String brand, int shoeSize, int price, int stock) {
		this.pCode = pCode;
		this.pName = pName;
		this.brand = brand;
		this.shoeSize = shoeSize;
		this.price = price;
		this.stock = stock;
	}
	public Shoes(String pName, String brand, int shoeSize, int price, int stock) {
		this.pName = pName;
		this.brand = brand;
		this.shoeSize = shoeSize;
		this.price = price;
		this.stock = stock;
	}


	public int getpCode() {
		return pCode;
	}

	public void setpCode(int pCode) {
		this.pCode = pCode;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getShoeSize() {
		return shoeSize;
	}

	public void setShoeSize(int shoeSize) {
		this.shoeSize = shoeSize;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "상품번호: " + pCode + "번, " + "상품명: " + pName + ", 브랜드명: " + brand + ", 사이즈: " + shoeSize
				+ "size, 가격: " + price + "원, 재고: " + stock + "개";
	}
	

	

}
