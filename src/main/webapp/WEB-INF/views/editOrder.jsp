<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<html>
<head>
    <title>Edit order</title>
    <link rel="stylesheet" href="../../css/cabinet.css">
    <meta charset=utf-8/>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-9">
            <div class="main-body">
                <div class="row">
                    <div class="col-lg-10">
                        <form:form action="saveEditedOrder" method="post">
                            <div class="card">

                                <div class="card-body">
                                    <div class="row mb-3">
                                        <div class="col-sm-3">
                                            <input style="display: none" name="id" value="${order.id}"/>
                                            <h6 class="mb-0">Full name</h6>
                                        </div>
                                        <div class="col-sm-9 text-secondary">
                                                ${order.user.firstName} ${order.user.lastName}
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-sm-3">
                                            <h6 class="mb-0">Delivery Address</h6>
                                        </div>
                                        <div class="col-sm-9 text-secondary">
                                                ${order.deliveryAddress}
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-sm-3">
                                            <h6 class="mb-0">Order date</h6>
                                        </div>
                                        <div class="col-sm-9 text-secondary">
                                                ${order.orderDate}
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-sm-3">
                                            <h6 class="mb-0">Total price</h6>
                                        </div>
                                        <div class="col-sm-9 text-secondary">
                                                ${order.totalPrice} &#8372;
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-sm-3">
                                            <h6 class="mb-0">
                                                <form:label path="orderStatus.name" type="text">
                                                    Order status
                                                </form:label>
                                            </h6>
                                        </div>
                                        <div class="col-sm-9 text-secondary">
                                            <form:select path="orderStatus.name" class="form-select">
                                                <c:forEach var="item" items="${orderStatus}" varStatus="status">
                                                    <form:option value="${item.name}"/>
                                                </c:forEach>
                                            </form:select>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-sm-3">
                                            <h6 class="mb-0">
                                                <form:label path="paymentStatus">
                                                    Payment status</form:label>
                                            </h6>
                                        </div>
                                        <div class="col-sm-9 text-secondary">
                                            <div class="col-md-6">
                                                <div class="input-group">
                                                    <c:choose>
                                                        <c:when test="${order.paymentStatus eq true}">
                                                            <form:radiobutton path="paymentStatus" checked="true"
                                                                              value="True" label="True"/>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <form:radiobutton path="paymentStatus" value="True"
                                                                              label="True"/>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </div>
                                                <div class="input-group">
                                                    <c:choose>
                                                        <c:when test="${order.paymentStatus eq false}">
                                                            <form:radiobutton path="paymentStatus" checked="true"
                                                                              value="False" label="False"/>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <form:radiobutton path="paymentStatus" value="False"
                                                                              label="False"/>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-3"></div>
                                        <div class="col-sm-9 text-secondary">
                                            <button type="submit" class="btn btn-primary px-4"> Save Changes</button>
                                            <a href="${pageContext.request.contextPath}/admin" class="btn btn-info"
                                               aria-label="Close">Home</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
