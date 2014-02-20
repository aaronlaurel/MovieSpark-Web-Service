$(function() {
	$('#searchbox').change(function() {
		var searchInput = $('#searchbox').val();
		$("#detail").html("");
		$.ajax({
			url : 'http://www.omdbapi.com/?t=' + searchInput,
			method : 'get',
			dataType : 'jsonp',
			success : function(movie) {
				if (movie['Error'] != null) {
					$('#searchbox').val(movie['Error']);
				} else {
					$('#searchbox').val(movie["Title"]);
					var plot = movie["Plot"];
					var released = movie["Released"];
					var poster = movie["Poster"];
					var runtime = movie["Runtime"];
		            var titleLink = $('<div><div><img src="' + poster + '"/></div>' + '<div>Title: ' + movie["Title"] + '</div></div>' + '<div>Runtime: ' + runtime + '</div><div>Released: ' + released + '</div><div>Plot: ' + plot + '</div></div>');  
		            titleLink.appendTo("#detail");
				}
			}
		});
	});
});