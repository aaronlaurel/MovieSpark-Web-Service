<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Top -->
<jsp:include page="top.jsp" />
			
<!-- Main -->
<div class="wrapper style1">
	<div class="container">
		<div style="float: right;"><a href="${pageContext.request.contextPath}/create_topic_link.do" class="button">Create &nbsp;A &nbsp;Topic</a></div>
		<div id="banner">
        	<h2 style="text-align:left; font-size: 2.2em;">Hot Topics &nbsp;&nbsp;&nbsp;&nbsp;
        	<a href="${pageContext.request.contextPath}/topic_list_link.do" style="font-size: 18px;">See More &gt;&gt;</a></h2>
			<!-- <span class="byline" style="text-align:left;">Here are the boling hot topics on the MovieSpark. Join the discussion!</span> -->
		</div>
		<div class="row">
			<c:forEach var="topic" items="${hotesttopic}">
				<article class="4u special">
					<header>
						<h3 style="padding-top:0em"><a href="${pageContext.request.contextPath}/topic_detail_link.do?topic_id=${topic.topic_id}"># ${topic.title} #</a></h3>
					</header>					
					<a href="${pageContext.request.contextPath}/topic_detail_link.do?topic_id=${topic.topic_id}" class="image featured"><img src="${topic.url }" /></a>				
				</article>
			</c:forEach>
		</div>
		<hr />
		<div id="banner">
        	<h2 style="text-align:left; font-size: 2.2em;">Recent Topics &nbsp;&nbsp;&nbsp;&nbsp;
        	<a href="${pageContext.request.contextPath}/topic_list_link.do" style="font-size: 18px;">See More &gt;&gt;</a></h2>
			<!-- <span class="byline" style="text-align:left;">Here are the topics contributed by our Sparkers recently. Join the discussion!</span> --> 
		</div>
		<div class="row">
			<c:forEach var="topic" items="${latesttopic}">
				<article class="4u special">
					<header>
						<h3 style="padding-top:0em">
							<a href="${pageContext.request.contextPath}/topic_detail_link.do?topic_id=${topic.topic_id}"># ${topic.title} #</a>
						</h3>
					</header>				
					<a href="${pageContext.request.contextPath}/topic_detail_link.do?topic_id=${topic.topic_id}" class="image featured"><img src="${topic.url }" /></a>
				</article>
			</c:forEach>
		</div>
	</div>
</div>

<!-- Footer -->
<jsp:include page="footer.jsp" />