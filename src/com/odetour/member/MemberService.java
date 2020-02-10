package com.odetour.member;

public class MemberService {
	
	MemberDAO memberDAO;
	
	public MemberService() {
		memberDAO = new MemberDAO();
	}
	
	// 새로운 회원을 추가시키기 위한 메서드
	// 회원추가 성공(1) 실패(-1) 결과값을 컨트롤러로 리턴
	public int addMember(String name, String pwd1, String email, String birth, String tel) {
		return memberDAO.registerMember(name, pwd1, email, birth, tel);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
