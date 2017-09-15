<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://zhiyou100.com/common/" prefix="goubo" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
    <link  href="${pageContext.request.contextPath }/static/css/jquery-confirm.css" rel="stylesheet" >
	<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery-confirm.js"></script>
	<script type="text/javascript">
	$(function(){
 		$(".bobo01").click(function(){
		 	var aa = this.id;
		 	$.confirm({
		 		title: '警告',
		 	    content: '你确定要删除此条记录吗?',
		 	    buttons: {
		 	    	确定删除 : function () {
		 	    		 $.get(
		 	    			"${pageContext.request.contextPath }/admin/ajax/ajax_deleteCourse.action",
		 	    			"id="+aa,
		 	    			function (mag){
		 	    				if(mag.success){
		 	    					location.reload();
		 	    				}
		 	    			},
		 	    			"json"
		 	    		) 
		 	        },
		 	       	 取消  : function () {
		 	            return;
		 	        }
		 	    }
		 	});
 		});
	});
	</script>
  </head>
  <body>
	   
	    	   	<jsp:include page="/WEB-INF/view/admin/admin.jsp">
					<jsp:param value="course" name="fromJsp"/>
				</jsp:include>
	    <div class="container theme-showcase" role="main">
	    	
		    <div class="jumbotron">
		        <h1>课程列表-课程管理</h1>
		    </div>
		    
		    <a type="button" class="btn btn-primary" href="${pageContext.request.contextPath}/admin/course/course_toAdd.action">添加课程</a>
		    
			<div class="bs-example" data-example-id="hoverable-table">
			    <table class="table table-hover">
			      <thead>
			        <tr>
			          <th class="col-md-0">序号</th>
			          <th class="col-md-1">标题</th>
			          <th class="col-md-0">学科</th>
			          <th class="col-md-10">简介</th>
			          <th class="col-md-1">编辑</th>
			          <th >删除</th>
			        </tr>
			      </thead>
			      <tbody>
				   <c:if test="${not empty page1.rows}">
					      <c:forEach items="${page1.rows }" var="course" varStatus="status">
						        <tr>
						          <th scope="row">${status.count+(page1.page-1)*5}</th>
						          <td >${course.courseName}</td>
						          <td >${course.subject.subjectName}</td>
						          <td >${course.courseDescr}</td>
						          <td><a class="glyphicon glyphicon-edit " href="${pageContext.request.contextPath }/admin/course/course_toUpdate.action?id=${course.id}"></a></td>
						          <td>
						          <%-- <a class="glyphicon glyphicon-trash " href="${pageContext.request.contextPath }/course/deleteCourse.action?id=${course.id}"></a> --%>
						          <a class="glyphicon glyphicon-trash bobo01" id="${course.id}"></a>
						          </td>
						        </tr>
					        </c:forEach>
					  </c:if>
					  <c:if test="${empty page1.rows }">
					  		<tr>
					  			<td width="100%">当前页内容为空!</td>
					  		</tr>
					  </c:if>
			      </tbody>
			    </table>
			    <goubo:page url="${pageContext.request.contextPath }/admin/course/course_list.action"></goubo:page>
			</div>
		</div>
  </body>
</html>