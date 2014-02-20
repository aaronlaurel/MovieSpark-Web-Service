<script>
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(showPosition);
	} else {
		alert("Geolocation is not supported by this browser.");
	}

	function showPosition(position) {
		window.open("map.do?latitude=" + position.coords.latitude
				+ "&longitude=" + position.coords.longitude, "_self")
	}
</script>