<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="../../css/bucket.css">
    <title>Bucket</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<jsp:include page="header.jsp"/>
<body>
<c:if test="${not empty bucket.bucketItems}">

</c:if>
<c:choose>
    <c:when test="${not empty bucket.bucketItems}">
        <div class="container">
            <div class="row">
                <div class="col-sm-12 col-md-10 col-md-offset-1">
                    <p style="color: green;font-weight: bold" class="text-center">${message}</p>
                    <p style="color: blue; font-weight: bold" class="text-center">${decreaseMsg}</p>
                    <p style="color: red; font-weight: bold" class="text-center">${decreaseMsgError}</p>
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>Product</th>
                            <th> </th>
                            <th class="text-center">Quantity</th>
                            <th> </th>
                            <th class="text-center">Price</th>
                            <th> </th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="bucketItem" items="${bucket.bucketItems}">
                            <tr>
                                <td class="col-sm-8 col-md-6">
                                    <div class="media">
                                        <a class="thumbnail pull-left" href="#"></a>
                                        <div class="media-body">
                                            <h4 class="media-heading"><a href="#">${bucketItem.dish.name}
                                            </a></h4>
                                            <p style="margin-bottom: 0px" class="small">
                                                <c:forEach var="ingredient" items="${bucketItem.dish.ingredientSet}"
                                                           varStatus="status">
                                                    ${ingredient.name} <c:if test="${not status.last}">, </c:if>
                                                </c:forEach>
                                            </p>
                                       </div>
                                    </div>
                                </td>
                                <td>
                                    <a class="btn btn-primary btn-floating border border-danger" id="minus"
                                       href="/decreaseDish?dishId=${bucketItem.dish.id}" role="button">
                                        <i class="fa fa-minus" aria-hidden="true"></i>
                                    </a>
                                </td>
                                <td class="col-sm-1 col-md-1" style="text-align: center">
                                    <strong>${bucketItem.quantity}</strong>
                                </td>
                                <td>

                                    <a class="btn btn-primary btn-floating border border-success" id="plus"
                                       href="/increaseDish?dishId=${bucketItem.dish.id}" role="button">
                                        <i class="fa fa-plus" aria-hidden="true"></i>
                                    </a>
                                </td>
                                <td class="col-sm-1 col-md-1 text-center">
                                    <strong>${bucketItem.dish.price} &#x20b4;</strong></td>
                                <td class="col-sm-1 col-md-1">
                                    <a class="btn btn-primary btn-danger glyphicon glyphicon-remove"
                                       href="/removeFromBucket?dishId=${bucketItem.dish.id}" role="button">Remove</a>
                                </td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td>  </td>
                            <td>  </td>
                            <td>  </td>
                            <td>  </td>
                            <td class="text-center"><h3>Total</h3></td>
                            <td class="text-right">
                                <h5>
                                    <strong>${bucket.totalPrice} &#x20b4;</strong>
                                </h5>
                            </td>
                        </tr>
                        <tr>
                            <td>  </td>
                            <td>  </td>
                            <td>  </td>
                            <td>  </td>
                            <td>
                                <button type="button" class="btn btn-default">
                                    <span class="glyphicon glyphicon-shopping-cart"></span> Create order?
                                </button>
                            </td>
                            <td>
                                <button class="btn btn-success" type="button" id="formButton">Order</button>
                            </td>
                        </tr>
                        <tr>
                            <td>  </td>
                            <td>  </td>
                            <td>  </td>
                            <td>  </td>
                            <td>
                                <button type="button" class="btn btn-default">
                                    <span class="glyphicon glyphicon-shopping-cart"></span> Delete all
                                </button>

                            </td>
                            <td>
                                <a class="btn btn-danger"
                                   href="/deleteAllDishesFromBucket?bucketId=${bucket.id}" role="button">Clear all</a>
                            </td>
                        </tr>
                        <tr>
                            <td>  </td>
                            <td>  </td>
                            <td>  </td>
                            <td>  </td>
                            <td colspan="2">
                                <form id="street-form" action="/createOrder" method="POST">
                                    <div class="form-group">
                                        <label class="control-label">Address</label>
                                        <input required type="text" class="form-control" id="address_id" name="address"
                                               placeholder="Enter your address"/>
                                    </div>
                                    <div class="form-group">
                                        <button type="submit" class="btn btn-primary">Create order</button>
                                    </div>
                                </form>
                            </td>
                        </tr>
                        </tbody>
                    </table>

                </div>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <p style="color: blue;text-align: center; font-weight: bold;">
            Cart is empty. Please, add dishes to bucket.<br>
            <a href="${pageContext.request.contextPath}/index">Return to order the dish</a>
        </p>

    </c:otherwise>
</c:choose>
<script src="../../js/mybucket.js"></script>
</body>
<jsp:include page="footer.jsp"/>
</html>