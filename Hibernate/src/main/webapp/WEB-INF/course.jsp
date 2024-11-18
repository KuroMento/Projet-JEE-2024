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
    <div class="logo">
        Logo
    </div>
    <header>
        <div class="categories">
            <ul>
                <li><a href="/FrontController?categorie=subject"> Matière </a></li>
                <li><a href="/FrontController?categorie=class"> Cours </a></li>
                <li><a href="/FrontController?categorie=user"> Enseignant | Student </a></li>
                <li><a href="/FrontController?categorie=grade"> Grade </a></li>
            </ul>
        </div>
        <div class="login">
            <a href="/FrontController?categorie=login">S'authentifier</a>
        </div>
    </header>
    <nav>Aside</nav>
    <%
        String categorie = request.getParameter("categorie");
        if (categorie != null) {
            response.getWriter().print("<main>" + categorie + "</main>");
        }
        else{
            response.getWriter().print("<main> Main </main>");
        }
    %>
    <footer>Footer</footer>
</body>
</html>
