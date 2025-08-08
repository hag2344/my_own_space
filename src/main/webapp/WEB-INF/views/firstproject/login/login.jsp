<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.nhs.firstProject.common.vo.UserInfoVo"%> 
<%@page import="java.util.HashMap"%>
<%@ page import="com.nhs.firstProject.common.ComConstant"%>
<!DOCTYPE html>
<html lang="ko" dir="ltr">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <meta name="viewport">
	<title>로그인 | 첫번째 프로젝트</title>
	
	<link rel="icon" href="<%=ComConstant.CONTEXT_ROOT %>/resources/img/logo/favicon.png"/> 
	<link rel="apple-touch-icon" href="<%=ComConstant.CONTEXT_ROOT %>/resources/img/logo/favicon.png"/> 
	<link rel="stylesheet" href="<%=ComConstant.CONTEXT_ROOT %>/resources/css/login/login.css">
	<script type="text/javascript" src="<%=ComConstant.CONTEXT_ROOT %>/resources/js/login/login.js"></script>
	
	<link rel="stylesheet" href="<%=ComConstant.CONTEXT_ROOT %>/resources/jquery/jquery-ui-1.12.1.min.css">
    <script type="text/javascript" src="<%=ComConstant.CONTEXT_ROOT %>/resources/jquery/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="<%=ComConstant.CONTEXT_ROOT %>/resources/jquery/jquery-ui-1.12.1.min.js"></script>
    <script type="text/javascript" src="<%=ComConstant.CONTEXT_ROOT %>/resources/jquery/datepicker-ko.js"></script>
    
    <link rel="stylesheet" href="<%=ComConstant.CONTEXT_ROOT %>/resources/css/sweetalert2.min.css">
	<script type="text/javascript" src="<%=ComConstant.CONTEXT_ROOT %>/resources/js/sweetalert2.min.js"></script>
    
	
	<script type="text/javascript">
		
		function loginCheck(){
			if($("#userId").val() == null || $("#userId").val()==''){
				
				Swal.fire({
					title: '아이디를 입력하시기 바랍니다.',
			        icon: 'warning',
			        confirmButtonText: '확인'
			    });

				return;
			}
			if($("#userBimilbun").val() == null || $("#userBimilbun").val()==''){
				Swal.fire({
					title: '비밀번호을 입력하시기 바랍니다.',
			        icon: 'warning',
			        confirmButtonText: '확인'
			    });
				return;
			}
			var param = {
				strUserId  : $("#userId").val(),
				strBimilbun : $("#userBimilbun").val(),
				strLoginType : "PC"
			}
			$.ajax({
				method : "post",
				url : "loginCheck.do",
				data : param,
				dataType : "JSON",
				success : sucLoginCheck,
				error : errLoginCheck
			});
		}
		
		function sucLoginCheck(result) {
			objUserCheckInfo = result.returnData;
			if(null == objUserCheckInfo){
				Swal.fire({
			        title: '로그인에 실패하였습니다.',
			        text: '입력하신 ID, 패스워드를 확인하세요.',
			        icon: 'error',
			        confirmButtonText: '확인'
			    });
			}else if(null != objUserCheckInfo && objUserCheckInfo.loginResult == 1) {
				$(location).attr('href','/menu.do'); 
			}else{
				Swal.fire({
			        title: objUserCheckInfo.msgTitle,
			        text: objUserCheckInfo.failReason,
			        icon: 'error',
			        confirmButtonText: '확인'
			    });
			}	
		}
		
		function errLoginCheck (xhr, status, error){
			Swal.fire({
		        title: '로그인 중 오류가 발생하였습니다.',
		        icon: 'error',
		        confirmButtonText: '확인'
		    });
		}	
	
		function beforeAjax(xmlHttpRequest) {
			xmlHttpRequest.setRequestHeader("AJAX", "true"); //ajax호출을 header에 기록
		}
	</script>
</head>
<body>
    <div class="login-container">
        <h2>로그인</h2>

        <div class="input-group">
            <span class="icon">👤</span>
            <input type="text" class="input-field" name="userId" id="userId" placeholder="아이디" 
                   onkeypress="if(event.keyCode == 13) loginCheck()" required>
        </div>

        <div class="input-group">
            <span class="icon">🔒</span>
            <input type="password" class="input-field" name="userBimilbun" id="userBimilbun" placeholder="비밀번호" 
                   onkeypress="if(event.keyCode == 13) loginCheck()" required>
        </div>

        <button type="submit" class="login-button" onclick="loginCheck()">로그인</button>

        <div class="forgot-password">
            <a href="/idFind.do">아이디 찾기</a> | <a href="/bimilbunFind.do">비밀번호 찾기</a>
        </div>
        <div class="signup-link">
            <a href="signUp.do">회원가입</a>
        </div>
    </div>
</body>
</html>