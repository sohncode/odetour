package com.odetour.member;

public class MemberService {
	
	MemberDAO memberDAO;
	
	public MemberService() {
		memberDAO = new MemberDAO();
	}
	
	// ���ο� ȸ���� �߰���Ű�� ���� �޼���
	// ȸ���߰� ����(1) ����(-1) ������� ��Ʈ�ѷ��� ����
	public int addMember(String name, String pwd1, String email, String birth, String tel) {
		return memberDAO.registerMember(name, pwd1, email, birth, tel);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
