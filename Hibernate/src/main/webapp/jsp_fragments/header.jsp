<%@ page import="fr.cyu.jee.model.User" %>
<header class="header">
    <div class="categories">
        <ul>
            <a href="/FrontController?categorie=subject"><li> Subjects </li></a>
            <a href="/FrontController?categorie=course"><li> Courses </li></a>
            <a href="/FrontController?categorie=grade"><li> Grades </li></a>
            <a href="/FrontController?categorie=user"><li> Users </li></a>
        </ul>
    </div>
    <jsp:include page="login_fragment.jsp"/>
</header>
