<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Admin</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
    <c:if test="${not empty currentOrder}">
        <div class="align-items-center">
            <div class="main-body">
                <div class="row gutters-sm">
                    <div class="col-md-4 mb-3">
                        <div class="card">
                            <div class="d-flex flex-column align-items-center text-center">
                                <div class="bg-transparent">
                                    <a href="/admin?pageId=${currentPage}" class="btn-close" aria-label="Close">Close cart</a>
                                    <br>
                                        <span class="h6 text-muted ml-2">
                                             ${currentOrder.totalPrice} &#8372;
                                         </span>
                                </div>
                                <div class="pull-right">
                                    <c:if test="${not empty currentOrder.orderStatus}">
                                        <span>Status: </span>
                                        <span class="text-success">
                                            <strong>${currentOrder.orderStatus.name}</strong>
                                        </span>
                                    </c:if>
                                </div>
                                <span class="label label-info">
                                <c:forEach var="item" items="${currentOrder.orderItems}" varStatus="status">
                                    ${item.dish.name} - ${item.quantity} pc.<br>
                                </c:forEach>
                                </span>
                                <span style="color: #0f66e2;">Address: ${currentOrder.deliveryAddress}</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
    <a class="btn btn-danger" href="/admin?pageId=${currentPage}&sortBy=active" role="button">Active order</a>
    <c:if test="${active eq 'active'}">
        <a class="btn btn-primary" href="/admin" role="button">Go home</a>
    </c:if>
    <table class="table">
        <thead class="thead-light">
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Full name</th>
            <th scope="col">Delivery address</th>
            <th scope="col">Order date</th>
            <th scope="col">Payment status</th>
            <th scope="col">Order status</th>
            <th scope="col">Total price</th>
            <th scope="col"></th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="order" items="${orders}">
            <tr
                    <c:choose>
                        <c:when test="${order.paymentStatus eq false}">
                            class="table-danger"
                        </c:when>
                        <c:when test="${order.orderStatus.name ne 'delivering'}">
                            class="table-danger"
                        </c:when>
                        <c:otherwise>
                            class="table-success"
                        </c:otherwise>
                    </c:choose>
            >
                <th scope="row">${order.id}</th>
                <td>${order.user.firstName} ${order.user.lastName}</td>
                <td>${order.deliveryAddress}</td>
                <td>${order.orderDate}</td>
                <td>${order.paymentStatus}</td>
                <td>${order.orderStatus.name}</td>
                <td>${order.totalPrice}</td>
                <td>
                    <a class="btn btn-info"
                       href="/getUserOrderInfo?orderId=${order.id}&sortBy=${active}&pageId=${currentPage}">
                        Show details</a>
                </td>
                <td>
                    <a href="/editOrder?orderId=${order.id}">Edit</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <c:if test="${active ne 'active'}">
        <nav aria-label="...">
            <ul class="pagination">
                <c:if test="${currentPage != 1}">
                    <li class="page-item">
                        <a class="page-link" href="/admin?pageId=${currentPage-1}">
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
                                                     href="/admin?pageId=${i}">${i}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${currentPage < pages}">
                    <li class="page-item">
                        <a class="page-link"
                           href="/admin?pageId=${currentPage + 1}">Next
                        </a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </c:if>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>