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
	
	// 특정 회원을 검색했을 때 검색정보가 JSON형태로 출력이 되는것인데
	// 그것을 다시 파싱해서 분석한다음 우리에게 보여준다.
	// 요청에 대한 응답을 JSON형태로 받는데 이 서블릿이 해당 역할을 한다.
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
		System.out.println("\n■ result는 => "+result);
		return result.toString();
		
	}//getJSON
	
	
	
	
	
	
	
	
	
	
}//UserSearchServlet끝
