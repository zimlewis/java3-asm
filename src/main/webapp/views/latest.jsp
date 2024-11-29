<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="row">
	<c:forEach items="${latestNews}" var="news">
		<jsp:include page="/views/components/news.jsp">
			<jsp:param name="id" value="${news.id}"/>
			<jsp:param name="title" value="${news.title}"/>
			<jsp:param name="content" value="${news.content}"/>
			<jsp:param name="image" value="${news.image}"/>
			<jsp:param name="postedDate" value="${news.postedDate}"/>
			<jsp:param name="author" value="${news.author}"/>
			<jsp:param name="viewCount" value="${news.viewCount}"/>
			<jsp:param name="categoryId" value="${news.categoryId}"/>
		</jsp:include>
	</c:forEach>
</div>