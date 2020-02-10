<%@ page language="java" contentType="text/html; charset=UTF-8"
    	 pageEncoding="UTF-8"%>
    	 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--컨텍스트 주소 저장 --%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<% request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <script type="js/bootstrap.js"></script>
   <title>odeTour 회원가입</title>
   
   <link rel="stylesheet" href="//code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" />
   <script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
   <script src="//code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
   
   
         <!-- Bootstrap CSS -->
      <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
      
      
<!--    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
 -->    <%-- <link href="${contextPath}/css/bootstrap.css" rel="stylesheet">
      <link href="${contextPath}/css/custom.css" rel="stylesheet"> --%>
   <style type="text/css">
   </style>
   <script type="text/javascript">
   var passCheck = false;
   var emailCheck = false;
   var emailAuthCheck = false;
   var form = document.memberForm;
   
      function allPassCheck(){
         if(passCheck == false){
            alert("비밀번호를 동일하게 입력하여 주세요.");
         }
         else if(emailCheck == false){
            alert("이메일 중복 체크를 완료하여 주세요");
         }
         else if(emailAuthCheck == false){
            alert("이메일 인증을 완료하여 주세요");
         }
         form.action = "${contextPath}/member/addMember.do";
         form.method = "post";
         form.submit();
      }

      $(function() {
        $( "#datepicker1" ).datepicker({
          dateFormat: 'yy.mm.dd',
          prevText: '이전 달',
          nextText: '다음 달',
          monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
          monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
          dayNames: ['일','월','화','수','목','금','토'],
          dayNamesShort: ['일','월','화','수','목','금','토'],
          dayNamesMin: ['일','월','화','수','목','금','토'],
          showMonthAfterYear: true,
          changeMonth: false,
          changeYear: true,
          yearRange: "-100:+0"
        });
      });
      
      function pass_Check(){
         if($("#pwd1").val() != ""){
            if($("#pwd1").val() == $("#pwd2").val()){
               $("#passwdCheckResult").css("color","green");
               $("#passwdCheckResult").text("비밀번호 일치");
               passCheck= true;
            }
            else{
               $("#passwdCheckResult").css("color","red");
               $("#passwdCheckResult").text("비밀번호 불일치");
               passCheck= false;
            }
         }
      }    
      // 공백 체크 후 제거
      function spaceCheck(obj){
         var text = $(obj).val().replace(/\s/gi,"");
         $(obj).val(text);
       }
      //이메일 형식 및 중복 검사
      function emailTest(){
         var email = $("#email").val();
         var emailDomain = $("#emailDomain").val();
         var totalEmail = email + emailDomain;
         var getMail = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);
         if (!getMail.test(totalEmail)) {
            window.alert("이메일 형식에 맞게 입력하여 주세요");
            $("#emailCheckResult").css("color","red");
            $("#emailCheckResult").text("이메일 사용 불가");
            return false;
         }
         else{
            $.ajax(
                  {
                     type:"post", //요청방식을 설정 합니다(post 또는 get 방식으로 선택)
                       url:"${contextPath}/member/emailDuplChk.do",//요청할 서블릿의 매핑주소 또는 JSP의 주소 
                       data:{"email" : $('#email').val(),
                            "emailDomain" : $("#emailDomain").val()},//서블릿 또는 JSP페이지로 요청할 데이터 설정
                       success:function(data){
                             if(data == "0"){
                              $("#emailCheckResult").css("color","green");
                              $("#emailCheckResult").text("이메일 사용 가능");
                              emailCheck = true;
                           }
                           else{
                              $("#emailCheckResult").css("color","red");
                              $("#emailCheckResult").text("이메일 사용 불가");
                              emailCheck = false;
                           }
                       }
                  }
            );
         }
      }
      function emailAuthTag(){
         var email = $("#email").val();
         var emailDomain = $("#emailDomain").val();
         var totalEmail = email + emailDomain;
         if(emailCheck == false){
            alert("이메일 중복체크를 완료하여 주세요");            
         }
         else{
            const str = 
               '<td style="width:110px;"><h5>인증번호</h5></td>' + 
               '<td>'+
               '<div class="input-group mb-3">'+
               '<input class="form-control" type="text" id="userAuthNum" name="userAuthNum" maxlength="20" placeholder="6자리 이상의 정수 입력">'+
               '<div class="input-group-append">'+
               '<input class="btn btn-primary" onclick="emailAuthNumCheck();" type="button" value="인증번호 작성 완료">'+
               '</div></div>'+
               '</td>';
            $("#emailAuthTag").append(str);
            $("#emailAuthButton").attr("disabled","disabled");
            $.ajax(
                  {
                       type:"post", //요청방식을 설정 합니다(post 또는 get 방식으로 선택)
                       url:"${contextPath}/member/emailAuthNumSend.do",//요청할 서블릿의 매핑주소 또는 JSP의 주소 
                       data:{"totalEmail" :totalEmail},//서블릿 또는 JSP페이지로 요청할 데이터 설정
                       success:function(data){
                    	  adminAuthNum = data;
                          alert("이메일 인증번호 전송" + data);
                        }
                  }
            );
         }
      }
      function emailAuthNumCheck(){
         var userAuthNum = $("#authNum").val();
		 alert("adminAuthNum : " + adminAuthNum);
         if(userAuthNum.equals(adminAuthNum)){
        	 alert("인증번호 확인 완료");
	         $("#emailAuthCheckResult").css("color","green");
	         $("#emailAuthCheckResult").text("이메일 인증 완료");
	         emailAuthCheck = true;
	     }
         else{
        	 alert("인증번호를 확인하여 주세요");
         }
     }
   </script>
