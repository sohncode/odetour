package com.odetour.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// [ 요청주소 ]
// 회원가입 => /joinMember.do => /addMember.do
// 회원수정
// 회원삭제
// 관리자

@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	
	MemberVO memberVO;
	MemberService memberService;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		memberService = new MemberService();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request,response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request,response);
	}
	
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String viewPage = "";
			
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		String action  = request.getPathInfo();
		System.out.println("\n■ action(요청주소) => "+action);
		
		try {
			
			if(action.equals("/joinMember.do")) {
				
				viewPage = "/user/joinForm.jsp";
			
			}else if(action.equals("/addMember.do")) {
				
				String name = request.getParameter("name");
		 		String pwd1 = request.getParameter("pwd1");
				String pwd2 = request.getParameter("pwd2");
				String email = request.getParameter("email");
				String birth = request.getParameter("birth");
				String tel = request.getParameter("tel");
				
				System.out.println("joinForm으로 부터 입력받은 정보\n"+name+"/"+pwd1+"/"+pwd2+"/"+email+"/"+birth+"/"+tel);
				
				//입력하지 않았을 경우 오류처리
//				if(name==null || name.equals("") || pwd1==null || pwd1.equals("") 
//						|| pwd2==null || pwd2.equals("") || email==null || email.equals("") 
//						|| birth==null || birth.equals("") || tel==null || tel.equals("") ) { 
//				   
//					request.getSession().setAttribute("messageType", "오류 메세지");
//					request.getSession().setAttribute("messageContent", "모든 내용을 입력하세요.");
//					response.sendRedirect("../index.jsp");
//					return;
//				}
//				
//				if(pwd1.equals(pwd2)) {  
//					request.getSession().setAttribute("messageType", "오류 메세지");
//					request.getSession().setAttribute("messageContent", "비밀번호가 서로 일치하지 않습니다.");
//					response.sendRedirect("../index.jsp");
//					return;
//				}
//				
				
				// 성공적으로 회원정보를 입력받으면
				System.out.println("성공적으로 회원정보를 입력받음");
				
				int result = memberService.addMember(name,pwd1,email,birth,tel);
				
				if(result==1) {
					PrintWriter pw = response.getWriter();
					pw.print("<script>");
					pw.print("alert('회원가입 성공');");
					pw.print("</script>");
					return;

//					request.getSession().setAttribute("messageType", "성공 메세지");
//					request.getSession().setAttribute("messageContent", "회원가입에 성공하였습니다.");
//					response.sendRedirect("index.jsp");
//					return;
					
				}else {
					
					PrintWriter pw = response.getWriter();
					pw.print("<script>");
					pw.print("alert('이미 존재하는 회원입니다.');");
					pw.print("</script>");
					return;
					
//					request.getSession().setAttribute("messageType", "오류 메세지");
//					request.getSession().setAttribute("messageContent", "이미 존재하는 회원입니다.");
//					response.sendRedirect("index.jsp");
//					return;
				}
			
			
			}else if(action.equals("infoMember")) {
				
			}
			
			// 포워딩
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);			
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			System.out.println("doHandle()내부오류"+e);
		}
	
		
		
	}//doHandle()메서드 끝
	
}//컨트롤러 끝
	
	
