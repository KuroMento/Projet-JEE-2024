<%@ page import="fr.cyu.jee.model.User" %>
<%@ page import="fr.cyu.jee.model.Course" %>
<%@ page import="java.util.List" %>
<%
    String mainDiv = "<main class='main'>";

    String error = (String) request.getAttribute("error");
    String option = request.getParameter("option");

    User currentUser = (User) session.getAttribute("loggedUser");
    List<Course> courses = (List<Course>) request.getAttribute("courses");
    Course selectedCourse = (Course) request.getAttribute("selectedCourse");

    // You are not connected
    if( currentUser == null){
        mainDiv = mainDiv + "<p> You are not <b>connected</b>. Courses are only visible for logged users !</p> ";
    }

    // An error occurred !
    else if( error != null && !error.isBlank() && !error.isEmpty() ){
        mainDiv = mainDiv + "<div style=\"color:red;\"> " + error + "</div>";
    }

    // You selected an option associated to an object
    else if( selectedCourse != null ){
        if( option.equals("create") ){
            mainDiv = mainDiv + "<input type=\"hidden\" name=\"action\" value=\"create\">"
                    + "<div class=\"form_input\">\n" +
                    "     <label for=\"subject\">Subject's Identification Number </label>\n" +
                    "     <input type=\"number\" id=\"subject\" name=\"subject\" value=\"1\"  min=\"1\" step=\"1\" required>\n" +
                    "  </div>\n"
            +       "<div class=\"form_input\">\n" +
                    "     <label for=\"teacher\">Teacher </label>\n" +
                    "     <input type=\"text\" id=\"teacher\" name=\"teacher\" placeholder=\"Teacher\" required>\n" +
                    "  </div>\n"
            +       "  <button type=\"submit\" class=\"form_button\"> Create Course </button>";
        }
        if( option.equals("update") ){
            mainDiv = mainDiv + "<input type=\"hidden\" name=\"action\" value=\"create\">" + "<input type=\"hidden\" name=\"course\" value=\"" + selectedCourse.getIdentification() + "\">"
                    + "<div class=\"form_input\">\n" +
                    "     <label for=\"subject_id\">Subject's Identification Number </label>\n" +
                    "     <input type=\"number\" id=\"subject_id\" name=\"subject\" value=\"1\"  min=\"1\" step=\"1\" required>\n" +
                    "  </div>\n"
                    +       "<div class=\"form_input\">\n" +
                    "     <label for=\"teacher\">Teacher </label>\n" +
                    "     <input type=\"number\" id=\"teacher\" name=\"teacher\" placeholder=\"" + selectedCourse.getTeacher().getIdentification() + "\" required>\n" +
                    "  </div>\n" +
                    "  <button type=\"submit\" class=\"form_button\"> Update Course </button>";
        }
        if( option.equals("delete") ){
            mainDiv = mainDiv + "<label class=\"selectable-label\"> <input class=\"selectable-input\" type=\"radio\" name=\"course\" value=\"" + selectedCourse.getIdentification() +"\">\n" +
                    "            <div class=\"selectable-div\"> Subject: " + selectedCourse.getSubject().getLabel() + "#" + selectedCourse.getIdentification() + " <br> " +
                    " Teacher: " + selectedCourse.getTeacher().getIdentification() +
                    "<br> Number of students:"+ selectedCourse.getStudents().size() + "</div>\n" +
                    "</label>";
        }
    }

    // Default printing of every course in the database
    else if( courses != null ){
        for(Course c : courses){
            mainDiv = mainDiv + "<label class=\"selectable-label\"> <input class=\"selectable-input\" type=\"radio\" name=\"course\" value=\"" + c.getIdentification() +"\">\n" +
                    "            <div class=\"selectable-div\"> Subject: " + c.getSubject().getLabel() + "#" + c.getIdentification() + " <br> " +
                    " Teacher: " + c.getTeacher().getIdentification() +
                    "<br> Number of students:"+ c.getStudents().size() + "</div>\n" +
                    "</label>";
        }
    }

    // No courses in the database
    else{
        mainDiv = mainDiv + "No courses available currently !";
    }

    // Printing the main div
    mainDiv = mainDiv + "</main>";
    response.getWriter().println(mainDiv);
%>
