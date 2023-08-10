<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head>
    <title>Home Page</title>
    <jsp:include page="/fragments/css.jsp"/>
</head>
<body>
<jsp:include page="/fragments/navbar.jsp"/>

<div class="row">
    <div class="col-md-10 offset-1">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Title</th>
                <th scope="col">Type</th>
                <th scope="col">Size</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${books}" var="book">

                <tr>
                    <th>${book.getId()}</th>
                    <th>
                        <a href="/book/detail/${book.getId()}">${book.getTitle()}</a>
                    </th>
                    <th>${book.getFile().getExtension()}</th>
                    <th>
                        <fmt:formatNumber value="${book.getFile().getSize()/1024/1024}" pattern="#,##0.00" />
                        mb
                    </th>
                    <th>
                        <c:if test="${session.getAttribute('role').equals('ADMIN')}">
                            <a href="/admin/book/delete/${book.getId()}" class="btn btn-danger">
                                DELETE BOOK
                            </a>||
                            <a href="/admin/book/update/${book.getId()}" class="btn btn-warning">
                                UPDATE BOOK
                            </a>
                        </c:if>
                    </th>
                </tr>
            </c:forEach>

            </tbody>
        </table>

        <div style="position: fixed; top: 600px" >
            <c:if test="${pageNumber > 1}">
                <a class="btn btn-success" href="${pageContext.request.contextPath}/book/list?page=${pageNumber - 1}">Previous</a>
            </c:if>

            <c:forEach begin="1" end="${totalPages}" var="page">
                <c:choose>
                    <c:when test="${pageNumber == page}">
                        <span class="btn btn-primary">${page}</span>
                    </c:when>
                    <c:otherwise>
                        <a class="btn btn-outline-secondary" href="/book/list?page=${page}">${page}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${pageNumber < totalPages}">
                <a class="btn btn-success" href="${pageContext.request.contextPath}/book/list?page=${pageNumber + 1}">Next</a>
            </c:if>
            <a class="btn btn-warning" href="/">Back to home page</a>

        </div>
    </div>
</div>

<jsp:include page="/fragments/js.jsp"/>
</body>
</html>
