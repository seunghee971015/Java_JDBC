package com.kh.model.dao;


import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.common.JDBCTemplate;
import com.kh.model.vo.Shoes;

// DAO(Data Access Object) : DB에 직접적으로 접근해서 사용자의 요청에 맞는 sql문 실행 후 결과 반환(JDBC)
//						  	 결과를 Controller로 다시 리턴
public class ShoesDao {
	
	/*
	 * 기존방식 : DAO 클래스에 사용자가 요철할때마다 실행해야되는 sql문을 자바소스코드내에 명시적으로 작성 => 정적코딩방식
	 * 
	 *  > 문제점 : sql문을 수정해야될 경우 자바소스코드를 수정해야됨 => 수정된 내용을 반영시키고자 한다면 프로그램을 종류 후 재구동 해야됨
	 *  
	 *  > 해결방식 : sql문들을 별도로 관리하는 외부 파일(.xml)로 만들어서 실시간으로 그 파일에 기록된 sql문을 읽어들여서 실행 => 동적코딩방식
	 * 
	 * */
	private Properties prop = new Properties();
	
	public ShoesDao() {
		try {
			prop.loadFromXML(new FileInputStream("resources/query.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 사용자가 입력한 정보들을 db에 추가시켜주는 메소드
	 * @param m : 사용자가 입력한 값들이 담겨있는 member 객체
	 * @return : insert문 실행 후 처리된 행 수
	 */
	public int insertShoes(Connection conn, Shoes s) {
		//insert => 처리된 행 수
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertShoes");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, s.getpName());
			pstmt.setString(2, s.getBrand());
			pstmt.setInt(3, s.getShoeSize());
			pstmt.setInt(4, s.getPrice());
			pstmt.setInt(5, s.getStock());
			
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<Shoes> selectList(Connection conn) {
		// select문(여러행 조회) => ResultSet객체 => ArrayList에 담아 넘기기
		
		ArrayList<Shoes> list = new ArrayList<>(); //비어있는 상태
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Shoes s = new Shoes();
				s.setpCode(rset.getInt("PCODE"));
				s.setpName(rset.getString("PNAME"));
				s.setBrand(rset.getString("BRAND"));
				s.setShoeSize(rset.getInt("SHOE_SIZE"));
				s.setPrice(rset.getInt("PRICE"));
				
				list.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
		
	}
//
	public Shoes selectBypCode(Connection conn, String pCode) {
		
		//select문(한 행) => ResultSet객체 => Member 객체
		Shoes s = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectBypCode");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pCode);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				s = new Shoes();
				m.setUserNo(rset.getInt("USERNO"));
				m.setUserId(rset.getString("USERID"));
				m.setUserPwd(rset.getString("USERPWD"));
				m.setUserName(rset.getString("USERNAME"));
				m.setGender(rset.getString("GENDER"));
				m.setAge(rset.getInt("AGE"));
				m.setEmail(rset.getString("EMAIL"));
				m.setPhone(rset.getString("PHONE"));
				m.setAddress(rset.getString("ADDRESS"));
				m.setHobby(rset.getString("HOBBY"));
				m.setEnrollDate(rset.getDate("ENROLLDATE"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return m;
	}
//	
//	public ArrayList<Member> selectByUserName(Connection conn, String keyword) {
//		// select문(여러행) => ResultSet객체 => ArrayList 객체
//		
//		ArrayList<Member> list = new ArrayList<>(); //비어있는 상태
//		
//		PreparedStatement pstmt = null;
//		ResultSet rset = null;
//		
//		String sql = prop.getProperty("selectByUserName");
//		
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, keyword);
//			rset = pstmt.executeQuery();
//			
//			while(rset.next()) {
//				Member m = new Member();
//				m.setUserNo(rset.getInt("USERNO"));
//				m.setUserId(rset.getString("USERID"));
//				m.setUserPwd(rset.getString("USERPWD"));
//				m.setUserName(rset.getString("USERNAME"));
//				m.setGender(rset.getString("GENDER"));
//				m.setAge(rset.getInt("AGE"));
//				m.setEmail(rset.getString("EMAIL"));
//				m.setPhone(rset.getString("PHONE"));
//				m.setAddress(rset.getString("ADDRESS"));
//				m.setHobby(rset.getString("HOBBY"));
//				m.setEnrollDate(rset.getDate("ENROLLDATE"));
//				
//				list.add(m);
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			JDBCTemplate.close(rset);
//			JDBCTemplate.close(pstmt);
//		}
//		return list;
//	}
//	
//	public int updateMember(Connection conn, Member m) {
//		//update문 => 처리된 행수(int)
//		int result = 0;
//		
//		PreparedStatement pstmt = null;
//		String sql = prop.getProperty("updateMember");
//		
//		try {
//			pstmt = conn.prepareStatement(sql);
//			
//			pstmt.setString(1, m.getUserPwd());
//			pstmt.setString(2, m.getEmail());
//			pstmt.setString(3, m.getPhone());
//			pstmt.setString(4, m.getAddress());
//			pstmt.setString(5, m.getUserId());
//			
//			result = pstmt.executeUpdate();
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			JDBCTemplate.close(pstmt);
//			
//		}
//		return result;		                        
//		
//		
//	}
//	
//	public int deleteMember(Connection conn, String userId) {
//		// delete문(처리된 행 수)=> 반환
//		
//		int result = 0;
//		
//		PreparedStatement pstmt = null;
//		String sql = prop.getProperty("deleteMember");
//		
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, userId);
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			JDBCTemplate.close(pstmt);
//		}
//		return result;
//		
//	}
//	
//	public Member loginMember(Connection conn, String userId, String userPwd) {
//		//select문(한 행) => ResultSet객체 => Member 객체
//			Member m = null;
//			PreparedStatement pstmt = null;
//			ResultSet rset = null;
//			
//			String sql = prop.getProperty("loginMember");
//	
//			try {
//				pstmt = conn.prepareStatement(sql);
//				pstmt.setString(1, userId);
//				pstmt.setString(2, userPwd);
//				rset = pstmt.executeQuery();
//				
//				if(rset.next()) {
//					m = new Member();
//					m.setUserNo(rset.getInt("USERNO"));
//					m.setUserId(rset.getString("USERID"));
//					m.setUserPwd(rset.getString("USERPWD"));
//					m.setUserName(rset.getString("USERNAME"));
//					m.setGender(rset.getString("GENDER"));
//					m.setAge(rset.getInt("AGE"));
//					m.setEmail(rset.getString("EMAIL"));
//					m.setPhone(rset.getString("PHONE"));
//					m.setAddress(rset.getString("ADDRESS"));
//					m.setHobby(rset.getString("HOBBY"));
//					m.setEnrollDate(rset.getDate("ENROLLDATE"));
//				}
//				
//			} catch (SQLException e) {
//				e.printStackTrace();
//			} finally {
//				JDBCTemplate.close(rset);
//				JDBCTemplate.close(pstmt);
//			}
//			return m;
//			
//			
//	}
}