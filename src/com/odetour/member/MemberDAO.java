package com.odetour.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

// [ �޼��� ������ ���� ]
// getConnection() => DB���� �޼��� 
// closeDB() => �ڿ����� �޼���

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
	//=> �̸��� �ߺ�üũ �޼���
		public int registerCheck(String email) {
			
			try {
				con = getConnection();
				
				sql = "select * from member where email=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, email);
				rs = pstmt.executeQuery();
				
				if(rs.next() || email.equals("")) {
					return 0; // �̹� �����ϴ� �̸���
				}else {
					return 1; // ���� ������ �̸���
				}
				
			} catch (Exception e) {
				System.out.println("registerCheck()�޼��� ���ο���"+e);
			} finally {
				closeDB();
			}
			
			return -1; // �����ͺ��̽� ����
		}
	//========================= registerCheck() =========================
	
	
	

		
	//========================= registerMember(memberVO) =========================
	//=> ȸ���� �߰��ϴ� �޼���
	public int registerMember(String name, String pwd1, String email, String birth, String tel) {
		System.out.println("���񽺷� ���� �Ѱܹ��� ����\n"+name+"/"+pwd1+"/"+email+"/"+birth+"/"+tel);
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
			
			return pstmt.executeUpdate(); // 1�� ȸ���߰� => 1��ȯ(���񽺷�)
			
		} catch (Exception e) {
			System.out.println("registerMember()�޼��� ����"+e);
		} finally {
			closeDB();
		}
		
		return -1; // ���� 
	}
	//========================= registerMember(memberVO) =========================
	
	
	
	
	
	//========================= memberSearch(name) =========================
	public ArrayList<MemberVO> memberSearch(String name) {
		System.out.println("memberSearch�� �ѿ��� name:"+name);
		ArrayList<MemberVO> memberList = new ArrayList<MemberVO>();
		
		try {
			
			con = getConnection();
			
			sql = "select * from member where name like ?"; //�Ű������� �Ѿ�� ���� �����Ѵٸ� �����ȯ 
			
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
			System.out.println("memberSearch(String name) �޼��� ����"+e);
		} finally {
			closeDB();
		}
		System.out.println("\n �� memberList�� => "+memberList);
		return memberList;
	}
	//========================= memberSearch(name) =========================
	
	
	
	
	
	
	
	
}//MemberDAO��
