<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <link rel="stylesheet" href="../../css/cabinet.css">
</head>
<body>
<jsp:include page="header.jsp"/>

<div class="container">
    <div class="main-body">
        <div class="row gutters-sm">
            <div class="col-md-4 mb-3">
                <div class="card">
                    <div class="card-body">
                        <div class="d-flex flex-column align-items-center text-center">
                            <img src="https://bootdey.com/img/Content/avatar/avatar${photo}.png" alt="Admin"
                                 class="rounded-circle" width="150">
                            <div class="mt-3">
                                <h4>${user.firstName} ${user.lastName}</h4>
                                <p class="text-secondary mb-1">${user.email}</p>
                                <p class="text-muted font-size-sm">${user.address}</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-8">
                <div class="card mb-3">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Full Name</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${user.firstName} ${user.lastName}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Email</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${user.email}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Phone</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${user.phoneNumber}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Address</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${user.address}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-12">
                                <a class="btn btn-info " href="${pageContext.request.contextPath}/editProfile">Edit</a>
                            </div>
                            <div class="container bootdey">
                                <div class="panel panel-default panel-order">
                                    <div class="panel-heading">
                                        <c:if test="${fn:length(orders) != 0}">
                                            <strong>Order history</strong>
                                        </c:if>
                                    </div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-md-11">
                                                <c:forEach var="order" items="${orders}">
                                                    <div class="row">
                                                        <div class="col-md-12">
                                                            <div class="bg-transparent card-header pt-4 border-0 float-right">
                                                                    <span class="h6 text-muted ml-2">
                                                                            ${order.totalPrice} &#8372;
                                                                    </span>
                                                            </div>
                                                            <div class="pull-right">
                                                                <span>Status: </span>
                                                                <span class="text-success">
                                                                    <strong>${order.orderStatus.name}</strong>
                                                                </span>
                                                            </div>
                                                            <span class="label label-info">
                                                    <c:forEach var="item" items="${order.orderItems}"
                                                               varStatus="status">
                                                        ${item.dish.name}
                                                        <c:if test="${not status.last}">, </c:if>
                                                    </c:forEach>
                                                            </span><br>
                                                            <span style="color: #0f66e2;">
                                                                Address: ${order.deliveryAddress}</span>
                                                        </div>
                                                        <div class="col-md-12">
                                                            order made on: ${order.orderDate}
                                                        </div>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                        </div>
                                    </div>
                                    <nav aria-label="...">
                                        <ul class="pagination">
                                            <c:if test="${currentPage != 1}">
                                                <li class="page-item">
                                                    <a class="page-link" href="/profile?pageId=${currentPage-1}">
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
                                                                                 href="/profile?pageId=${i}">${i}</a>
                                                        </li>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                            <c:if test="${currentPage < pages}">
                                                <li class="page-item">
                                                    <a class="page-link"
                                                       href="/profile?pageId=${currentPage + 1}">
                                                        Next
                                                    </a>
                                                </li>
                                            </c:if>
                                        </ul>
                                    </nav>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>
