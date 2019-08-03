<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<meta charset="utf-8">
<title>Make the music happen.</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/welcome">${user.username}</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="/welcome">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/searchpage">Search Page</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" onclick="document.forms['logoutForm'].submit()" href="login.jsp">Logout</a>
            </li>
        </ul>
    </div>
</nav>
<head>
    <style>
        #map {
            height: 800px;
            width: 100%;
        }
    </style>
</head>
<h3>Find Your Musical Match!</h3>
<div id="map"></div>
<script>
    var geocoder;
    var map;
    function initMap() {
        geocoder = new google.maps.Geocoder();
        var omaha = {lat: 41.2565, lng: -95.9345};
        map = new google.maps.Map(
            document.getElementById('map'), {zoom: 10, center: omaha});
        <c:forEach items="${users}" var="user">

        codeAddress("${user.address}, ${user.zipCode}")
        </c:forEach>

    }

    function codeAddress(address) {
        //var address = document.getElementById('address').value;
        geocoder.geocode( { 'address': address}, function(results, status) {
            if (status == 'OK') {
                map.setCenter(results[0].geometry.location);
                var marker = new google.maps.Marker({
                    map: map,
                    position: results[0].geometry.location,
                    title: "${user.username}"
                });
                marker.addListener('click', function() {
                    var infowindow = new google.maps.InfoWindow({
                        content: "I love the guitar",
                        maxWidth: 200
                    });
                    infowindow.open(map, marker);
                });
            } else {
                alert('Geocode was not successful for the following reason: ' + status);
            }
        });
    }


</script>
<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBCKCqfi6N1AVJdWzmvPERdCzcKvl3uIQE&callback=initMap"
        type="text/javascript"></script>
</body>
</html>