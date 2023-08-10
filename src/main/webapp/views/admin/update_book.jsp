<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <h1>Update Book ${book.getTitle()}</h1>
            <form action="/admin/book/update/${book.getId()}" method="post">
                <div class="mb-3">
                    <label for="title" class="form-label">title</label>
                    <input value="${book.getTitle()}" type="text" class="form-control" id="title" name="title">

<%--                    <c:if test="${upt_err.contains('title_null')}">--%>
<%--                        <span style="color: red"> Title is null </span>--%>
<%--                    </c:if>--%>
                </div>
                <div class="mb-3">
                    <label for="description" class="form-label">Description</label>
                    <textarea class="form-control" id="description" name="description" cols="30" rows="5">${book.getDescription()}</textarea>
<%--                    <c:if test="${upt_err.contains('description_null')}">--%>
<%--                        <span style="color: red"> Description is null </span>--%>
<%--                    </c:if>--%>
                </div>

                <button type="submit" class="btn btn-primary">Update</button>
                <a href="/book/list" class="btn btn-warning">Back</a>
            </form>
        </div>
    </div>
</div>
<jsp:include page="/fragments/js.jsp"/>
</body>
</html>
