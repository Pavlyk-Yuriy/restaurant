<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="dns-prefetch" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css?family=Raleway:300,400,600" rel="stylesheet" type="text/css">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="../../css/registration.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <title>Registration</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<main class="my-form">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">Register</div>
                    <form:form modelAttribute="userModel" class="form-start" name="my-form"
                          action="/registration" method="POST">
                        <p style="color: red; text-align: center" >${usernameError}</p>
                        <p style="color: red; text-align: center" >${error}</p>
                        <div class="form-group row">
                            <form:label path="firstName" for="first_name" class="col-md-4 col-form-label text-md-right">
                               First Name
                            </form:label>
                            <div class="col-md-6">
                                <div class="input-group">
                                    <form:input required="required" path="firstName" type="text" id="first_name"
                                                class="form-control" name="first-name" placeholder="Enter firstName"/>
                                </div>
                            </div>
                        </div>
                        <div class="form-group row">
                            <form:label path="lastName" for="last_name" class="col-md-4 col-form-label text-md-right">Last Name</form:label>
                            <div class="col-md-6">
                                <div class="input-group">
                                    <form:input required="required" path="lastName" type="text" id="last_name"
                                                class="form-control" name="last-name" placeholder="Enter lastName"/>
                                </div>
                            </div>
                        </div>
                        <div class="form-group row">
                            <form:label path="email" type="email"
                                        for="email_address"
                                        class="col-md-4 col-form-label text-md-right">Email Address</form:label>
                            <div class="col-md-6">
                                <div class="input-group">
                                    <form:input required="required" path="email" type="text" id="email_address"
                                                class="form-control" name="email-address" placeholder="Enter email"/>
                                </div>
                            </div>
                        </div>

                        <div class="form-group row">
                            <form:label path="phoneNumber"  for="phone_number"
                                        class="col-md-4 col-form-label text-md-right">Phone Number</form:label>
                            <div class="col-md-6">
                                <div class="input-group">
                                    <form:input required="required" path="phoneNumber" type="text"
                                                class="form-control" id="phone_number" name="phone"
                                                placeholder="Enter phone number"/>
                                </div>
                            </div>
                        </div>
                        <div class="form-group row">
                            <form:label path="address"  for="address"
                                        class="col-md-4 col-form-label text-md-right">Address</form:label>
                            <div class="col-md-6">
                                <div class="input-group">
                                    <form:input required="required" path="address" type="text"
                                                class="form-control" id="address" name="address"
                                                placeholder="Enter address"/>
                                </div>
                            </div>
                        </div>
                        <div class="form-group row">
                            <form:label path="password"  for="password" class="col-md-4 col-form-label text-md-right">Password</form:label>
                            <div class="col-md-6">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                                    <form:input required="required" path="password" type="password"
                                                class="form-control" name="password" id="password"
                                           placeholder="Enter password"/>
                                </div>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="confirm" class="col-md-4 col-form-label text-md-right">Confirm Password</label>
                            <div class="col-md-6">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                                    <form:input path="passwordConfirm" required="required" type="password"
                                                class="form-control" name="confirm" id="confirm"
                                                placeholder="Confirm password"/>
                                </div>
                            </div>
                        </div>

                        <div class="form-group row">
                            <form:label path = "role" class="col-md-4 col-form-label text-md-right">Role</form:label>
                            <div class="col-md-6">
                                <div class="input-group">
                                    <form:radiobutton path = "role" checked = "true" value = "USER" label = "User"/>
                                </div>
                                <div class="input-group">
                                    <form:radiobutton path = "role" value = "ADMIN" label = "Admin" />
                                </div>
                            </div>
                        </div>


                        <div class="col-md-6 offset-md-4">
                            <button type="submit" onclick="return validform();" class="btn btn-primary"> Register</button>
                        </div>
                        <p style="text-align: center; padding-top: 10px">You have a account?
                            <a href="${pageContext.request.contextPath}/login" class="link-primary">Login</a>
                        </p>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</main>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</body>
<jsp:include page="footer.jsp"/>
</html>