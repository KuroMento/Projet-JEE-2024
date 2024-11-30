<%@ page import="fr.cyu.jee.model.User" %>
<%@ page import="fr.cyu.jee.model.Permissions" %><%--
  Created by IntelliJ IDEA.
  User: cytech
  Date: 10/11/2024
  Time: 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CY Board</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css?v=1.0">
</head>
<body>
    <jsp:include page="../../jsp_fragments/logo.jsp"/>
    <jsp:include page="../../jsp_fragments/header.jsp"/>
    <form method="get" class="center-content" action="/FrontController">
        <jsp:include page="../../jsp_fragments/options.jsp"/>
        <jsp:include page="../../jsp_fragments/main_fragments/index_main.jsp"/>
    </form>
    <jsp:include page="../../jsp_fragments/footer.jsp"/>
</body>
</html>
