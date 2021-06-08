<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Header</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<footer class="text-center text-lg-start bg-light text-muted">
    <section class="">
        <div class="container text-center text-md-start mt-5">
            <div class="row mt-3">
                <div class="col-md-3 col-lg-4 col-xl-3 mx-auto mb-4">
                    <h6 class="text-uppercase fw-bold mb-4">
                        <i class="fas fa-gem me-3"></i>Yurii Jungle
                    </h6>
                    <p>Yurii Jungle is new restaurant. The Yurii Jungle website reminds visitors that a special
                        event is more memorable when celebrated in an exceptional location.
                    </p>
                </div>
                <div class="col-md-2 col-lg-2 col-xl-2 mx-auto mb-4">
                    <h6 class="text-uppercase fw-bold mb-4">
                        Products
                    </h6>
                    <p><a href="${pageContext.request.contextPath}/index?category=pizza" class="text-reset">Pizza</a></p>
                    <p><a href="${pageContext.request.contextPath}/index?category=pasta" class="text-reset">Pasta</a></p>
                    <p><a href="${pageContext.request.contextPath}/index?category=fish" class="text-reset">Fish</a></p>
                </div>
                <div class="col-md-3 col-lg-2 col-xl-2 mx-auto mb-4">
                    <h6 class="text-uppercase fw-bold mb-4">
                        Useful links
                    </h6>
                    <p>
                        <a href="${pageContext.request.contextPath}/index" class="text-reset">Home</a>
                    </p>
                    <p>
                        <a href="${pageContext.request.contextPath}/index" class="text-reset">Home</a>
                    </p>
                    <p>
                        <a href="${pageContext.request.contextPath}/index" class="text-reset">Home</a>
                    </p>
                </div>
                <div class="col-md-4 col-lg-3 col-xl-3 mx-auto mb-md-0 mb-4">
                    <h6 class="text-uppercase fw-bold mb-4">
                        Contact
                    </h6>
                    <p><i class="fa fa-home" aria-hidden="true"></i> Lukasha 5, 902a, UA</p>
                    <p>
                        <i class="fa fa-envelope-o" aria-hidden="true"></i>
                        pavlykyuriy@gmail.com
                    </p>
                    <p><i class="fa fa-phone" aria-hidden="true"></i></i> +380 95 59 24 373</p>
                </div>
            </div>
        </div>
    </section>
</footer>
</html>