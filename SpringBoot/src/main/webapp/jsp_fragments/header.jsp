<%@ page import="fr.cyu.jee.model.User" %>
<header class="header">
    <div class="categories">
        <ul>
            <a href="/subject"><li> Subjects </li></a>
            <a href="/course"><li> Courses </li></a>
            <a href="/grade"><li> Grades </li></a>
            <a href="/user"><li> Users </li></a>
        </ul>
    </div>
    <jsp:include page="login_fragment.jsp"/>
</header>
