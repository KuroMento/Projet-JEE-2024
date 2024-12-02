<%--
  Created by IntelliJ IDEA.
  User: cytech
  Date: 17/11/2024
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CY Grades</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
    <jsp:include page="../../jsp_fragments/logo.jsp"/>
    <jsp:include page="../../jsp_fragments/header.jsp"/>
    <form method="get" class="center-content">
        <input type="hidden" name="categorie" value="grade">
        <jsp:include page="../../jsp_fragments/options.jsp"/>
        <jsp:include page="../../jsp_fragments/main_fragments/grade_main.jsp"/>
    </form>
    <jsp:include page="../../jsp_fragments/footer.jsp"/>
</body>
</html>
