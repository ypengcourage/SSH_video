<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title></title>
<%--     <link href="${pageContext.request.contextPath }/static/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath }/static/js/jquery-1.12.4.min.js"></script>
    <script src="${pageContext.request.contextPath }/static/js/bootstrap.min.js"></script> --%>
 	<style>
 	</style>
  </head>
  <body>
  
  	    <nav class="navbar navbar-inverse navbar-fixed-top">
	      <div class="container">
	        <div class="navbar-header">
	          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
	            <span class="sr-only">Toggle navigation</span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	          </button>
	          <a class="navbar-brand" href="#">视频管理系统</a>
	        </div>
	        
	        <div id="navbar" class="navbar-collapse collapse">
	          <ul class="nav navbar-nav">
	            <li ${param.fromJsp=="video"?"class='active'":""}><a  href="${pageContext.request.contextPath }/admin/video/video_list.action">视频管理</a></li>
	            <li ${param.fromJsp=="speaker"?"class='active'":""}><a href="${pageContext.request.contextPath }/admin/speaker/speaker_list.action">主讲人管理</a></li>
	            <li ${param.fromJsp=="course"?"class='active'":""}><a href="${pageContext.request.contextPath }/admin/course/course_list.action">课程管理</a></li>
				<li ${param.fromJsp=="statistics"?"class='active'":""}><a href="${pageContext.request.contextPath }/admin/video/stat_show.action">统计分析</a></li>
	          </ul>
		      <div id="navbar" class="navbar-collapse collapse">
		          <ul class="nav navbar-nav navbar-right">
		            <li><a href="#">${admin.loginName }</a></li>
		            <li><a href="${pageContext.request.contextPath }/admin/admin_loginout.action" class="glyphicon glyphicon-log-out"></a></li>
		          </ul>
		      </div>
	        </div>
	      </div>
	    </nav>
	    

  </body>
</html>