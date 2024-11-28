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
    <title>Projet JEE</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
    <jsp:include page="../../jsp_fragments/logo.jsp"/>
    <jsp:include page="../../jsp_fragments/header.jsp"/>
    <jsp:include page="../../jsp_fragments/options.jsp"/>
    <%
        String categorie = request.getParameter("categorie");
        if (categorie != null) {
            response.getWriter().print("<main>" + categorie + "</main>");
        }
        else{
            response.getWriter().print("<main> Main </main>");
        }
    %>
    <jsp:include page="../../jsp_fragments/footer.jsp"/>
</body>
</html>
