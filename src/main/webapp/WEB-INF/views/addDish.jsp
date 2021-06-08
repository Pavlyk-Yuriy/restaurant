<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add dish</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="main-body">
        <div class="row">
            <div class="col-lg-10">
                <form:form modelAttribute = "dishModel" action="saveDish" method="post">
                    <div class="card">
                        <div class="card-body">
                            <div class="row mb-3">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Name</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <form:input path="name" type="text" class="form-control" required="true"/>
                                </div>
                            </div>
                            <div class="row mb-3">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Price</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <form:input path="price" type="number" min = "1" class="form-control" />
                                </div>
                            </div>
                            <div class="row mb-3">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Quantity</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <form:input path="quantity" type="number" min = "1" class="form-control" />
                                </div>
                            </div>
                            <div class="row mb-3">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Category</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <form:select path="category.name" class="form-select">
                                        <c:forEach var="item" items="${categoryList}" varStatus="status">
                                            <form:option value="${item.name}"/>
                                        </c:forEach>
                                    </form:select>
                                </div>
                            </div>
                            <div class="row mb-3">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Cooking time</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <form:input path="cookingTime" type="time"  value="00:00:00"
                                                class="form-control" required="true"/>
                                </div>
                            </div>
                            <div class="row mb-3">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Ingredients</h6>
                                </div>
                                <div class="col-sm-9">
                                    <c:forEach var="item" items="${ingredientSet}">
                                        <span class="checkbox">
                                            <form:label path = "ingredientSet"> ${item.name}</form:label>
                                            <form:checkbox path="ingredientSet" value="${item}"/> &nbsp; &nbsp;
                                        </span>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-3"></div>
                                <div class="col-sm-9 text-secondary">
                                    <button type="submit" class="btn btn-primary px-4"> Add dish</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form:form>
            </div>
            <div class="col-lg-10">
                <form:form modelAttribute = "categoryModel" action="saveCategory" method="post">
                    <div class="card">
                        <div class="card-body">
                            <div class="row mb-3">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Category</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <form:input path="name" type="text" class="form-control" required="true"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-3"></div>
                                <div class="col-sm-9 text-secondary">
                                    <button type="submit" class="btn btn-primary px-4"> Add category</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form:form>
            </div>
            <div class="col-lg-10">
                <form:form modelAttribute = "ingredientModel" action="saveIngredient" method="post">
                    <div class="card">
                        <div class="card-body">
                            <div class="row mb-3">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Ingredient</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                        <form:input path="name" type="text" class="form-control" required="true"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-3"></div>
                                <div class="col-sm-9 text-secondary">
                                    <button type="submit" class="btn btn-primary px-4"> Add ingredient</button>
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
