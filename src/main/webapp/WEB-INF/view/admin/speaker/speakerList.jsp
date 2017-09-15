<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://zhiyou100.com/common/" prefix="goubo" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title></title>
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
		 	    			"${pageContext.request.contextPath }/admin/ajax/ajax_deleteSpeaker.action",
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
					<jsp:param value="speaker" name="fromJsp"/>
				</jsp:include>
	    
	    <div class="container theme-showcase" role="main">
	    	
		    <div class="jumbotron">
		        <h1>主选人列表-主选人管理</h1>
		    </div>
		    
		    <a type="button" class="btn btn-primary" href="${pageContext.request.contextPath }/admin/speaker/speaker_toAdd.action">添加主选人</a>
		    <div style="float: right;">
	          <form class="navbar-form navbar-right">
	            <div class="form-group">
	              	名称<input type="text" placeholder="主选人名称" class="form-control" name="speakerName" value="${speaker_name}">
	            </div>
	            <div class="form-group">
	            	主讲人职位<input type="text" placeholder="主讲人职位" class="form-control" name="speakerJob" value="${speaker_job}">
	            </div>
	            <button type="submit" class="btn btn-primary">查询</button>
	          </form>
        	</div>
		    
			
			<div class="bs-example" data-example-id="hoverable-table">
			    <table class="table table-hover">
			      <thead>
			        <tr>
			          <th class="col-md-0">序号</th>
			          <th class="col-md-1">名称</th>
			          <th class="col-md-2">职位</th>
			          <th class="col-md-9">介绍</th>
			          <th class="col-md-0">编辑</th>
			          <th class="col-md-0">删除</th>
			        </tr>
			      </thead>
			      <tbody>
			      <c:if test="${not empty page1.rows}">
				      <c:forEach items="${page1.rows}" var="speaker" varStatus="status">
				        <tr>
				          <th scope="row">${status.count+(page1.page-1)*5}</th>
				          <td>${speaker.speakerName }</td>
				          <td>${speaker.speakerJob }</td>
				          <td>${speaker.speakerDescr }</td>
				          <td><a class="glyphicon glyphicon-edit" href="${pageContext.request.contextPath }/admin/speaker/speaker_toUpdate.action?id=${speaker.id}"></a></td>
				          <td>
				          <%-- <a class="glyphicon glyphicon-trash" href="${pageContext.request.contextPath }/speaker/deleteSpeaker.action?id=${speaker.id}" onclick="return delete1()"></a> --%>
				          <a class="glyphicon glyphicon-trash bobo01" id="${speaker.id}"></a>
				          </td>
				        </tr>
				      </c:forEach>
			      </c:if>
				      <c:if test="${empty page1.rows}">
				      		<tr><td width="100%">当前页内容为空!</td>
				      		</tr>
				      </c:if>
			      </tbody>
			    </table>
			    <goubo:page url="${pageContext.request.contextPath }/admin/speaker/speaker_list.action"></goubo:page>
			</div>
		</div>
  </body>
</html>