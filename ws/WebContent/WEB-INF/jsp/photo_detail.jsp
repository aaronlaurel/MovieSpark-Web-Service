<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Top -->
<jsp:include page="top.jsp" />

<!-- Main -->
<div class="wrapper style1">
	<div class="container">
		<div class="row">
			<div class="8u skel-cell-important" id="content">
				<article id="main">					
					<header>
						<h2>${picture.title}</h2>
					</header>
					<section>
						<a class="image featured"><img src="${picture.url}"></a>						
						<p style="float: right;">
							<a href="https://twitter.com/${user.screenName}"
								class="twitter-follow-button" data-show-count="true"
								data-size="large" data-lang="en">Follow @${user.screenName}</a>
						</p>
						<p>${picture.description}</p>
						<script>
							!function(d, s, id) {
								var js, fjs = d.getElementsByTagName(s)[0];
								if (!d.getElementById(id)) {
									js = d.createElement(s);
									js.id = id;
									js.src = "//platform.twitter.com/widgets.js";
									fjs.parentNode.insertBefore(js, fjs);
								}
							}(document, "script", "twitter-wjs");
						</script>
					</section>

					<div>
						<c:forEach var="oembed" items="${oembeds}">
							${oembed.html}
						</c:forEach>
					</div>
					<br />
					<form action="${pageContext.request.contextPath}/comment.do"
						method="post">
						<input type="hidden" name="pid" value="${picture.picture_id}" />
						<table>
							<tr>
								<td width="63%"><textarea name="comment"></textarea></td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td align="right"><input type="submit" class="button"
									value="Post a tweet" style="padding: 0.2em 1em 0.2em 1em;" />
								</td>
								<td>&nbsp;</td>
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