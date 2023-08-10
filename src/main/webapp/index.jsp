
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
  <jsp:include page="fragments/css.jsp"/>
</head>
<body>
<jsp:include page="fragments/navbar.jsp"/>

<div class="container">
    <h2 class="my-3" style="text-align: center; color: cadetblue">In this website you can find any programming book for free and paid versions</h2>
    <h4 class="my-5" style="text-align: center">What are in this Web site</h4>

    <ul style="text-align: center; list-style: none">
        <li>Built in Jakarta EE (JPA) (Bootstrap)</li>
        <li>CRUD with Admin page</li>
        <li>Preview book before downloading</li>
        <li>Download Book</li>
        <li>Preview Book description (Size, ...)</li>
        <li>Pagination</li>
        <li>Remember me function</li>
        <li>Session Cookies</li>
        <li>Entity Check Validations</li>
        <li>Login required</li>
        <li>Password saved hashed version</li>
        <li>Others</li>
    </ul>
</div>

<jsp:include page="fragments/js.jsp"/>
</body>
</html>
