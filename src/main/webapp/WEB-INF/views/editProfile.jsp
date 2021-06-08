<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8"%>
<html>
<head>
    <title>Edit profile</title>
    <link rel="stylesheet" href="../../css/cabinet.css">
    <meta charset=utf-8 />
</head>
<body>
<jsp:include page="header.jsp"/>

<div class="container">
    <div class="main-body">
        <div class="row">
            <div class="col-md-4 mb-3">
                <div class="card">
                    <div class="card-body">
                        <div class="d-flex flex-column align-items-center text-center">
                            <img src="https://bootdey.com/img/Content/avatar/avatar7.png" alt="Admin"
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
            <div class="col-lg-8">
                <form:form modelAttribute="userModel" action="saveEditedProfile" method="post">
                <div class="card">
                    <div class="card-body">
                        <div class="row mb-3">
                            <div class="col-sm-3">
                                <h6 class="mb-0">First Name</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                <form:input path="firstName" type="text" class="form-control" value="${user.firstName}"/>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Last Name</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                <form:input path="lastName" type="text" class="form-control" value="${user.lastName}"/>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Email</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                <form:input path="email" type="text" class="form-control" value="${user.email}"/>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Phone</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                <form:input path="phoneNumber" type="text" class="form-control" value="${user.phoneNumber}"/>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-sm-3">
                                <h6 class="mb-0">Address</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                <form:input path="address" type="text" class="form-control" value="${user.address}"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-3"></div>
                            <div class="col-sm-9 text-secondary">
                                <button type="submit" class="btn btn-primary px-4"> Save Changes</button>
                            </div>
                        </div>
                    </div>
                </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
