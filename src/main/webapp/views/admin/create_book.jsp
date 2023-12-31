<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Home Page</title>
    <jsp:include page="/fragments/css.jsp"/>
</head>
<body>
<jsp:include page="/fragments/navbar.jsp"/>

<div class="container mt-5">
    <div class="row">
        <div class="col-md-6 offset-3">
            <h1>Create Book</h1>
            <form action="/admin/book/create" method="post" enctype="multipart/form-data">
                <div class="mb-3">
                    <label for="title" class="form-label">title</label>
                    <input type="text" class="form-control" id="title" name="title">

                    <c:if test="${error.contains('title_null')}">
                        <span style="color: red"> Title is null </span>
                    </c:if>
                </div>
                <div class="mb-3">
                    <label for="description" class="form-label">Description</label>
                    <textarea class="form-control" id="description" name="description" cols="30" rows="5"></textarea>
                    <c:if test="${error.contains('description_null')}">
                        <span style="color: red"> Description is null </span>
                    </c:if>
                </div>
                <div class="mb-3">
                    <label for="file" class="form-label">File</label>
                    <input type="file" class="form-control" id="file" name="file">
                </div>

                <button type="submit" class="btn btn-primary">Save</button>
                <a href="/book/list" class="btn btn-warning">Back to book list</a>
                <a href="/" class="btn btn-primary">Back to Home Page</a>
            </form>
        </div>
    </div>
</div>
<jsp:include page="/fragments/js.jsp"/>
</body>
</html>
