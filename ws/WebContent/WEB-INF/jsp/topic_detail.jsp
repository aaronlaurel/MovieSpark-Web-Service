<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Top -->
<jsp:include page="top.jsp" />
			
<!-- Main -->
<div class="wrapper style1">
	<div class="container">
		<div class="row">
			<div class="8u skel-cell-important" id="content">
				<article id="main">
					<div style="float: right;"><a href="${pageContext.request.contextPath}/upload_photo_link.do?topic_id=${topic.topic_id }" class="button">Upload Photo</a></div>
					<header>
						<h2># ${topic.title} #</h2>										
					</header>
					<c:forEach var="picture" items="${picturelist }" >						
						<section>
							<header>
							<h3><a href="${pageContext.request.contextPath}/photo_detail_link.do?pid=${picture.picture_id }">${picture.title }</a></h3>
							</header>
                             <a href="${pageContext.request.contextPath}/photo_detail_link.do?pid=${picture.picture_id }" class="image featured">
                             	<img src="${picture.url }" />
                             </a>							
							<p>${picture.description }</p>
						</section>
					</c:forEach>
				</article>
			</div>
			
			<!-- Right Siderbar -->
			<jsp:include page="right.jsp" />
		</div>
	</div>
</div>

<!-- Footer -->
<jsp:include page="footer.jsp" />