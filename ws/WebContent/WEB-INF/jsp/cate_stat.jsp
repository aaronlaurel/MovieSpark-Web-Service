<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Top -->
<jsp:include page="top.jsp" />
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">
	google.load("visualization", "1", {
		packages : [ "corechart" ]
	});
	google.setOnLoadCallback(drawChart);
	function drawChart() {
		var data = google.visualization.arrayToDataTable([
				[ 'Catagory', 'Topic Amounts' ]
				<c:forEach var="cateList" items="${cateList}">
		          ,['${cateList.type}',${cateList.amounts}]
		        </c:forEach>
          ]);
		var options = {
			title : 'Popularity of Movie Categories',
			titleTextStyle : {
				fontSize : 30,
				bold : true,
				italic : false
			},
			pieHole: 0.4,
		}; 
		var chart = new google.visualization.PieChart(document
				.getElementById('donutchart'));
        function selectHandler() {
            var selectedItem = chart.getSelection()[0];
            if (selectedItem) {
              var topping = data.getValue(selectedItem.row, 0);
              window.open("cate_stat_redirect.do?cat=" + topping, "_self");
            }
        }
        google.visualization.events.addListener(chart, 'select', selectHandler); 
		chart.draw(data, options);
	}
</script>
<div id="donutchart" style="width: 100%; height: 600px"></div>

<!-- Footer -->
<jsp:include page="footer.jsp" />