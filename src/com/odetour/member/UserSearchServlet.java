package com.odetour.member;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UserSearchServlet")
public class UserSearchServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		String name = request.getParameter("name");
		
		response.getWriter().write(getJSON(name));
	
	}//doPost
	
	// Ư�� ȸ���� �˻����� �� �˻������� JSON���·� ����� �Ǵ°��ε�
	// �װ��� �ٽ� �Ľ��ؼ� �м��Ѵ��� �츮���� �����ش�.
	// ��û�� ���� ������ JSON���·� �޴µ� �� ������ �ش� ������ �Ѵ�.
	public String getJSON(String name) {
		if(name == null) name = "";
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		
		MemberDAO memberDAO = new MemberDAO();
		ArrayList<MemberVO> memberList = memberDAO.memberSearch(name);
		
		for(int i=0;i<memberList.size();i++) {
			result.append("[{\"idx\": \"" + memberList.get(i).getIdx() + "\"},");
			result.append("{\"name\": \"" + memberList.get(i).getName() + "\"},");
			result.append("{\"email\": \"" + memberList.get(i).getEmail() + "\"},");
			result.append("{\"point\": \"" + memberList.get(i).getPoint() + "\"},");
			result.append("{\"tel\": \"" + memberList.get(i).getTel() + "\"}],");
		}
		result.append("]}");
		System.out.println("\n�� result�� => "+result);
		return result.toString();
		
	}//getJSON
	
	
	
	
	
	
	
	
	
	
}//UserSearchServlet��
