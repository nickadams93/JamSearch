<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Home Page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="/welcome">${user.username}</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>odio
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
    <c:if test="${empty user.message}">
        <div class="jumbotron">
            <h1>Hello, ${user.firstName} ${user.lastName}!</h1>
            <p>In order to put your message out there to other musicians
            looking to jam, write a short message below.  Think about
            including preferred times, types of music, or other pertinent details.
            After you have set your message, other musicians can see your details and post on your board
            or send you an email looking to hook up and make the music happen.  After you have
            set your message feel free to head to search page and look for other musicians in your area
            who are looking to jam.</p>
            <p><a class="btn btn-primary btn-lg" href="/messageCreation" role="button">Set message!</a></p>
        </div>
    </c:if>
    <ul class="list-group">
        <c:forEach items="${user.posts}" var="post">
            <li class="list-group-item"><h2>From: ${post.sender}</h2><p>${post.content}</p><a href="/post/${post.sender}">Reply</a></li>
        </c:forEach>
    </ul>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
  <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
