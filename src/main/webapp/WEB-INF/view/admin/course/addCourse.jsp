<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
					<jsp:param value="course" name="fromJsp"/>
				</jsp:include>
	   
	    
	    <div class="container theme-showcase" role="main">
	    	
		    <div class="jumbotron">
		        <h1>添加课程</h1>
		    </div>
		    
		    <form class="form-horizontal" action="${pageContext.request.contextPath }/admin/course/course_add.action" method="post">
			  <div class="form-group">
			    <label for="inputEmail3" class="col-sm-2 control-label">所属学科</label>
			    <div class="col-sm-10">
			    	<select class="form-control" name="subject_id">
	            		  <option>请选择所属课程</option>
	            		  <c:forEach items="${subject }" var="sb">
						  <option  value="${sb.id }">${sb.subjectName }</option>
						  </c:forEach>
						</select>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="inputPassword3" class="col-sm-2 control-label">标题</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" id="inputPassword3" placeholder="标题" name="courseName">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="inputPassword3" class="col-sm-2 control-label">简介</label>
			    <div class="col-sm-10">
			      <textarea class="form-control" id="inputPassword3" name="courseDescr"></textarea>
			    </div>
			  </div>
			  <div class="form-group">
			    <div class="col-sm-offset-2 col-sm-3">
			      <button type="submit" class="btn btn-default">保存</button>
			    </div>
			  </div>
			</form>
		</div>
  </body>
</html>