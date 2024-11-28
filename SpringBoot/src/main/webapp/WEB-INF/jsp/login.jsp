<%--
  Created by IntelliJ IDEA.
  User: cytech
  Date: 17/11/2024
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <head>
        <title>CY JEE</title>
        <link rel="stylesheet" type="text/css" href="/css/style.css">
    </head>
<body>
    <jsp:include page="../../jsp_fragments/logo.jsp"/>
    <jsp:include page="../../jsp_fragments/header.jsp"/>
    <jsp:include page="../../jsp_fragments/options.jsp"/>
    <main>
        <div class="login_form">
            <form method="post" action="/FrontController">
                <label name="id"> Identifiant :</label>
                <input type="text" required name="id">
                <br>
                <label name="password"> Mot de passe :</label>
                <input type="password" required name="pw">
                <br>
                <input type="submit" value="Cliquez">
            </form>
            <p> If you want to test the site as an admin, you don't </p>
        </div>
    </main>
    <jsp:include page="../../jsp_fragments/footer.jsp"/>
</body>
</html>
