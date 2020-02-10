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

// [ ��û�ּ� ]
// ȸ������ => /joinMember.do => /addMember.do
// ȸ������
// ȸ������
// ������

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
		System.out.println("\n�� action(��û�ּ�) => "+action);
		
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
				
				System.out.println("joinForm���� ���� �Է¹��� ����\n"+name+"/"+pwd1+"/"+pwd2+"/"+email+"/"+birth+"/"+tel);
				
				//�Է����� �ʾ��� ��� ����ó��
//				if(name==null || name.equals("") || pwd1==null || pwd1.equals("") 
//						|| pwd2==null || pwd2.equals("") || email==null || email.equals("") 
//						|| birth==null || birth.equals("") || tel==null || tel.equals("") ) { 
//				   
//					request.getSession().setAttribute("messageType", "���� �޼���");
//					request.getSession().setAttribute("messageContent", "��� ������ �Է��ϼ���.");
//					response.sendRedirect("../index.jsp");
//					return;
//				}
//				
//				if(pwd1.equals(pwd2)) {  
//					request.getSession().setAttribute("messageType", "���� �޼���");
//					request.getSession().setAttribute("messageContent", "��й�ȣ�� ���� ��ġ���� �ʽ��ϴ�.");
//					response.sendRedirect("../index.jsp");
//					return;
//				}
//				
				
				// ���������� ȸ�������� �Է¹�����
				System.out.println("���������� ȸ�������� �Է¹���");
				
				int result = memberService.addMember(name,pwd1,email,birth,tel);
				
				if(result==1) {
					PrintWriter pw = response.getWriter();
					pw.print("<script>");
					pw.print("alert('ȸ������ ����');");
					pw.print("</script>");
					return;

//					request.getSession().setAttribute("messageType", "���� �޼���");
//					request.getSession().setAttribute("messageContent", "ȸ�����Կ� �����Ͽ����ϴ�.");
//					response.sendRedirect("index.jsp");
//					return;
					
				}else {
					
					PrintWriter pw = response.getWriter();
					pw.print("<script>");
					pw.print("alert('�̹� �����ϴ� ȸ���Դϴ�.');");
					pw.print("</script>");
					return;
					
//					request.getSession().setAttribute("messageType", "���� �޼���");
//					request.getSession().setAttribute("messageContent", "�̹� �����ϴ� ȸ���Դϴ�.");
//					response.sendRedirect("index.jsp");
//					return;
				}
			
			
			}else if(action.equals("infoMember")) {
				
			}
			
			// ������
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);			
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			System.out.println("doHandle()���ο���"+e);
		}
	
		
		
	}//doHandle()�޼��� ��
	
}//��Ʈ�ѷ� ��
	
	
