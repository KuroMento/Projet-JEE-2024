<%@ page import="fr.cyu.jee.model.User" %><%
  String loginDiv = "<div class=\"login\">";
  User currentUser = (User) session.getAttribute("loggedUser");
  if (currentUser == null){
    loginDiv = loginDiv + "<div class=\"login_status\"> No User Connected</div><a href=\"/FrontController?action=login\" class=\"login_button\"> Log in </a>";
  }
  else{
    loginDiv = loginDiv + "<div class=\"login_status\">" + currentUser.getIdentification() + "</div><a href=\"/FrontController?action=logout\" class=\"login_button\"> Log out </a>";
  }
  loginDiv = loginDiv + "</div>";
  response.getWriter().print(loginDiv);
%>
