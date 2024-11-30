<%--
  Created by IntelliJ IDEA.
  User: zimlewis
  Date: 11/29/24
  Time: 3:54 PM
  To change this template use File | Settings | File Templates.
--%>


<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	String id = request.getParameter("id");
	String image = request.getParameter("image");
	request.setAttribute("id", id);
	request.setAttribute("image", image);
%>
<div class="col-lg-12 mb-4">
	<div class="card">
		<%--
			id
			<jsp:param name="title" value="${news.title}"/>
			<jsp:param name="content" value="${news.content}"/>
			<jsp:param name="image" value="${news.image}"/>
			<jsp:param name="postedDate" value="${news.postedDate}"/>
			<jsp:param name="author" value="${news.author}"/>
			<jsp:param name="viewCount" value="${news.viewCount}"/>
			<jsp:param name="categoryId" value="${news.categoryId}"/>

--%>
		<div class="row no-gutters">
			<c:url value="/news/${id}" var="news" />
			<div class="col-md-4">
				<img src="${pageContext.request.contextPath}/get-image/${image}" class="card-img" alt="Post Image">
			</div>
			<div class="col-md-8">
				<div class="card-body">
					<h5 class="card-title">
						<!-- Thay đổi liên kết để chuyển đến trang chi tiết bài viết -->
						<a class="text-decoration-none" href="${news}">
							<%= request.getParameter("title")%>
						</a>
					</h5>
					<p class="text-muted"><%= request.getParameter("postedDate")%> • <%= request.getParameter("viewCount")%> lượt xem</p>
					<p class="card-text">
						<div class="d-inline-block text-truncate w-100">
						<%= request.getParameter("content")%>
						</div>

					</p>
				</div>
			</div>
		</div>
	</div>
</div>
