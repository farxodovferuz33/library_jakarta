<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Page</title>
    <jsp:include page="/fragments/css.jsp"/>
</head>
<body>
<jsp:include page="/fragments/navbar.jsp"/>

<h1>Do you confirm to delete ${book.getTitle()} book ?</h1>
<form action="/admin/book/delete/${book.getId()}" method="post">
    <button type="submit" class="btn btn-danger">YES delete</button>
    <a href="/book/list" class="btn btn-warning">Back</a>
</form>

<jsp:include page="/fragments/js.jsp"/>
</body>
</html>
