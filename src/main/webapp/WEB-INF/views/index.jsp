<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="../../css/menu.css">
    <title>Restaurant</title>
    <script src="jquery-3.5.1.min.js"></script>
</head>
<jsp:include page="header.jsp"/>
<body>
<div class="container py-1">
    <div class="row">
        <div class="col-12 mt-4">
            <h3 class="text-center">Menu</h3>
            <div class="btn-group">
                <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
                        aria-haspopup="true" aria-expanded="false">Sort
                </button>
                <div class="dropdown-menu">
                    <a class="dropdown-item"
                       href="/index?pageId=${currentPage}&sortBy=name&category=${category}">Name</a>
                    <a class="dropdown-item"
                       href="/index?pageId=${currentPage}&sortBy=priceAsc&category=${category}">From cheap to expensive</a>
                    <a class="dropdown-item"
                       href="/index?pageId=${currentPage}&sortBy=priceDesc&category=${category}">From expensive to cheap</a>
                    <a class="dropdown-item"
                       href="/index?pageId=${currentPage}&sortBy=cookingTime&category=${category}">Cooking time</a>
                </div>
            </div>
            <a href="/index" class="btn btn-outline-primary btn-sm myCategoryClass" role="button">All</a>
            <sec:authorize access="isAuthenticated() and hasRole('ADMIN')">
                <c:forEach var="item" items="${allCategoryList}" varStatus="loop">
                    <a href="/index?category=${item.name}" class="btn btn-outline-primary btn-sm myCategoryClass"
                       role="button">${item.name}</a>
                </c:forEach>
            </sec:authorize>
            <sec:authorize access="!isAuthenticated() or hasRole('USER')">
            <c:forEach var="item" items="${categoryList}" varStatus="loop">
                <a href="/index?category=${item.name}" class="btn btn-outline-primary btn-sm myCategoryClass"
                   role="button">${item.name}</a>
            </c:forEach>
            </sec:authorize>
            <hr class="accent my-3">
            <p style="color: green">${message}</p>
            <p style="color: green">${orderMsg}</p>
            <c:choose>
                <c:when test="${fn:length(dishes) == 0}">
                    <p style="color: red">Dishes of this category is unavailable.</p>
                </c:when>
                <c:otherwise>
                    <c:if test="${sort != 'id'}">
                        <p style="color: green">Dishes are sorted by ${sort}</p>
                    </c:if>
                    <c:if test="${not empty category}">
                        <p style="color: green">Displayed category: ${category}</p>
                    </c:if>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="row">
            <c:forEach var="dish" items="${dishes}">
                <div class="col-sm-4">
                    <div class="card">
                        <div class="card-body">
                            <span class="float-right font-weight-bold">${dish.price} &#x20b4;</span>
                            <h6 class="text-truncate">${dish.name}</h6>
                            <h6 class="card-subtitle mb-2 text-muted">${dish.quantity} gr.</h6>
                            <sec:authorize access="isAuthenticated()  and hasRole('ADMIN')">
                                <h6 class="card-subtitle mb-2 text-muted">deleted: ${dish.deleted}</h6>
                                <h6 class="card-subtitle mb-2 text-muted">category: ${dish.category.name}</h6>
                            </sec:authorize>
                            <p class="small">
                                <c:forEach var="ingredient" items="${dish.ingredientSet}" varStatus="status">
                                    ${ingredient.name} <c:if test="${not status.last}">, </c:if>
                                </c:forEach>
                            </p>
                            <sec:authorize access="isAuthenticated() and hasRole('USER')">
                                <a class="btn btn-primary btn-sm"
                                   href="/addToBucket?dishId=${dish.id}&pageId=${currentPage}&sortBy=${sort}&category=${category}"
                                   role="button">Add to bucket</a>
                            </sec:authorize>
                            <sec:authorize access="isAuthenticated()  and hasRole('ADMIN')">
                                <c:choose>
                                    <c:when test="${not dish.deleted}">
                                        <a class="btn btn-danger btn-sm"
                                           href="/disabledDish?dishId=${dish.id}&pageId=${currentPage}&sortBy=${sort}&category=${category}"
                                           role="button">Disabled dish</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a class="btn btn-success btn-sm"
                                           href="/enabledDish?dishId=${dish.id}&pageId=${currentPage}&sortBy=${sort}&category=${category}"
                                           role="button">Enabled dish</a>
                                    </c:otherwise>
                                </c:choose>
                                <a class="btn btn-primary btn-sm"
                                   href="/editDish?dishId=${dish.id}"
                                   role="button">Edit</a>
                            </sec:authorize>
                            <sec:authorize access="!isAuthenticated()">
                                <a href="/login">Login to add to bucket</a>
                            </sec:authorize>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <nav aria-label="...">
        <ul class="pagination">
            <c:if test="${currentPage != 1}">
                <li class="page-item">
                    <a class="page-link" href="/index?pageId=${currentPage-1}&sortBy=${sort}&category=${category}">
                        Previous
                    </a>
                </li>
            </c:if>
            <c:forEach var="i" begin="1" end="${pages}">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <li class="page-item active">
                            <a class="page-link" href="#">${i}<span class="sr-only">(current)</span></a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a class="page-link"
                                                 href="/index?pageId=${i}&sortBy=${sort}&category=${category}">${i}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${currentPage < pages}">
                <li class="page-item">
                    <a class="page-link"
                       href="/index?pageId=${currentPage + 1}&sortBy=${sort}&category=${category}">
                        Next
                    </a>
                </li>
            </c:if>
        </ul>
    </nav>
</div>
<script src="../../js/menu.js"></script>
</body>
<jsp:include page="footer.jsp"/>
</html>