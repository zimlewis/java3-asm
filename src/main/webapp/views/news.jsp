<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="card mb-4">
	<c:url var="urlImage" value="/get-image" />
	<img src="${urlImage}/${news.image}" class="card-img-top" alt="Main News Image">
	<div class="card-body">
		<h5 class="card-title">${news.title}</h5>
		<p class="text-muted mb-2">${news.postedDate} • ${news.viewCount} lượt xem</p>
		<p class="text-truncate">${news.content}</p>
		<div class="row">
			<c:forEach items="${newsInCategory}" var="newsCategory">
				<c:if test="${newsCategory.id != news.id}">
					<jsp:include page="/views/components/news.jsp">
						<jsp:param name="id" value="${newsCategory.id}"/>
						<jsp:param name="title" value="${newsCategory.title}"/>
						<jsp:param name="content" value="${newsCategory.content}"/>
						<jsp:param name="image" value="${newsCategory.image}"/>
						<jsp:param name="postedDate" value="${newsCategory.postedDate}"/>
						<jsp:param name="author" value="${newsCategory.author}"/>
						<jsp:param name="viewCount" value="${newsCategory.viewCount}"/>
						<jsp:param name="categoryId" value="${newsCategory.categoryId}"/>
					</jsp:include>
				</c:if>

			</c:forEach>
		</div>
	</div>
</div>