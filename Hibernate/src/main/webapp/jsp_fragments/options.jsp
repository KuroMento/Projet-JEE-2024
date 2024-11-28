<%@ page import="fr.cyu.jee.model.User" %>
<%@ page import="fr.cyu.jee.model.Permissions" %>
<%
    String mainDiv = "<aside class=\"options\">";
    User currentUser = (User) session.getAttribute("loggedUser");
    String categorie = (String) request.getParameter("categorie");

    if(currentUser != null && currentUser.getPermissions() == Permissions.ADMIN){
        mainDiv = mainDiv + "<div class='radio-group'><p><b> CRUD Operations </b></p>"
                + "<button class=\"option_button\" type=\"submit\" name=\"option\" value=\"create\">Create</button>\n" +
                "    <button class=\"option_button\" type=\"submit\" name=\"option\" value=\"update\">Update</button>\n" +
                "    <button class=\"option_button\" type=\"submit\" name=\"option\" value=\"delete\">Delete</button></div>";
    }
    if(currentUser != null && categorie != null){
        if( categorie.equals("user") && currentUser.getPermissions() == Permissions.ADMIN){
            mainDiv = mainDiv + "<div class='radio-group'><p><b> User Filters </b></p>"
                    + "<label><input type=\"radio\" name=\"attributeFilter\" value=\"firstName\" checked=\"checked\"> First Name </label>"
                    + "<label><input type=\"radio\" name=\"attributeFilter\" value=\"lastName\" > Last Name </label>"
                    + "<label><input type=\"radio\" name=\"attributeFilter\" value=\"contact\" > Email / Contact </label></div>"
                    + "<div class=\"checkbox-group\">"
                    + "<label><input type=\"checkbox\" name=\"statusFilter\" value=\"STUDENT\">Student</label>"
                    + "<label><input type=\"checkbox\" name=\"statusFilter\" value=\"TEACHER\">Teacher</label>"
                    + "<label><input type=\"checkbox\" name=\"statusFilter\" value=\"ADMIN\">Admin</label>"
            ;
        }
        if( categorie.equals("grade")){
            if( currentUser != null && currentUser.getPermissions() == Permissions.STUDENT) {
                mainDiv = mainDiv + "<div class='radio-group'><p><b> Grade Options  </b></p>"
                        + "<button class=\"option_button\" type=\"submit\" name=\"option\" value=\"report\">Generate Report</button>\n"
                ;
            }
            else if(currentUser != null && currentUser.getPermissions() == Permissions.TEACHER){
                mainDiv = mainDiv + "<div class='radio-group'><p><b> Grade Options  </b></p>"
                        + "<button class=\"option_button\" type=\"submit\" name=\"option\" value=\"create\">Grade Students</button>\n"
                ;
            }
        }
        if( categorie.equals("course")){
            if( currentUser != null && currentUser.getPermissions() == Permissions.STUDENT) {
                mainDiv = mainDiv + "<div class='radio-group'><p><b> Course Options </b></p>"
                        + "<button class=\"option_button\" type=\"submit\" name=\"option\" value=\"register\">Register</button>\n"
                        + "<button class=\"option_button\" type=\"submit\" name=\"option\" value=\"mean\">Calculate Mean</button>\n";
            }
            else if(currentUser != null && currentUser.getPermissions() == Permissions.TEACHER){
                mainDiv = mainDiv + "<div class='radio-group'><p><b> Course Options  </b></p>"
                        + "<button class=\"option_button\" type=\"submit\" name=\"option\" value=\"gradeStudents\">Grade Students</button>\n"
                ;
            }
        }
        if( categorie.equals("subject")){
            mainDiv = mainDiv + "<div class='radio-group'><p><b> Subject Options </b></p>"
                    + "<button class=\"option_button\" type=\"submit\" name=\"option\" value=\"associatedCourses\">Associated Courses</button>\n"
            ;
        }
    }
    if( currentUser != null && categorie == null ){
        mainDiv = mainDiv + "<div class='radio-group'><p><b> Profile Options </b></p>"
                + "<button class=\"option_button\" type=\"submit\" name=\"option\" value=\"unregister\">Unregister Course</button>\n";
    }
    mainDiv = mainDiv + "</aside>";
    response.getWriter().print(mainDiv);
%>
