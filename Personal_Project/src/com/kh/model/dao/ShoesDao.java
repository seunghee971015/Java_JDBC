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
import com.kh.model.vo.ShoesDetail;

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
				s.setStock(rset.getInt("STOCK"));
				
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

	public Shoes selectBypCode(Connection conn, int pCode) {
		
		//select문(한 행) => ResultSet객체 => Member 객체
		Shoes s = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selctBypCode");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pCode);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				s = new Shoes();
				s.setpCode(rset.getInt("PCODE"));
				s.setpName(rset.getString("PNAME"));
				s.setBrand(rset.getString("BRAND"));
				s.setShoeSize(rset.getInt("SHOE_SIZE"));
				s.setPrice(rset.getInt("PRICE"));
				s.setStock(rset.getInt("STOCK"));
			}
			/*PCODE
			PNAME
			BRAND
			SHOE_SIZE
			PRICE
			STOCK*/
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return s;
	}

	public ArrayList<Shoes> selectByBrand(Connection conn, String brand) {
		// select문(여러행) => ResultSet객체 => ArrayList 객체
		
		ArrayList<Shoes> list = new ArrayList<>(); //비어있는 상태
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectByBrand");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, brand);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Shoes s = new Shoes();
				s.setpCode(rset.getInt("PCODE"));
				s.setpName(rset.getString("PNAME"));
				s.setBrand(rset.getString("BRAND"));
				s.setShoeSize(rset.getInt("SHOE_SIZE"));
				s.setPrice(rset.getInt("PRICE"));
				s.setStock(rset.getInt("STOCK"));
				
				list.add(s);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}
	
	public int updateShoes(Connection conn, Shoes s) {
		//update문 => 처리된 행수(int)
		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateShoes");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, s.getpName());
			pstmt.setString(2, s.getBrand());
			pstmt.setInt(3, s.getShoeSize());
			pstmt.setInt(4, s.getPrice());
			pstmt.setInt(5, s.getpCode());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			
		}
		return result;		                        
		
		
	}
//	
	public int deleteShoes(Connection conn, int pCode) {
		// delete문(처리된 행 수)=> 반환
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteShoes");
		String sql2 = prop.getProperty("deleteShoes2");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pCode);
			
			result = pstmt.executeUpdate();
			
			
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, pCode);
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
		
	}

	public Shoes selectByStock(Connection conn, int pCode) {
		
		//select문(한 행) => ResultSet객체 => Member 객체
		Shoes s = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selctBypCode");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pCode);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				s = new Shoes();
				s.setpCode(rset.getInt("PCODE"));
				s.setpName(rset.getString("PNAME"));
				s.setBrand(rset.getString("BRAND"));
				s.setShoeSize(rset.getInt("SHOE_SIZE"));
				s.setPrice(rset.getInt("PRICE"));
				s.setStock(rset.getInt("STOCK"));
			}
			/*PCODE
			PNAME
			BRAND
			SHOE_SIZE
			PRICE
			STOCK*/
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return s;
	}

	public int updateProductStore(Connection conn, ShoesDetail sd) {
		//insert => 처리된 행 수
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateProductStore");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sd.getpCode());
			pstmt.setInt(2, sd.getAmount());
			pstmt.setString(3, sd.getStatus());
			
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public int updateProductRelease(Connection conn, ShoesDetail sd) {
		//insert => 처리된 행 수
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateStore");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sd.getpCode());
			pstmt.setInt(2, sd.getAmount());
			pstmt.setString(3, sd.getStatus());
			
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public ArrayList<ShoesDetail> storeStatus(Connection conn) {
		// select문(여러행 조회) => ResultSet객체 => ArrayList에 담아 넘기기
		
		ArrayList<ShoesDetail> list = new ArrayList<>(); //비어있는 상태
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("storeStatus");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				ShoesDetail sd = new ShoesDetail();
				sd.setDeCode(rset.getInt("DECODE"));
				sd.setpCode(rset.getInt("PCODE"));
				sd.setpDate(rset.getDate("PDATE"));
				sd.setAmount(rset.getInt("AMOUNT"));
				sd.setStatus(rset.getString("STATUS"));
				
				/*
				DECODE
				PCODE
				PDATE
				AMOUNT
				STATUS
				*/
				
				
				list.add(sd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
		
	}
	
}
