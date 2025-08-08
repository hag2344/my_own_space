<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.nhs.firstProject.common.vo.UserInfoVo"%> 
<%@page import="java.util.HashMap"%>
<%@page import="com.nhs.firstProject.common.ComConstant"%>
<%@page import="org.slf4j.Logger"%>
<%@page import="org.slf4j.LoggerFactory"%>
<%@page import="java.net.URL"%>
<%@page import="java.util.Properties"%>
<%
	Logger LOGGER = LoggerFactory.getLogger("com.nhs.firstProject");
	ClassLoader currentThreadClassLoader = Thread.currentThread().getContextClassLoader();
	URL url = currentThreadClassLoader.getResource("config/properties/linkage.properties");
	Properties prop = new Properties();
	prop.load(url.openStream());
	
	String strEnv = prop.getProperty("server.environment") == null ? "devel" : prop.getProperty("server.environment");
	String astrU = request.getRequestURL().toString();
%>
<!DOCTYPE html>
<html lang="ko" dir="ltr">
<head>
<title>Error | FirstProject</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<meta name="viewport">
<!-- ** Site Information -->
<meta name="author" content="Gioinfra">
<meta name="description" content="Gioinfra Intro Page : Login">
<meta name="subject" content="Gioinfra Intro Page">
<meta name="classification" content="html">
<meta name="keywords" content="gioinfra, intro page, Login">
<meta name="robots" content="ALL">
<meta name="og:image" content="">
<meta name="og:description" content="Gioinfra Intro Page : Login">
<meta name="og:title" content="Gioinfra Intro Page">
<meta name="twitter:title" content="Gioinfra Intro Page">
<!-- ** Favicon -->

<!-- ** jQuery CDN -->
<link rel="stylesheet" href="<%=ComConstant.CONTEXT_ROOT %>/resources/jquery/jquery-ui-1.12.1.min.css">
<script type="text/javascript" src="<%=ComConstant.CONTEXT_ROOT %>/resources/jquery/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="<%=ComConstant.CONTEXT_ROOT %>/resources/jquery/jquery-ui-1.12.1.min.js"></script>
<script type="text/javascript" src="<%=ComConstant.CONTEXT_ROOT %>/resources/jquery/datepicker-ko.js"></script>
<!-- ** Font Awesome CDN -->
<!-- Our project just needs Font Awesome Solid + Brands -->
<link rel="stylesheet" href="<%=ComConstant.CONTEXT_ROOT %>/resources/fontawesome/css/fontawesome.min.css">
<link rel="stylesheet" href="<%=ComConstant.CONTEXT_ROOT %>/resources/fontawesome/css/brands.min.css">
<link rel="stylesheet" href="<%=ComConstant.CONTEXT_ROOT %>/resources/fontawesome/css/solid.min.css">
<!-- ** CSS & JS -->
<!-- Noto Sans CJK KR -->
<link rel="stylesheet" href="<%=ComConstant.CONTEXT_ROOT %>/resources/css/noto-sans-cjk-kr.css">
<!-- Normalize CSS -->
<link rel="stylesheet" href="<%=ComConstant.CONTEXT_ROOT %>/resources/css/normalize.css">
<!-- Cross Browsing -->
<!--[if lt IE 9]>
<link rel="stylesheet" href="<%=ComConstant.CONTEXT_ROOT %>/css/common-ie8.css">
   <script type="text/javascript" src="<%=ComConstant.CONTEXT_ROOT %>/js/html5shiv-printshiv.js"></script>
   <script type="text/javascript" src="<%=ComConstant.CONTEXT_ROOT %>/js/respond.min.js"></script>
   <script type="text/javascript" src="<%=ComConstant.CONTEXT_ROOT %>/js/common-ie8.js"></script>
<![endif]-->
<!--[if gte IE 9]><!-->
   <link rel="stylesheet" href="<%=ComConstant.CONTEXT_ROOT %>/resources/css/swiper.min.css">
   <link rel="stylesheet" href="<%=ComConstant.CONTEXT_ROOT %>/resources/css/common.css">
   <link rel="stylesheet" href="<%=ComConstant.CONTEXT_ROOT %>/resources/css/board.css">
   <link rel="stylesheet" href="<%=ComConstant.CONTEXT_ROOT %>/resources/css/content.css">
   <script type="text/javascript" src="<%=ComConstant.CONTEXT_ROOT %>/resources/js/swiper.min.js"></script>
   <script type="text/javascript" src="<%=ComConstant.CONTEXT_ROOT %>/resources/js/common.js"></script>
   <script type="text/javascript" src="<%=ComConstant.CONTEXT_ROOT %>/resources/js/sweetalert.min.js"></script>
<!--<![endif]-->

<style type="text/css">
	.swal-modal { position:relative; top : 200px; }
	.swal-loginResultPop {margin-bottom: 550px !important;}
	.swal-button {padding:4px 15px; color:#002284;}
</style>
<script type="text/javascript">
  
	
	function beforeAjax(xmlHttpRequest) {
		xmlHttpRequest.setRequestHeader("AJAX", "true"); //ajax호출을 header에 기록
	}
     </script>

<head>
<meta charset="UTF-8">
<title>Error Page</title>
</head>
 <body>
        <div id="viewport" class="login-wrap">
            <div id="login-view">
              <div class="login-inner">
                <header id="login-header">
                    <h1 class="logo" style="display: table; width: 100%;margin-bottom: 20px;box-sizing: border-box; font-weight: bold; font-size: 30px;"> 스미싱대응시스템 <span class="magenta"></span></h1>
                </header>
                <main id="login-main" class="login">
                    <div class="main-inner" style="width: 0% !important">
                        <!-- 에러폼 -->
                        <div class="login-warp" style = "height:265px;">
                            <div class="login-area">
                                <div class="login-logo">
                                  <%-- <img src="<%=ComConstant.CONTEXT_ROOT %>/img/login/login_logo.png" alt="kt통합단말시스템"> --%>
                                  <div class="txt_box">
                                    <h2>Error</h2>
                                    <p>
                                      죄송합니다. 요청하신 기능에 문제가 발생했습니다. 
                                    </p>
                                    <p style = "position:relative; color:red; font-size: 22px; font-weight:400; left:110px; top:29px; ">
                                      비정상적인 접근입니다.
                                    </p>
                                  </div>
                                </div>
                            

                                <div class="login-form" style="margin: 35px 0;">
                                  
                                    <button type="button" name="" class="btn btnM btn002 w100p" onclick="$(location).attr('href', '<%=ComConstant.CONTEXT_ROOT %>/menu.do')">홈으로 돌아가기</button>
                                    
                                   
                                  
                                </div>
                              
                            </div>
                        </div>
                      </div>
                        <!-- // 이미지 슬라이드 (기본형) -->
                        <!-- //로그인 배너 -->
                    </div>
                </main>
            </div>
        </div>

      </div>
    </body>
</html>