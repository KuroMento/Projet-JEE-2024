<%@ page import="fr.cyu.jee.model.User" %>
<%@ page import="fr.cyu.jee.model.Permissions" %><%
  String mainDiv = "<main class=\"main\">";
  User currentUser = (User) session.getAttribute("loggedUser");
  if( currentUser == null ){
    mainDiv = mainDiv + "<h1> Welcome to CY Board </h1><p> This site enables you to see the subjects and courses available if not logged in."
            + "If you are logged as a Student, you can access your grades, course's mean adn generate your complete result for your semester. <br> As a Teacher,"
            + " you can grade students. <br><br>And as an Admin, you can do many things such as searching through the user database as well as creating, updating or deleting anything in the database</p>";
  }
  else{
    if( currentUser.getPermissions() == Permissions.STUDENT){
      // print User profile
    }
    if( currentUser.getPermissions() == Permissions.TEACHER){
      // print User profile
    }
  }
  mainDiv = mainDiv + "</main>";
  response.getWriter().print(mainDiv);
%>
