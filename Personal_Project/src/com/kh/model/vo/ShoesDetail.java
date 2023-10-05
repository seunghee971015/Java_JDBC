package com.kh.model.vo;

import java.sql.Date;

public class ShoesDetail{
	private int deCode; //이력번호
	private int pCode; //상품번호
	private Date pDate; //입출고일
	private int amount; //입출고수량
	private String status; //상태(입고, 출고)

	public ShoesDetail() {
		super();
	}
	
	public ShoesDetail(int deCode, int pCode, Date pDate, int amount, String status) {
		super();
		this.deCode = deCode;
		this.pCode = pCode;
		this.pDate = pDate;
		this.amount = amount;
		this.status = status;
	}
	public ShoesDetail(int pCode, int stock) {
		super();
	}
	
	public ShoesDetail(int pCode, int amount, String status) {
		super();
		this.amount = amount;
		this.pCode = pCode;
		this.status = status;
	}

	public int getDeCode() {
		return deCode;
	}

	public void setDeCode(int deCode) {
		this.deCode = deCode;
	}

	public int getpCode() {
		return pCode;
	}

	public void setpCode(int pCode) {
		this.pCode = pCode;
	}

	public Date getpDate() {
		return pDate;
	}

	public void setpDate(Date pDate) {
		this.pDate = pDate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "이력번호: " + deCode + " " + "상품번호: " + pCode + " " + "입출고 날짜: " + pDate +
				" " + "입출고 수량:  " + amount + " " + "상태: " + status;
	}


	
	
}

