<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Top -->
<jsp:include page="top.jsp" />
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
    	  var data = google.visualization.arrayToDataTable([
                ['Title', 'rank', 'pictureStat', 'Catagory','commentStat']
                <c:forEach var="topicList" items="${TopicList}">
                ,['${topicList.title}', ${topicList.rank}, ${topicList.picStat},'${topicList.type}',${topicList.commStat}]
                 </c:forEach>
              ]);

              var options = {
                title: 'Popularity of Movie Topics',
                titleTextStyle : {
                	fontSize : 30,
      			    bold : true,
      		        italic : false
      		 	},
                hAxis: {title: 'Topic Rank'},
                vAxis: {title: 'Number of Pictures'},
                bubble: {textStyle: {fontSize: 11}}
              };

        var chart = new google.visualization.BubbleChart(document.getElementById('chart_div'));
        
        function selectHandler() {
            var selectedItem = chart.getSelection()[0];
            if (selectedItem) {
              var topping = data.getValue(selectedItem.row, 0);
              window.open("topic_stat_redirect.do?title=" + topping, "_self");
            }
        }
        
        google.visualization.events.addListener(chart, 'select', selectHandler); 
        chart.draw(data, options);
      }
    </script>
<div id="chart_div" style="width: 100%; height: 700px"></div>

<!-- Footer -->
<jsp:include page="footer.jsp" />