</head>
<body>
   <div class="container">
       <form action="" method="post" name="memberForm">
          <table class="table table-bordered table-hover" style="text-align: center; border: 1px solid #dddddd;">
             <thead>
               <tr>
                  <th colspan="3" style="text-align: center;"><h2>회원 가입</h2></th>
               </tr>
             </thead>
             <tbody>
                <tr>             
                   <td style="width: 110px;"><h5>이름</h5></td>
                   <td colspan="2"><input class="form-control" type="text" id="name" name="name" maxlength="20" onkeyup="spaceCheck(this);"></td>
                </tr>
                <tr>             
                   <td style="width: 110px;"><h5>비밀번호</h5></td>
                   <td colspan="2"><input class="form-control" type="password" id="pwd1" name="pwd1" maxlength="20" onkeyup="pass_Check();"></td>
                </tr>
                <tr>
                   <td style="width: 110px;"><h5>비밀번호 확인</h5></td>
                   <td colspan="2"><input class="form-control" type="password" id="pwd2" name="pwd2" maxlength="20" onkeyup="pass_Check();"></td>
                </tr>
                <tr>
                    <td colspan="2"><span id="passwdCheckResult"></span></td>
                </tr>
                <tr>             
                   <td style="width: 110px;"><h5>이메일</h5></td>
                   <td>
		               <div class="input-group mb-3">
		                  <input class="form-control" type="text" id="email" name="email" maxlength="20" onkeyup="spaceCheck(this);">
		                  <div class="input-group-append">
		                       <select class="custom-select" name="emailDomain" id="emailDomain">
		                            <option value="@naver.com">naver.com</option>
		                            <option value="@daum.net">daum.net</option>
		                            <option value="@google.com">google.com</option>
		                         </select>
		                  </div>
		               </div>
                   </td>
                </tr>
               <tr id="emailAuthTag">
               </tr>
               <tr>
                  <td colspan="2" style="width: 20px;"><button class="btn btn-primary" onclick="emailTest();" type="button">이메일 중복체크</button>
                                             &nbsp;<button class="btn btn-primary" onclick="emailAuthTag();" type="button" id="emailAuthButton">이메일 인증</button></td>
               </tr>
               <tr>
                  <td colspan="2"><span id="emailCheckResult"></span> &nbsp; <span id="emailAuthCheckResult"></span></td>
               </tr>
                <tr>             
                   <td style="width: 110px;"><h5>생년월일</h5></td>
                   <td colspan="2"><input class="form-control" type="text" id="datepicker1" name="birth" maxlength="20" readonly></td>
                </tr>
                <tr>             
                   <td style="width: 110px;"><h5>전화번호</h5></td>
                   <td colspan="2"><input class="form-control" type="text" id="tel" name="tel" maxlength="20" onkeyup="spaceCheck(this);" placeholder="숫자만 입력하여주세요"></td>
                </tr>
                <tr>             
                   <td style="text-align: left;" colspan="3"><input class="btn btn-primary pull-right" type="button" value="회원가입" onclick="allPassCheck();"></td>
                </tr>
             </tbody>
          </table>
       </form>
    </div>
</body>
</html>