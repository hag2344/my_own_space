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
	<title>아이디 찾기 | 그룹웨어</title>
	
	<link rel="icon" href="<%=ComConstant.CONTEXT_ROOT %>/resources/img/logo/favicon.png"/> 
	<link rel="apple-touch-icon" href="<%=ComConstant.CONTEXT_ROOT %>/resources/img/logo/favicon.png"/> 
	<link rel="stylesheet" href="<%=ComConstant.CONTEXT_ROOT %>/resources/css/login/login.css">
	
	<link rel="stylesheet" href="<%=ComConstant.CONTEXT_ROOT %>/resources/jquery/jquery-ui-1.12.1.min.css">
    <script type="text/javascript" src="<%=ComConstant.CONTEXT_ROOT %>/resources/jquery/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="<%=ComConstant.CONTEXT_ROOT %>/resources/jquery/jquery-ui-1.12.1.min.js"></script>
    <script type="text/javascript" src="<%=ComConstant.CONTEXT_ROOT %>/resources/jquery/datepicker-ko.js"></script>
    
    <link rel="stylesheet" href="<%=ComConstant.CONTEXT_ROOT %>/resources/css/sweetalert2.min.css">
	<script type="text/javascript" src="<%=ComConstant.CONTEXT_ROOT %>/resources/js/sweetalert2.min.js"></script>
	
	<script type="text/javascript">
		
		function sendVerificationCode(){
			if($("#userName").val() == null || $("#userName").val()==''){
				
				Swal.fire({
					title: '이름을 입력하시기 바랍니다.',
			        icon: 'warning',
			        confirmButtonText: '확인'
			    });

				return;
			}
			if($("#userEmail").val() == null || $("#userEmail").val()==''){
				Swal.fire({
					title: '이메일을 입력하시기 바랍니다.',
			        icon: 'warning',
			        confirmButtonText: '확인'
			    });
				return;
			}
			var param = {
				userName  : $("#userName").val(),
				userEmail : $("#userEmail").val()
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
    <div class="login-container" style ="width: 380px;">
        <h2>아이디 찾기</h2>
        
        <div class="input-group small-input">
            <span class="icon">👤</span>
            <input type="text" class="input-field" name="userName" id="userName" maxlength="40" placeholder="이름" required>
        </div>
        <div class="verify-group" title="본인확인 이메일 주소와 입력한 이메일 주소가 같아야, 인증번호를 받을 수 있습니다.">
        	<div class="input-group small-input">
            	<span class="icon">📧</span>
            	<input type="email" class="input-field" name="userEmail" id="userEmail" maxlength="100" placeholder="이메일 주소" required>                	
        	</div>
        	<button type="button" class="verify-button verify-abs" onclick="sendVerificationCode()">인증번호 받기</button>
        </div>
        
        <div class="input-group small-input verify-disabled">
            <span class="icon">🔢</span>
            <input type="text" class="input-field" name="verificationCode" id="verificationCode" maxlength="6" placeholder="인증번호 6자리 숫자 입력" maxlength="6" required disabled>
        </div>
        
        <button type="submit" class="login-button" onclick="findId()">아이디 찾기</button>
        
        <div class="login-link">
            <a href="/login.do">로그인 페이지로 돌아가기</a>
        </div>
    </div>
</body>
</html>