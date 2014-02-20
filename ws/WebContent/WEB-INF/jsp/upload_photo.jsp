<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Top -->
<jsp:include page="top.jsp" />
<style type="text/css">
</style>
<!-- Main -->
<div class="wrapper style1">
	<div class="container">
		<div class="row">
			<div class="8u skel-cell-important" id="content">
				<article id="main">
					<div id="banner">
				        <h2 style="text-align:left; font-size: 2.5em;">Upload a Photo</h2>
						<span class="byline" style="text-align:left;">Share your photos to join current discussions</span>
					</div>
					<c:forEach var="error" items="${errors}">
			            <h3 style="color:red; font-size: 18px; margin-left:50px;">${error}</h3>
			        </c:forEach>
				    <form action="${pageContext.request.contextPath}/upload_photo.do" method="post" enctype="multipart/form-data">
						<table class="style1" style="width:30%; margin-left:30px;">
							<input type="hidden" name="topic_id" value="${topic.topic_id}"/>
				        	<tr>
								<th>Topic</th>
								<th># ${topic.title} #</th>
							</tr>
							<tr>
								<th>Title</th>
								<td><input type="text" name="title" size="50"/></td>
							</tr>
							<tr>
								<th>Movie</th>
								<td><input id="searchbox" type="text" name="movie" size="50" placeholder="Type related movie name"/></td>
							</tr>
							<tr>
                                <td>Search Result</td>
                                <td><div id="detail"></div></td>
                            </tr>
							<tr>
								<th>Photo</th>
								<td><input type="file" name="photo"  size="50" /></td>
							</tr>
							<tr>
								<th>Description</th>
								<td><textarea name="description"></textarea></td>
							</tr>
							<tr>
								<td colspan="2" align="center"><input type="submit" name="submit" class="button" value="Upload Photo" style="padding: 0.2em 1em 0.2em 1em; margin: 3em 1em 3em 1em;"/></td>
							</tr>
						</table>
					</form>
				</article>
			</div>
			
			<!-- Right Siderbar -->
			<jsp:include page="right.jsp" />
		</div>
	</div>
</div>

<!-- Footer -->
<jsp:include page="footer.jsp" />