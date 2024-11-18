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
            <a href="/FrontController?action=home">S'authentifier</a>
        </div>
    </header>
    <nav>Aside</nav>
    <main>
        <h1>Page de connexion</h1>
        <form method="post" action="/FrontController">
            <label name="id"> Identifiant :</label>
            <input type="text" required name="id">
            <br>
            <label name="password"> Mot de passe :</label>
            <input type="password" required name="pw">
            <br>
            <input type="submit" value="Cliquez">
        </form>
    </main>
    <footer>Footer</footer>
</body>
</html>
