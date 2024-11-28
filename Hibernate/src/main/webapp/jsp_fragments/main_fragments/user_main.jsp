<%@ page import="fr.cyu.jee.model.User" %>
<%@ page import="fr.cyu.jee.model.Permissions" %>
<%@ page import="java.util.List" %>
<%
    String mainDiv = "<main class='main'>";
    User currentUser = (User) session.getAttribute("loggedUser");

    if( currentUser == null){
        mainDiv = mainDiv + "<p> You are not <b>connected</b>. Note: Only Admins have access to user data ! </p> ";
    }
    else if( currentUser != null && currentUser.getPermissions() == Permissions.ADMIN){
        mainDiv = mainDiv + "<input type=\"search\" name=\"search\" placeholder=\"Enter a firstname, lastname or an email...\"/>";
        List<User> users = (List<User>) request.getAttribute("users");
        if( users != null ){
            for(User u : users){
                mainDiv = mainDiv + "<label class=\"selectable-label\"> <input class=\"selectable-input\" type=\"radio\" name=\"user\" value=\"id\"> " +
                        "<div class=\"selectable-div\"> Username: " + u.getIdentification() + "</div> </label>";
            }
        }
    }
    else{
        mainDiv = mainDiv + "<p style=\"color:red;\"> You are not <b>allowed access to the user database</b> as a " + currentUser.getPermissions() + "</p>";
    }
    mainDiv = mainDiv + "</main>";
    response.getWriter().print(mainDiv);
%>