<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- Top -->
<jsp:include page="top.jsp" />
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['PicTitle','Topic']
          <c:forEach var="picList" items="${picList}">
          ,['${picList.title}',<fmt:formatNumber pattern="#,##0.00">${picList.ratio}</fmt:formatNumber>]
           </c:forEach>
        ]);

        var options = {
          title: 'User Behavior Analysis',
          titleTextStyle : {
        	  fontSize : 30,
			  bold : true,
		      italic : false
		  },
		  hAxis: {title: 'Topic List'},
          vAxis: {title: 'Number of Comments /  Clicks on Picutres'}
        };

        var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
        function selectHandler() {
            var selectedItem = chart.getSelection()[0];
            if (selectedItem) {
              var topping = data.getValue(selectedItem.row, 0);
              window.open("pic_stat_redirect.do?title=" + topping, "_self");
            }
        }
        google.visualization.events.addListener(chart, 'select', selectHandler); 
        chart.draw(data, options);
      }
    </script>
<div id="chart_div" style="width: 100%; height: 700px"></div>

<!-- Footer -->
<jsp:include page="footer.jsp" />