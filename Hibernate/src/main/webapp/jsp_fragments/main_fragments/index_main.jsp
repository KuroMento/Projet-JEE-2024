<%@ page import="fr.cyu.jee.model.*" %>
<%@ page import="java.util.List" %><%
  String mainDiv = "<main class=\"main\">";
  User currentUser = (User) session.getAttribute("loggedUser");
  if( currentUser == null ){
    mainDiv = mainDiv + "<h1> Welcome to CY Board </h1><p> This site enables you to see the subjects and courses available if not logged in."
            + "If you are logged as a Student, you can access your grades, course's mean adn generate your complete result for your semester. <br> As a Teacher,"
            + " you can grade students. <br><br>And as an Admin, you can do many things such as searching through the user database as well as creating, updating or deleting anything in the database</p>";
  }
  else{
    mainDiv = mainDiv + "<h1> Welcome, " + currentUser.getIdentification() + "</h1>"
                + "<p> Status : " + currentUser.getPermissions() + "<br>"
                + " First Name : " + currentUser.getFirstName() + "<br>"
                + " Last Name : " + currentUser.getLastName() + "<br>"
                + " Contact / Email : " + currentUser.getContact() + "<br>"
                + " Date of birth : " + currentUser.getDateOfBirth() + "<br></p>";
    if( currentUser.getPermissions() == Permissions.STUDENT){
        List<Course> courses = ((Student)currentUser).getCourses();
        if( courses == null || courses.isEmpty()){
          mainDiv = mainDiv + "<br><p>You currently have no courses registered to your profile</p>";
        }
        else {
            mainDiv = mainDiv + "<div>Registered courses :</div><br>";
            for (Course c : courses) {
              mainDiv = mainDiv + "<label class=\"selectable-label\"> <input class=\"selectable-input\" type=\"radio\" name=\"course\" value=\"" + c.getIdentification() +"\">\n" +
                      "            <div class=\"selectable-div\"> Subject : " + c.getSubject().getLabel() + " <br> Teacher : " + c.getTeacher().getIdentification() + " <br> " +
                      "Coefficient: " + c.getSubject().getCoefficient() + "</div>\n" +
                      "</label>";
            }
        }
    }
      if( currentUser.getPermissions() == Permissions.TEACHER){
          List<Course> courses = ((Teacher)currentUser).getCourses();
          if( courses == null || courses.isEmpty()){
              mainDiv = mainDiv + "<br><p>You currently have no courses registered to your profile</p>";
          }
          else {
              mainDiv = mainDiv + "<div>Registered courses :</div><br>";
              for (Course c : courses) {
                  mainDiv = mainDiv + "<label class=\"selectable-label\"> <input class=\"selectable-input\" type=\"radio\" name=\"course\" value=\"" + c.getIdentification() +"\">\n" +
                          "            <div class=\"selectable-div\"> Subject : " + c.getSubject().getLabel() + " <br> Teacher : " + c.getTeacher().getIdentification() + " <br> " +
                          "Coefficient: " + c.getSubject().getCoefficient() + "</div>\n" +
                          "</label>";
              }
          }
      }
  }
  mainDiv = mainDiv + "</main>";
  response.getWriter().print(mainDiv);
%>
