<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<meta charset="utf-8">
<title>Create an account</title>
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
                <a class="nav-link" href="searchpage.jsp">Search Page</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" onclick="document.forms['logoutForm'].submit()" href="login.jsp">Logout</a>
            </li>
        </ul>
    </div>
</nav>
<html>
<head>
    <style>
        #map {
            height: 800px;
            width: 100%;
        }
    </style>
</head>
<body>
<h3>Find Your Musical Match!</h3>
<div id="map"></div>
<script>
    function initMap() {
        var omaha = {lat: 41.2565, lng: -95.9345};
        var map = new google.maps.Map(
            document.getElementById('map'), {zoom: 1, center: omaha});
        <c:forEach items="${users}" var="user">
        var marker = new google.maps.Marker({position: codeAddress("${user.address}, ${user.zipCode}"), map: map});
        </c:forEach>

    }

    function codeAddress(address) {
        geocoder.geocode( { 'address': address}, function(results, status) {
            if (status == 'OK') {
                map.setCenter(results[0].geometry.location);
                var marker = new google.maps.Marker({
                    map: map,
                    position: results[0].geometry.location
                });
            } else {
                alert('Geocode was not successful for the following reason: ' + status);
            }
        });}
</script>
<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAyOMuRltFLwDXWDl1-tREqojpS065Qu5I&callback=initMap">
</script>
</body>
</html>