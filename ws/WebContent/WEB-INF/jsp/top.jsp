<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>MovieSpark by Team One</title>
        <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery.dropotron.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/skel.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/skel-panels.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/init.js"></script>
        <script src="${pageContext.request.contextPath}/js/movie.js"></script>
        <noscript>
            <link rel="stylesheet" href="${pageContext.request.contextPath}/css/skel-noscript.css" />
            <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
            <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style-desktop.css" />
            <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style-noscript.css" />
        </noscript>
	</head>
	<body class="no-sidebar">

		<!-- Header -->
			<div id="header">

				<!-- Inner -->
				<div class="inner">
					<header>
						<h1><a href="${pageContext.request.contextPath}/init.do" id="logo">MovieSpark</a></h1>
					</header>
				</div>
				
				<!-- Nav -->
				<jsp:include page="navigation.jsp" />
			</div>