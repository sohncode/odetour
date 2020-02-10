package com.odetour.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

// [ 메서드 순서와 설명 ]
// getConnection() => DB연결 메서드 
// closeDB() => 자원해제 메서드

public class MemberDAO {
	
	private Connection con;
	private DataSource ds;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private String sql;
	
	
	//====================== getConnection() ======================
	
	private Connection getConnection() throws Exception {
		
		Context init = new InitialContext(); 
		
		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/odetour");
		
		con = ds.getConnection();
		
		return con;
	}
	//====================== getConnection() ======================
	
	
	
	//========================= closeDB() =========================
	public void closeDB() {
		try {
			if(rs != null) rs.close(); 
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//========================= closeDB() =========================
	
	
	
	
	
	//========================= registerCheck() =========================
	//=> 이메일 중복체크 메서드
		public int registerCheck(String email) {
			
			try {
				con = getConnection();
				
				sql = "select * from member where email=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, email);
				rs = pstmt.executeQuery();
				
				if(rs.next() || email.equals("")) {
					return 0; // 이미 존재하는 이메일
				}else {
					return 1; // 가입 가능한 이메일
				}
				
			} catch (Exception e) {
				System.out.println("registerCheck()메서드 내부오류"+e);
			} finally {
				closeDB();
			}
			
			return -1; // 데이터베이스 오류
		}
	//========================= registerCheck() =========================
	
	
	

		
	//========================= registerMember(memberVO) =========================
	//=> 회원을 추가하는 메서드
	public int registerMember(String name, String pwd1, String email, String birth, String tel) {
		System.out.println("서비스로 부터 넘겨받은 정보\n"+name+"/"+pwd1+"/"+email+"/"+birth+"/"+tel);
		try {
			
			con = getConnection();
			
			sql = "insert into member(name,pwd,email,birth_year,birth_month,birth_day,point,tel) values(?,?,?,?,?,?,?,?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, pwd1);
			pstmt.setString(3, email);
			pstmt.setString(4, birth.substring(0,4));
			pstmt.setString(5, birth.substring(5,7));
			pstmt.setString(6, birth.substring(8));
			pstmt.setInt(7, 1000);
			pstmt.setString(8, tel);
			
			return pstmt.executeUpdate(); // 1명 회원추가 => 1반환(서비스로)
			
		} catch (Exception e) {
			System.out.println("registerMember()메서드 오류"+e);
		} finally {
			closeDB();
		}
		
		return -1; // 오류 
	}
	//========================= registerMember(memberVO) =========================
	
	
	
	
	
	//========================= memberSearch(name) =========================
	public ArrayList<MemberVO> memberSearch(String name) {
		System.out.println("memberSearch로 넘오온 name:"+name);
		ArrayList<MemberVO> memberList = new ArrayList<MemberVO>();
		
		try {
			
			con = getConnection();
			
			sql = "select * from member where name like ?"; //매개변수로 넘어온 값을 포함한다면 결과반환 
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberVO memberVO = new MemberVO();
				
				memberVO.setIdx(rs.getInt("idx"));
				memberVO.setName(rs.getString("name"));
				memberVO.setPwd(rs.getString("pwd"));
				memberVO.setEmail(rs.getString("email"));
				memberVO.setBirth_year(rs.getString("birth_year"));
				memberVO.setBirth_month(rs.getString("birth_month"));
				memberVO.setBirth_day(rs.getString("birth_day"));
				memberVO.setPoint(rs.getInt("point"));
				memberVO.setTel(rs.getString("tel"));
				
				memberList.add(memberVO);
				System.out.println(memberVO);
				
			}
			
		} catch (Exception e) {
			System.out.println("memberSearch(String name) 메서드 오류"+e);
		} finally {
			closeDB();
		}
		System.out.println("\n ■ memberList는 => "+memberList);
		return memberList;
	}
	//========================= memberSearch(name) =========================
	
	
	
	
	
	
	
	
}//MemberDAO끝
