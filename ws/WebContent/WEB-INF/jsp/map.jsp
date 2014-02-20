<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Top -->

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <title>MovieSpark by Team One</title>
        <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery.dropotron.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/skel.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/init.js"></script>
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


<script
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB88KAvqVW035HB3HUvWhh-T379_0702S0&sensor=true"></script>
<script
    src="//google-maps-utility-library-v3.googlecode.com/svn/trunk/geolocationmarker/src/geolocationmarker-compiled.js"></script>
<script>
    var map, GeoMarker;

    function initialize() {
        var mapOptions = {
            zoom : 15,
            center : new google.maps.LatLng(0, 0),
            mapTypeId : google.maps.MapTypeId.ROADMAP
        };
        map = new google.maps.Map(document.getElementById('map_canvas'),
                mapOptions);

        GeoMarker = new GeolocationMarker();
        GeoMarker.setCircleOptions({
            fillColor : '#808080'
        });

        google.maps.event.addListenerOnce(GeoMarker, 'position_changed',
                function() {
                    map.setCenter(this.getPosition());
                });

        google.maps.event.addListener(GeoMarker, 'geolocation_error', function(
                e) {
            alert('There was an error obtaining your position. Message: '
                    + e.message);
        });

        GeoMarker.setMap(map);

        var features = [ 
            <c:forEach var="photo" items="${photos}">
            {
                position : new google.maps.LatLng(${photo.photoLocation.latitude}, ${photo.photoLocation.longitude}),
                url : '${photo.url}',
                icon : {
                    url : '${photo.squareUrl}',
                    size : new google.maps.Size(100, 100),
                    scaledSize : new google.maps.Size(100, 100)
                }
            },
            </c:forEach>
            {}];
        for (var i = 0; i < features.length - 1; i++) {
            addMarker(features[i]);
        };
    }

    function addMarker(feature) {
        var marker = new google.maps.Marker({
            position : feature.position,
            icon : feature.icon,
            url : feature.url,
            map : map
        });
        google.maps.event.addListener(marker, 'click', function() {
            window.open(marker.url, "_self");
        });
    }

    google.maps.event.addDomListener(window, 'load', initialize);

    if (!navigator.geolocation) {
        alert('Your browser does not support geolocation');
    }
</script>

        <style>
html,body,#map_canvas {
    margin: 0;
    padding: 0;
    height: 100%;
}
</style>

<div id="map_canvas"></div>

<!-- Footer -->
<jsp:include page="footer.jsp" />