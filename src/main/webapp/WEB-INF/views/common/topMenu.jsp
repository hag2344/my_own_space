<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.nhs.firstProject.common.ComConstant"%>
<%@ page import="com.nhs.firstProject.common.vo.UserInfoVo"%>
<%@ page import="java.util.HashMap"%>
<%@page import="java.net.URL"%>
<%@page import="java.util.Properties"%>
<!DOCTYPE html>
<%
	session = request.getSession();
	HashMap<String, Object> sessionMap = (HashMap<String, Object>) session.getAttribute("sessionMap"); 
	UserInfoVo userMap = (UserInfoVo)sessionMap.get("userMap");
%>	

	<script type="text/javascript">
	
	var menuModule = (function(){
		var arrScreenMenu = new Array();
		var screenId = null;
		var cnt = 0;
		var specificUser = false;

		function topMenuDocReady(){
			doMenuSrch();
		}

		function doMenuSrch() {
			var	userid = '<%=userMap.getStrUserId()%>';
			var param = {
				TYPE : 0 ,
				USERID : userid
			};
			$.ajax({
				method : "post",
				url : "<%=ComConstant.CONTEXT_ROOT %>/menuList.do",
				data : param,
				dataType : "JSON",
				async : false,
				beforeSend : beforeAjax,
				success : sucDoMenuSrch,
				error : errDoMenuSrch
			});
		}
		
		function sucDoMenuSrch(result) {
			var menuList = result.returnData.resultData; 
			arrScreenMenu = menuList;  
			var inHtml = '';
			var firstInHtml = '';
			var secondInHtml = '';
			var thirdInHtml = '';
		
			if(menuList.length > 0){
				firstInHtml += '<ul class="dep1">';
				for(var i = 0; i<menuList.length; i++){
					var val = menuList[i];
					if(val.LEVEL_NO == 1){
						firstInHtml += '<li onMouseEnter="showSubMenu(\''+val.MENU_ID+'\')"><a href="#">'+val.MENU_NAME+'</a></li>';

						inHtml += '<li>';
						inHtml += '<div>';
						inHtml += '<div class="sub_nav_wrap">';
						inHtml += '<ul id=\''+val.MENU_ID+'\' class="dep2"></ul>';
						inHtml += '</div>';
						inHtml += '</div>';
						inHtml += '</li>';
					}
				}
			}
			firstInHtml += '</ul>';
			$("#lnb_p").html(firstInHtml);
			$("#ulLnb_depth").html(inHtml);
		}

		function showSubList(strScreenId){
			var inHtml ='';
			for (var i=0; i < cnt; i++) {
				var d = Number(i);
				inHtml += '<li>';
				inHtml += '<div>';
				inHtml += 	'<div class="sub_nav_wrap">';
				inHtml +=  		'<ul id=\''+strScreenId+'\' class="dep2">';
				inHtml += 		'</ul>';
				inHtml += 	'</div>';
				inHtml += 	'</div>';
				inHtml +=	'</li>';
			}
	 		$("#ulLnb_depth").html(inHtml);
		}

		function showSubMenu(strScreenId) {
			var secondInHtml ='';
			for (var i = 0; i < arrScreenMenu.length; i++) {
				if (arrScreenMenu[i].PARENT_MENU_ID == strScreenId) {
					if (null != arrScreenMenu[i].URL_PATH) {
						secondInHtml +=	'<li><a href="#" onClick="movePage(\''+arrScreenMenu[i].URL_PATH+'\', \''+arrScreenMenu[i].MENU_ID+'\', \''+arrScreenMenu[i].PATH_NAME+'\')">'+ arrScreenMenu[i].MENU_NAME+'<span class="icon"></a>';
					} else {
						secondInHtml +=	'<li><a href="#">'+ arrScreenMenu[i].MENU_NAME+'<span class="icon"></a>';
					}
					secondInHtml +=	'<ul class="dep3">';
					for (var z = 0; z < arrScreenMenu.length; z++) { 
						if (arrScreenMenu[z].PARENT_MENU_ID == arrScreenMenu[i].MENU_ID) {
							if (null != arrScreenMenu[z].URL_PATH) {
								secondInHtml +='<li><a href="#" onClick="movePage(\''+arrScreenMenu[z].URL_PATH+'\', \''+arrScreenMenu[z].MENU_ID+'\', \''+arrScreenMenu[z].PATH_NAME+'\')">'+ arrScreenMenu[z].MENU_NAME+'<span class="icon"></a>';
							} else {
								secondInHtml +='<li><a href="#">'+ arrScreenMenu[z].MENU_NAME+'<span class="icon"></a></li>';
							}
						}
					}
					secondInHtml +='</ul>';
					secondInHtml +='</li>';					
				}
				$("#"+strScreenId).html(secondInHtml);
			}
		}

		function errDoMenuSrch(xhr,status,error){
			if(xhr.status == 999) {
				swal({title:"세션이 만료되었습니다. 다시 로그인하시기 바랍니다.",closeOnClickOutside:false}).then($(location).attr('href', 'login.do'));
			} else {
				swal({title:"메뉴 리스트 조회 중 오류가 발생하였습니다.",icon: "error",closeOnClickOutside:false});
			}
		}

		function beforeAjax(xmlHttpRequest) {
			xmlHttpRequest.setRequestHeader("AJAX", "true");
		}

		function movePage(url, screenId, pathName) {
			var $form = $('<form></form>');
			$form.attr('action', url);
			$form.attr('target', '_self');
			$form.attr('method', 'post');
			$form.appendTo('body');
			$form.append($('<input type="hidden" name="SAVE_FLAG" value="M">'));
			$form.append($('<input type="hidden" name="SCREENID" value="'+ screenId +'">'));
			$form.append($('<input type="hidden" name="PATHNAME" value="'+ pathName +'">'));
			$form.append($('<input type="hidden" name="CLIENTIP" value="<%=userMap.getStrClientIP()%>">'));
			$form.submit();
		}
		
		return {
			init: function(){
				$(document).ready(function(){
					topMenuDocReady();
				});
			}
		}
	})();
	menuModule.init();

  </script>
	<div class="inr">
		<div class="logo">
        		<h1><span>
        		<a href="<%=ComConstant.CONTEXT_ROOT %>/menu.do" style="display:inline-flex;width:130px; margin-left:15px;">
        			<span style="color:#D25A5A;float:left;font-weight:bold;">비밀</span>
        			<span style="font-size:28px;float:left;font-weight:bold;color:#fff;line-height:35px;">사전</span>
        		</a></span></h1>
	   	</div>
		<div id="lnb_p" class="lnb_p">
		
		</div>
		<div class="gnb_p">
            <ul class="gnb_info">
	            <li class="name"><a href=""><%=userMap.getStrName()%> 님(<%=userMap.getStrRoleName()%>)</a></li>
	            <li class="name2"></li>
            </ul>
            <ul class="gnb_list">
	            <li class="login"><button type="button" name="" class="btn btnR btn002" onclick="$(location).attr('href', '<%=ComConstant.CONTEXT_ROOT %>/logout.do')">로그아웃</button></li>
              <!-- 로그인 전후 분류별 메뉴 -->
              <!-- <li class="logout"><a href="">로그아웃</a></li>
                     <li class="mypage"><a href="">마이페이지</a></li> -->
            </ul>
       	</div>
	</div>
		
	<div class="lnb_depth">
		<ul id="ulLnb_depth">
		
		</ul>
	</div>
