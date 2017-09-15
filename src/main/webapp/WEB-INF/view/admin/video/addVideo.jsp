<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
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
  </head>
  <body>
	   	   	<jsp:include page="/WEB-INF/view/admin/admin.jsp">
				<jsp:param value="video" name="fromJsp"/>
			</jsp:include>
	    
	    <div class="container theme-showcase" role="main">
	    	
		    <div class="jumbotron">
		        <h1>添加视频信息-视频管理</h1>
		    </div>
		    
		    <form class="form-horizontal" action="${pageContext.request.contextPath }/admin/video/video_add.action" method="post"> 
			  <div class="form-group">
			    <label for="inputEmail3" class="col-sm-2 control-label">视频标题</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" id="inputEmail3" placeholder="视频标题" name="videoTitle">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="inputPassword3" class="col-sm-2 control-label">主讲人</label>
			    <div class="col-sm-10">
			      <select class="form-control" name="speaker_id">
	            	  <option  value="">请选择主选人</option>
					  <c:forEach items="${listSpeaker }" var="speaker">
					  <option value="${speaker.id}">${speaker.speakerName}</option>
					  </c:forEach>
					</select>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="inputPassword3" class="col-sm-2 control-label">所属课程</label>
			    <div class="col-sm-10">
			      <select class="form-control" name="course_id">
	            	  <option value="">请选择课程</option>
					  <c:forEach items="${listCourse }" var="course">
					  <option value="${course.id}">${course.courseName}</option>
					  </c:forEach>
					</select>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="inputPassword3" class="col-sm-2 control-label">视频时长</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" id="inputPassword3" placeholder="视频时长(秒)" name="videoLength">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="inputPassword3" class="col-sm-2 control-label">封面图片</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" id="inputPassword3" placeholder="视频封面图片地址、网络地址" name="videoImageUrl">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="inputPassword3" class="col-sm-2 control-label">视频播放地址</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" id="inputPassword3" placeholder="视频播放地址、网络地址" name="videoUrl">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="inputPassword3" class="col-sm-2 control-label">视频简介</label>
			    <div class="col-sm-10">
			      <textarea class="form-control" id="inputPassword3" name="videoDescr"></textarea>
			    </div>
			  </div>
			  <div class="form-group">
			    <div class="col-sm-offset-2 col-sm-3">
			      <button type="submit" class="btn btn-primary">提交</button>
			      <a type="button" class="btn btn-default" href="javascript:history.go(-1)">返回列表</a>
			    </div>
			  </div>
			</form>
		

		</div>
  </body>
</html>