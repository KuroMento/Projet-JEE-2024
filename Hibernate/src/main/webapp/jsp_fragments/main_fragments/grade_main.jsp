<%@ page import="fr.cyu.jee.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="fr.cyu.jee.model.Grade" %>
<%@ page import="fr.cyu.jee.model.Course" %>
<%@ page import="fr.cyu.jee.model.Student" %>
<%
    String mainDiv = "<main class='main'>";

    String error = (String) request.getAttribute("error");
    String option = request.getParameter("option");

    User currentUser = (User) session.getAttribute("loggedUser");
    List<Grade> grades = (List<Grade>) request.getAttribute("grades");
    Grade selectedGrade = (Grade) request.getAttribute("selectedGrade");


    // You are not connected
    if( currentUser == null){
        mainDiv = mainDiv + "<p> You are not <b>connected</b>. You can see your grades as a Student and every grades as an Administrator </p> ";
    }

    // An error occurred !
    else if(error != null && !error.isBlank() && !error.isEmpty() ){
        mainDiv = mainDiv + "<div style=\"color:red;\"> " + error + "</div>";
    }

    // You selected an option associated to an object
    else if( selectedGrade != null ){
        System.err.println(option);
        if( option.equals("create")){
            mainDiv = mainDiv + "<input type=\"hidden\" name=\"action\" value=\"create\">" +
                    "<div class=\"form_input\">\n" +
                    "     <label for=\"label\">Grade Label </label>\n" +
                    "     <input type=\"text\" id=\"label\" name=\"label\" placeholder=\"First Grade\" required>\n" +
                    "  </div>\n"
                    + "<div class=\"form_input\">\n" +
                    "     <label for=\"value\"> Value </label>\n" +
                    "     <input type=\"text\" id=\"value\" name=\"value\" placeholder=\"10\" required>\n" +
                    "  </div>\n"
                    + "<div class=\"form_input\">\n" +
                    "     <label for=\"coefficient\"> Coefficient </label>\n" +
                    "     <input type=\"text\" id=\"coefficient\" name=\"coefficient\" placeholder=\"1.0\" required>\n" +
                    "  </div>\n"
                    + "<div class=\"form_input\">\n" +
                    "     <label for=\"student\"> Student Username </label>\n" +
                    "     <input type=\"text\" id=\"student\" name=\"student\" placeholder=\"Student\" required>\n" +
                    "  </div>\n"
                    + "<div class=\"form_input\">\n" +
                    "     <label for=\"student\"> Course Identification </label>\n" +
                    "     <input type=\"number\" id=\"student\" name=\"student\" value=\"1\" min=\"1\" step=\"1\" required>\n" +
                    "  </div>\n"
                    +       "  <button type=\"submit\" class=\"form_button\"> Create Grade </button>";
        }
        if( option.equals("update")){
            mainDiv = mainDiv + "<input type=\"hidden\" name=\"action\" value=\"update\">" + "<input type=\"hidden\" name=\"grade\" value=\"" + selectedGrade.getIdentification() + "\">" +
                    "<div class=\"form_input\">\n" +
                    "     <label for=\"label\">Grade Label </label>\n" +
                    "     <input type=\"text\" id=\"label\" name=\"label\" placeholder=\"" + selectedGrade.getLabel() + "\" required>\n" +
                    "  </div>\n"
                    + "<div class=\"form_input\">\n" +
                    "     <label for=\"value\"> Value </label>\n" +
                    "     <input type=\"text\" id=\"value\" name=\"value\" placeholder=\""+ selectedGrade.getValue() + "\" required>\n" +
                    "  </div>\n"
                    + "<div class=\"form_input\">\n" +
                    "     <label for=\"coefficient\"> Coefficient </label>\n" +
                    "     <input type=\"text\" id=\"coefficient\" name=\"coefficient\" placeholder=\""+ selectedGrade.getCoefficient() + "\" required>\n" +
                    "  </div>\n"
                    + "<div class=\"form_input\">\n" +
                    "     <label for=\"student\"> Student Username </label>\n" +
                    "     <input type=\"text\" id=\"student\" name=\"student\" placeholder=\"" + selectedGrade.getStudent().getIdentification() +  "\" required>\n" +
                    "  </div>\n"
                    + "<div class=\"form_input\">\n" +
                    "     <label for=\"student\"> Course Identification </label>\n" +
                    "     <input type=\"number\" id=\"student\" name=\"student\" value=\"" + selectedGrade.getCourse().getIdentification() +  "\" min=\"1\" step=\"1\" required>\n" +
                    "  </div>\n"
            +       "  <button type=\"submit\" class=\"form_button\"> Update Grade </button>";
        }
        if( option.equals("delete")){
            mainDiv = mainDiv + "<input type=\"hidden\" name=\"action\" value=\"delete\">" +
                    "<label class=\"selectable-label\">\n" +
                    "    <input class=\"selectable-input\" type=\"radio\" name=\"grade\" value=\"\"/>\n" +
                    "    <div class=\"selectable-div\">Subject : " + selectedGrade.getCourse().getSubject().getLabel() + " | Teacher : " + selectedGrade.getCourse().getTeacher().getIdentification()
                    + " | Grade : " + selectedGrade.getValue() + " | Coefficient : " + selectedGrade.getCoefficient() + "</div>\n" +
                    "</label>"
                    + "  <button type=\"submit\" class=\"form_button\"> Delete Grade </button>";
        }
        if( option.equals("report") ){
            mainDiv = mainDiv + "<div><b> Grade Report </b></div> <ul>";
            List<Course> courses = (List<Course>) request.getAttribute("courses");
            double total = 0;
            double totalCoefficient = 0;
            for( Course c : courses ){
                System.err.println("Grade for different courses : " + c.getGrades() );
                mainDiv = mainDiv + "<li> Mean for " + c.getSubject().getLabel() + "#" + c.getIdentification() + ": " + c.getMean() + "/20</li>";
                total += c.getMean() * c.getSubject().getCoefficient();
                totalCoefficient += c.getSubject().getCoefficient();
            }
            mainDiv = mainDiv + "</ul> <div> Total for your courses: " + total / totalCoefficient + "/20";
        }
    }

    // Default Printing of every grade depending on status (Admin, Student, Teacher)
    else if( grades != null ){
        for(Grade g : grades){
            mainDiv = mainDiv + "<label class=\"selectable-label\">" +
                    "    <input class=\"selectable-input\" type=\"radio\" name=\"grade\" value=\"" + g.getIdentification() +"\"/>\n" +
                    "    <div class=\"selectable-div\">Course : " + g.getCourse().getSubject().getLabel() + " | Teacher : " + g.getCourse().getTeacher().getIdentification()
                    + " | Grade : " + g.getValue() + "| Coefficient : "+ g.getCoefficient() + "</div>\n" +
                    "</label>";
        }
    }

    // No Grades in the database
    else{
        mainDiv = mainDiv + "You have no notes!";
    }

    // Closing the main and printing it
    mainDiv = mainDiv + "</main>";
    response.getWriter().print(mainDiv);
%>