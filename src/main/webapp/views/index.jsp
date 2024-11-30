<%@ page contentType="text/html;charset=UTF-8;" pageEncoding="UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>ASM</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>

	<c:url value="/" var="home" />
	<c:url value="/category" var="category" />
	<c:url value="/newsletter" var="newsletter" />
	<c:url value="/news/1" var="news" />
	<c:url value="/latest" var="latest" />
	<c:url value="/most-viewed" var="mostViewed" />
	<c:url value="/dashboard" var="dashboard"/>
	<c:url value="/" var="mainURL" />


	<nav class="navbar navbar-expand-sm navbar-dark bg-black border-bottom-5">
		<div class="container">
			<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="collapsibleNavbar">
				<ul class="navbar-nav me-auto">
					<li class="nav-item">
						<a href="${home}" class="nav-link">Trang chủ</a>
					</li>
					<li class="nav-item">
						<a href="${dashboard}" class="nav-link">Dashboard</a>
					</li>
					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
							Chuyên mục
						</a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<c:forEach items="${categories}" var="categoryObject">
								<li class="nav-item">
									<a href="${category}/${categoryObject.id}" class="dropdown-item">${categoryObject.name}</a>
								</li>
							</c:forEach>
						</ul>
					</li>
				</ul>
			</div>
		</div>
	</nav>



	<div class="container mt-5">
		<div class="row">
			<c:choose>
				<c:when test="${hideSideBar}">
					<div class="col-lg-12" style="min-height: 500px">
						<jsp:include page="${page}" />
					</div>
				</c:when>
				<c:otherwise>
					<div class="col-lg-8" style="min-height: 500px">
						<jsp:include page="${page}" />
					</div>

					<!-- Sidebar bên phải -->
					<div class="col-lg-4">
						<div class="sidebar p-3"
							 style="background-color: #f8f9fa; border: 1px solid #d3d3d3; border-radius: 8px;">

							<!-- 5 bản tin được xem nhiều -->
							<div class="card mb-3">
								<div class="card-header bg-primary text-white">
									<a class="text-white" data-toggle="collapse" href="${mostViewed}"
									   role="button" aria-expanded="false" aria-controls="popularNews">
										5 bản tin được xem nhiều </a>
								</div>
							</div>

							<!-- 5 bản tin mới nhất -->
							<div class="card mb-3">
								<div class="card-header bg-success text-white">
									<a class="text-white" data-toggle="collapse" href="${latest}"
									   role="button" aria-expanded="false" aria-controls="latestNews">
										5 bản tin mới nhất </a>
								</div>
							</div>

							<!-- 5 bản tin đã bạn đã xem -->
								<%--					<div class="card mb-3">--%>
								<%--						<div class="card-header bg-warning text-white">--%>
								<%--							<a class="text-white" data-toggle="collapse" href=""--%>
								<%--							   role="button" aria-expanded="false" aria-controls="viewedNews">--%>
								<%--								5 bản tin đã bạn đã xem </a>--%>
								<%--						</div>--%>
								<%--					</div>--%>

							<!-- Newsletter -->
							<div class="card">
								<div class="card-header bg-secondary text-white">
									<a class="text-white" data-toggle="collapse" href="${newsletter}"
									   role="button" aria-expanded="false" aria-controls="newsletter">
										Newsletter </a>
								</div>
							</div>

						</div>
					</div>
				</c:otherwise>
			</c:choose>



		</div>
	</div>

	<footer class="py-5 mt-5 text-center bg-dark text-white">
		<p class="align-items-center">
			<i class="bi bi-c-circle"></i> Copyright by FPT Polytechinc
		</p>
	</footer>
</body>
</html>
