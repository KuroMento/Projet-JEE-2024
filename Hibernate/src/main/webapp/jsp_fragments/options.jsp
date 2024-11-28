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
        if( categorie.equals("user")){
            mainDiv = mainDiv + "<div class='radio-group'><p><b> User Filters </b></p>"
                    + "<label><input type=\"radio\" name=\"Test\" value=\"test\" checked=\"checked\"> First Name </label>"
                    + "<label><input type=\"radio\" name=\"Test\" value=\"test\" > Last Name </label>"
                    + "<label><input type=\"radio\" name=\"Test\" value=\"test\" > Email / Contact </label></div>"
                    + "<div class=\"checkbox-group\">"
                    + "<label><input type=\"checkbox\" name=\"option\" value=\"test\">Student</label>"
                    + "<label><input type=\"checkbox\" name=\"option\" value=\"test\">Teacher</label>"
                    + "<label><input type=\"checkbox\" name=\"option\" value=\"test\">Admin</label>"
                    + "<button class=\"option_button\" type='submit'> Search User </button>"
            ;
        }
        if( categorie.equals("grade")){
            if( currentUser != null && currentUser.getPermissions() == Permissions.STUDENT) {
                mainDiv = mainDiv + "<div class='radio-group'><p><b> CRUD Operations </b></p>"
                        + "<button class=\"option_button\" type=\"submit\" name=\"option\" value=\"create\">Create</button>\n" +
                        "    <button class=\"option_button\" type=\"submit\" name=\"option\" value=\"update\">Update</button>\n" +
                        "    <button class=\"option_button\" type=\"submit\" name=\"option\" value=\"delete\">Delete</button></div>"
                ;
            }
            else if(currentUser != null && currentUser.getPermissions() == Permissions.TEACHER){
                mainDiv = mainDiv + "<div class='radio-group'><p><b> CRUD Operations </b></p>"
                        + "<button class=\"option_button\" type=\"submit\" name=\"option\" value=\"create\">Create</button>\n" +
                        "    <button class=\"option_button\" type=\"submit\" name=\"option\" value=\"update\">Update</button>\n" +
                        "    <button class=\"option_button\" type=\"submit\" name=\"option\" value=\"delete\">Delete</button></div>"
                ;
            }
        }
        if( categorie.equals("course")){
            mainDiv = mainDiv + "<div class='radio-group'><p><b> Course Options </b></p>"
                    + "<label><input type=\"radio\" name=\"Test\" value=\"test\" checked=\"checked\"> Test </label>"
                    + "<label><input type=\"radio\" name=\"Test\" value=\"test\" checked=\"checked\"> Test </label>"
                    + "<label><input type=\"radio\" name=\"Test\" value=\"test\" checked=\"checked\"> Test </label>"
            ;
        }
        if( categorie.equals("subject")){
            mainDiv = mainDiv + "<div class='radio-group'><p><b> Subject Options </b></p>"
                    + "<label><input type=\"radio\" name=\"Test\" value=\"test\" checked=\"checked\"> Test </label>"
                    + "<label><input type=\"radio\" name=\"Test\" value=\"test\" checked=\"checked\"> Test </label>"
                    + "<label><input type=\"radio\" name=\"Test\" value=\"test\" checked=\"checked\"> Test </label>"
            ;
        }
    }
    mainDiv = mainDiv + "</aside>";
    response.getWriter().print(mainDiv);
%>
