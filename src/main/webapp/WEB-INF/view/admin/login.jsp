<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>登录界面</title>
    <link href="${pageContext.request.contextPath }/static/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath }/static/js/jquery-1.12.4.min.js"></script>
    <script src="${pageContext.request.contextPath }/static/js/bootstrap.min.js"></script>
   	<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery.validate.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/messages_zh.js"></script>
	<link  href="${pageContext.request.contextPath }/static/css/jquery-confirm.css" rel="stylesheet" >
	<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery-confirm.js"></script>
	<script type="text/javascript">
	$(function(){
		$("#checkForm").validate({
			rules:{
				loginName:{
					required:true,
					minlength:5,
					maxlength:16
				},
				loginPwd:{
					required:true,
					minlength:5,
					maxlength:16
				}
			},
			messages:{
				loginName:{
					required:"用戶名不能為空",
					minlength:"用戶太短了吧",
					maxlength:"用戶太太长了"
				},
				loginPwd:{
					required:"密码不能為空",
					minlength:"密码太短",
					maxlength:16
				}
			}
		});
	});
	
	</script>
 	<style>
	#bobo1{
	position: relative;
	margin:200px auto;
	background: white;
	width: 350px;
	height: 240px;
	border-radius:15px;
	} 
 	</style>
  </head>
  <body>
    <div id="bobo1">
    	<img src="${pageContext.request.contextPath }/static/img/logo.png" width="100%"/>
    	<form id="checkForm" action="<c:url value="/admin/admin_login.action" />" method="post">
    		<div class="form-group">
    			<br />
		    <input type="text" class="form-control" placeholder="请输入账户" name="loginName" value="admin">
		    </div>
		  	<div class="form-group">
		  	<input type="password" class="form-control" placeholder="请输入密码" name="loginPwd" value="admin" >
		  	 </div>
		  	<div class="form-group">
		  		<s:actionerror/>
		  	 </div>
		   	<button type="submit" class="btn btn-success btn-lg btn-block">登录</button>
		</form>
	</div>
	
  </body>
</html>