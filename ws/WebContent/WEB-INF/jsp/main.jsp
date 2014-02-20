<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link
	href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600"
	rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath}/js/jquery.dropotron.min.js"></script>
<script src="${pageContext.request.contextPath}/js/skel.min.js"></script>
<script src="${pageContext.request.contextPath}/js/skel-panels.min.js"></script>
<script src="${pageContext.request.contextPath}/js/init.js"></script>
<noscript>
	<link rel="stylesheet"
		href="${pageContext.request.contextPath}/css/skel-noscript.css" />
	<link rel="stylesheet"
		href="${pageContext.request.contextPath}/css/style.css" />
	<link rel="stylesheet"
		href="${pageContext.request.contextPath}/css/style-desktop.css" />
	<link rel="stylesheet"
		href="${pageContext.request.contextPath}/css/style-noscript.css" />
</noscript>
<title>MovieSpark by Team One</title>
</head>
<body class="homepage">
	<!-- Header -->
	<div id="header">

		<!-- Inner -->
		<div class="inner">
			<header>
			<h1>
				<a href="#" id="logo">MovieSpark</a>
			</h1>
			<hr />
			<span class="byline">Connect movie sparks around the world to
				bring lightning impact</span> </header>
			<footer> <a href="#banner" class="button circled scrolly">START</a>
			</footer>
		</div>

		<!-- Nav -->
		<jsp:include page="navigation.jsp" />
	</div>

	<!-- Banner -->
	<div id="banner">
		<h2>
			Hi. You're looking at HOT topics on <strong>MovieSpark</strong>.
		</h2>
		<span class="byline">Join the discussions in interesting topics</span>
	</div>

	<!-- Carousel -->
	<div class="carousel">
		<div class="reel">
			<c:forEach var="topic" items="${topiclist}">			
				<article> 					
					<a href="${pageContext.request.contextPath}/topic_detail_link.do?topic_id=${topic.topic_id}" class="image featured"><img src="${topic.url}"></a> 				
					<header>
						<h3># ${topic.title } #</h3>
					</header>
				</article>
			</c:forEach>
		</div>
	</div>

	<!-- Footer -->
	<div id="footer">
		<div class="container">
			<div class="row">
				<div class="12u">
					<!-- Copyright -->
					<div class="copyright">
						<ul class="menu">
							<li>&copy; MovieSpark. &nbsp;&nbsp;All rights reserved.</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>