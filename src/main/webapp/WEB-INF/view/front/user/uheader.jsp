<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zhouyc
  Date: 2017/6/30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<header>
	<div class="container top_bar clearfix">
		<img src="${pageContext.request.contextPath }/static/img/logo.png" alt="智游">
		<div id="tele">
			<span>4006-371-555</span>
			<span>0371-88888598</span>
		</div>
	</div>
	<menu>
		<div class="container clearfix">
			<ul class="clearfix f_left">
				<li><a href="${pageContext.request.contextPath }/index.jsp">首页</a></li>
				<li class="menu_active"><a href="${pageContext.request.contextPath }/front/user/user_index.action?id=${_front_user.id}">个人中心</a></li>
			</ul>
			<div id="user_bar">
				<a href="${pageContext.request.contextPath }/front/user/user_index.action?id=${_front_user.id}">
					<c:if test="${empty sessionScope._front_user.headUrl}">
						<img id="avatar" src="${pageContext.request.contextPath }/static/img/avatar_lg.png" alt="">
					</c:if>

					<c:if test="${not empty sessionScope._front_user.headUrl}">
						<img id="avatar" src="${_front_user.headUrl}" alt="">
					</c:if>

				</a>
				<a href="${pageContext.request.contextPath }/front/user/user_logout.action" id="lay_out">退出</a>
			</div>
		</div>
	</menu>
</header>
