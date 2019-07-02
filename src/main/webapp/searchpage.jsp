<!DOCTYPE html>
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
            document.getElementById('map'), {zoom: 4, center: omaha});
        var marker = new google.maps.Marker({position: omaha, map: map});
    }
</script>
<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAyOMuRltFLwDXWDl1-tREqojpS065Qu5I&callback=initMap">
</script>
</body>
</html>