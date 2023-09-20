package com.kh.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.JDBCTemplate;
import com.kh.model.dao.ShoesDao;
import com.kh.model.vo.Shoes;
import com.kh.model.vo.ShoesDetail;

public class ShoesService {
	// 1) JDBC driver등록
	// 2) Connection 객체생성
	// 3) Connection 객체 처리
	
	public int insertShoes(Shoes s) {
		
		Connection conn = JDBCTemplate.getConnection();
		 int result = new ShoesDao().insertShoes(conn, s);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		return result;
	}
	
	public ArrayList<Shoes> selectList() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Shoes> list = new ShoesDao().selectList(conn);
		JDBCTemplate.close(conn);
		
		return list;
	}
//	
	public Shoes selectBypCode(int pCode) {
		Connection conn = JDBCTemplate.getConnection();
		Shoes s = new ShoesDao().selectBypCode(conn, pCode);
		JDBCTemplate.close(conn);
		
		return s;
	}
	
	public ArrayList<Shoes> selectByBrand(String brand) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Shoes> list = new ShoesDao().selectByBrand(conn, brand);
		JDBCTemplate.close(conn);
		
		return list;
	}
	
	public int updateShoes(Shoes s) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new ShoesDao().updateShoes(conn, s);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		return result;
	}
	
	public int deleteShoes(int pCode) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new ShoesDao().deleteShoes(conn, pCode);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	
	public Shoes selectByStock(int pCode) {
		Connection conn = JDBCTemplate.getConnection();
		Shoes s = new ShoesDao().selectBypCode(conn, pCode);
		JDBCTemplate.close(conn);
		
		return s;
	}
	
	public int updateProductStore(ShoesDetail sd) {
		
		Connection conn = JDBCTemplate.getConnection();
		 int result = new ShoesDao().updateProductStore(conn, sd);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		return result;
	}
	
public int updateProductRelease(ShoesDetail sd) {
		
		Connection conn = JDBCTemplate.getConnection();
		 int result = new ShoesDao().updateProductStore(conn, sd);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		return result;
	}
//	
//	public Member loginMember(String userId, String userPwd) {
//		Connection conn = JDBCTemplate.getConnection();
//		Member m = new ShoesDao().loginMember(conn, userId, userPwd);
//		JDBCTemplate.close(conn);
//		
//		return m;
//	}
}
