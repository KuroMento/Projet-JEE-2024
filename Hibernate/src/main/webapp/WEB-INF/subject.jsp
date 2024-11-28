<%@ page import="fr.cyu.jee.model.Subject" %>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CY Subjects</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
    <jsp:include page="../jsp_fragments/logo.jsp"/>
    <jsp:include page="../jsp_fragments/header.jsp"/>
    <form method="get" class="center-content">
        <input type="hidden" name="categorie" value="subject">
        <jsp:include page="../jsp_fragments/options.jsp"/>
        <jsp:include page="../jsp_fragments/main_fragments/subject_main.jsp"/>
    </form>
    <jsp:include page="../jsp_fragments/footer.jsp"/>
</body>
</html>
