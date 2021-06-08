<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Header</title>
    <link rel="stylesheet" href="../../css/header.css">
</head>
<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-white">
        <div class="container-fluid">
            <div class="site-identity">
                <h2><a href="${pageContext.request.contextPath}/index">Yurii Jungle</a></h2>
            </div>
            <button class="navbar-toggler"
                    type="button"
                    data-mdb-toggle="collapse"
                    data-mdb-target="#navbarExample01"
                    aria-controls="navbarExample01"
                    aria-expanded="false"
                    aria-label="Toggle navigation">
                <i class="fas fa-bars"></i>
            </button>
            <div class="collapse navbar-collapse" id="navbarExample01">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item active">
                        <a class="nav-link" aria-current="page" href="${pageContext.request.contextPath}/index">Home</a>
                    </li>
                    <sec:authorize access="isAuthenticated()">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/profile">Profile</a>
                    </li>
                    </sec:authorize>

                    <sec:authorize access="isAuthenticated() and hasRole('USER')">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/bucket">Bucket</a>
                    </li>
                    </sec:authorize>

                    <sec:authorize access="!isAuthenticated()">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/login">Login</a>
                    </li>
                    </sec:authorize>

                    <sec:authorize access="!isAuthenticated()">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/registration">Registration</a>
                    </li>
                    </sec:authorize>

                    <sec:authorize access="hasRole('ADMIN')">
                        <a class="nav-link" href="${pageContext.request.contextPath}/admin">Admin</a>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ADMIN')">
                        <a class="nav-link"
                           href="${pageContext.request.contextPath}/addDish">Add dish/ingredient/category</a>
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                        <li class="nav-item">
                            <a class="nav-link" href="/logout">Logout</a>
                        </li>
                    </sec:authorize>
                </ul>
            </div>
        </div>
    </nav>
</header>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</html>