<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://zhiyou100.com/common/" prefix="bobo"%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>视频列表</title>
    <link href="${pageContext.request.contextPath }/static/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath }/static/js/jquery-1.12.4.min.js"></script>
    <script src="${pageContext.request.contextPath }/static/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery-confirm.js"></script>
	<link  href="${pageContext.request.contextPath }/static/css/jquery-confirm.css" rel="stylesheet" >
 	<script type="text/javascript">
 	var count=0;
 	$(function(){
 		$(".bobo01").click(function(){
		 	var aa = this.id;
		 	$.confirm({
		 		title: '警告',
		 	    content: '你确定要删除此条记录吗?',
		 	    buttons: {
		 	    	确定删除 : function () {
		 	    		 $.get(
		 	    			"${pageContext.request.contextPath }/admin/ajax/ajax_deleteVideo.action",
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
 		$("input[name=bobo02]").click(function(){
 			var cc = this.checked;
 			$(":checkbox").each(function(a,b){
 				b.checked=cc;
 			});
 			if(cc){
 				count=$(":checkbox").length-1;
 				$(".badge").text(count);
 			}else{
 				count=0;
 				$(".badge").text(count);
 			}
 		});
 		$("input[name=xuanze]").click(function(){
 			if(this.checked){
 				count++;
 			}else{
 				count--;
 			}
 			$(".badge").text(count);
 			if(count == $(":checkbox").length-1){
 				
 			}
 		});
 		$("#bobo03").click(function(){
 			if(count!=0){
 				$.confirm({
 			 		title: '多条记录删除警告!',
 			 	    content: '你确定要删除此些记录吗?',
 			 	    buttons: {
 			 	    	确定删除 : function () {
 			 	    		$("#bobo04").submit();
 			 	        },
 			 	       	 取消  : function () {
 			 	            return;
 			 	        }
 			 	    }
 			 	});
	 			/* if(confirm("确定要删除这些记录吗?")){
	 				$("#bobo04").submit();
	 			} */
 			}else{
 				$.alert({
 				    title: '没选中',
 				    content: '删个屁!!!',
 				});
 			}
 		});
 	});
	</script>
  </head>
  <body>
	   	<jsp:include page="/WEB-INF/view/admin/admin.jsp">
			<jsp:param value="video" name="fromJsp"/>
		</jsp:include>
	    
	    <div class="container theme-showcase" role="main">
	    	
		    <div class="jumbotron">
		        <h1>视频列表-视频管理</h1>
		    </div>
		    
		    <a class="btn btn-primary" href="${pageContext.request.contextPath }/admin/video/video_toAdd.action">添加视频</a>
		    <a class="btn btn-primary" id="bobo03"><font color="white">批量删除</font>&nbsp;&nbsp;<span class="badge">0</span></a>
		    <div style="float: right;">
	          <form class="navbar-form navbar-right"  action="${pageContext.request.contextPath }/admin/video/video_list.action">
	            <div class="form-group">
	              <input type="text" placeholder="视频标题" class="form-control" name="videoTitle" value="${videoTitle }">
	            </div>
	            <div class="form-group">
	            	<select class="form-control" name="speaker_id">
	            	  <option  value="0" >请选择主选人</option>
					  <c:forEach items="${listSpeaker }" var="speaker">
					  <option value="${speaker.id}" ${ speaker.id == speaker_id ? "selected":"" }>${speaker.speakerName}</option>
					  </c:forEach>
					</select>
	            </div>
	            <div class="form-group">
	            	<select class="form-control" name="course_id">
	            	  <option value="0">请选择课程</option>
					  <c:forEach items="${listCourse }" var="course">
					  <option value="${course.id}" ${ course.id==course_id?"selected":""}>${course.courseName}</option>
					  </c:forEach> 
					</select>
	            </div>
	            <button type="submit" class="btn btn-primary">查询</button>
	          </form>
        	</div>
		    
			
			<div class="bs-example" data-example-id="hoverable-table">
			    <form action="${pageContext.request.contextPath }/admin/video/video_deleteAll.action" id="bobo04">
			    <table class="table table-hover">
			      <thead>
			        <tr>
			          <th class="col-md-0"><input type="checkbox" name="bobo02"></th>
			          <th class="col-md-0">序号</th>
			          <th class="col-md-2">名称</th>
			          <th class="col-md-9">介绍</th>
			          <th class="col-md-0">讲师</th>
			          <th class="col-md-2">课程</th>
			          <th class="col-md-0">时长(秒)</th>
			          <th class="col-md-0">播放次数</th>
			          <th class="col-md-0">编辑</th>
			          <th class="col-md-0">删除</th>
			        </tr>
			      </thead>
			      <tbody>
				      <c:if test="${not empty page1.rows }">
				        <c:forEach items="${page1.rows }" var="video" varStatus="status">
					        <tr>
					          <th scope="row"><input type="checkbox" name="xuanze" value="${video.id}"></th>
					          <td>${status.count+(page1.page-1)*5}</td>
					          <td>${video.videoTitle }</td>
					          <td>${video.videoDescr }</td>
					          <td>${video.speaker.speakerName }</td>
					          <td>${video.course.courseName }</td>
					          <td>${video.videoLength }</td>
					          <td>${video.videoPlayTimes }</td>
					          <td><a class="glyphicon glyphicon-edit" href="${pageContext.request.contextPath }/admin/video/video_toUpdate.action?id=${video.id }"></a></td>
					         <%--  <td><a class="glyphicon glyphicon-trash" id="bobo01" href="${pageContext.request.contextPath }/video/deleteVideo.action?id=${video.id }" onclick="return delete1()"></a></td> --%>
					          <td><a class="glyphicon glyphicon-trash bobo01" id="${video.id }"></a></td>
					        </tr>
				        </c:forEach>
				      </c:if>
				      <c:if test="${empty page1.rows}">
				      		<tr><td width="100%">当前页内容为空!</td>
				      		</tr>
				      </c:if>
			      </tbody>
			    </table>
			    </form>
			    <bobo:page url="${pageContext.request.contextPath }/admin/video/video_list.action"></bobo:page>
			</div>
		</div>
  </body>
</html>