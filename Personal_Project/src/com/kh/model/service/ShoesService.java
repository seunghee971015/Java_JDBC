package com.kh.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.JDBCTemplate;
import com.kh.model.dao.ShoesDao;
import com.kh.model.vo.Shoes;

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
//	public Member selectByUserId(String userId) {
//		Connection conn = JDBCTemplate.getConnection();
//		Member m = new ShoesDao().selectByUserId(conn, userId);
//		JDBCTemplate.close(conn);
//		
//		return m;
//	}
//	
//	public ArrayList<Member> selectByUserName(String keyword) {
//		Connection conn = JDBCTemplate.getConnection();
//		ArrayList<Member> list = new ShoesDao().selectByUserName(conn, keyword);
//		JDBCTemplate.close(conn);
//		
//		return list;
//	}
//	
//	public int updateMember(Member m) {
//		Connection conn = JDBCTemplate.getConnection();
//		int result = new ShoesDao().updateMember(conn, m);
//		
//		if(result > 0) {
//			JDBCTemplate.commit(conn);
//		} else {
//			JDBCTemplate.rollback(conn);
//		}
//		return result;
//	}
//	
//	public int deleteMember(String userId) {
//		Connection conn = JDBCTemplate.getConnection();
//		int result = new ShoesDao().deleteMember(conn, userId);
//		
//		if(result > 0) {
//			JDBCTemplate.commit(conn);
//		} else {
//			JDBCTemplate.rollback(conn);
//		}
//		JDBCTemplate.close(conn);
//		return result;
//	}
//	
//	public Member loginMember(String userId, String userPwd) {
//		Connection conn = JDBCTemplate.getConnection();
//		Member m = new ShoesDao().loginMember(conn, userId, userPwd);
//		JDBCTemplate.close(conn);
//		
//		return m;
//	}
}
