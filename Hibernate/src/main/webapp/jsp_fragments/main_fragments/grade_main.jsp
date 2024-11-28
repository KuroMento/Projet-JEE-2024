<%@ page import="fr.cyu.jee.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="fr.cyu.jee.model.Grade" %>
<%
    String mainDiv = "<main class='main'>";
    User currentUser = (User) session.getAttribute("loggedUser");

    List<Grade> grades = (List<Grade>) request.getAttribute("grades");
    if( grades != null ){
        for(Grade g : grades){
            mainDiv = mainDiv + String.valueOf(g.getValue()) + "<br>";
        }
    }
    else{
        mainDiv = mainDiv + "Aucune notes!";
    }
    mainDiv = mainDiv + "</main>";
    response.getWriter().print(mainDiv);
%>
