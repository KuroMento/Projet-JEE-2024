<%@ page import="fr.cyu.jee.model.User" %>
<%@ page import="fr.cyu.jee.model.Course" %>
<%@ page import="java.util.List" %>
<%
    String mainDiv = "<main class='main'>";
    User currentUser = (User) session.getAttribute("loggedUser");
    List<Course> courses = (List<Course>) request.getAttribute("courses");

    if( courses == null ){
        mainDiv = mainDiv + "No subjects currently !";
    }
    else{
        for(Course c : courses){
            mainDiv = mainDiv + "<label class=\"selectable-label\"> <input class=\"selectable-input\" type=\"radio\" name=\"subject\" value=\"" + c.getIdentification() +"\">\n" +
                    "            <div class=\"selectable-div\">Teacher: " + c.getTeacher().getIdentification() + " <br> Subject: " + c.getSubject().getLabel() + " <br> " +
                     "Number of students:"+ c.getStudents().size() + "</div>\n" +
                    "</label>";
        }
    }
    mainDiv = mainDiv + "</main>";
    response.getWriter().println(mainDiv);
%>
