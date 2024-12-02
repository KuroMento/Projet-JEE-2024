<%@ page import="fr.cyu.jee.model.User" %>
<%@ page import="fr.cyu.jee.model.Permissions" %>
<%
    String mainDiv = "<aside class=\"options\">";
    User currentUser = (User) session.getAttribute("loggedUser");
    String categorie = request.getParameter("categorie");

    if( currentUser != null ){
        Permissions currentPermissions = currentUser.getPermissions();

        // ADMIN OPTIONS
        if( currentPermissions == Permissions.ADMIN ){
            // CRUD Options
            mainDiv = mainDiv + "<div class='radio-group'><p><b> CRUD Operations </b></p>"
                    + "<button class=\"option_button\" type=\"submit\" name=\"option\" value=\"create\">Create</button>\n" +
                    "    <button class=\"option_button\" type=\"submit\" name=\"option\" value=\"update\">Update</button>\n" +
                    "    <button class=\"option_button\" type=\"submit\" name=\"option\" value=\"delete\">Delete</button></div>";
            // User Options
            if( categorie != null && categorie.equals("user")){
                mainDiv = mainDiv + "<div class='radio-group'><p><b> User Filters </b></p>"
                        + "<label><input type=\"radio\" name=\"attributeFilter\" value=\"firstName\" checked=\"checked\"> First Name </label>"
                        + "<label><input type=\"radio\" name=\"attributeFilter\" value=\"lastName\" > Last Name </label>"
                        + "<label><input type=\"radio\" name=\"attributeFilter\" value=\"contact\" > Email / Contact </label></div>"
                        + "<div class=\"checkbox-group\">"
                        + "<label><input type=\"checkbox\" name=\"statusFilter\" value=\"STUDENT\">Student</label>"
                        + "<label><input type=\"checkbox\" name=\"statusFilter\" value=\"TEACHER\">Teacher</label>"
                        + "<label><input type=\"checkbox\" name=\"statusFilter\" value=\"ADMIN\">Admin</label>"
                        + "<button class=\"option_button\" type=\"submit\" name=\"option\" value=\"profile\">See Profile</button></div>"
                ;
            }
            // Subject Options
            else if( categorie != null && categorie.equals("subject")){
                mainDiv = mainDiv + "<div class='radio-group'><p><b> Subject Options </b></p>"
                        + "<button class=\"option_button\" type=\"submit\" name=\"option\" value=\"associatedCourses\">Associated Courses</button>\n"
                ;
            }
            // Courses Options
            else if( categorie != null && categorie.equals("course")){

            }
            // Grades Options
            else if( categorie != null && categorie.equals("grade")){

            }
            // Profile Options
            else {
                String action = request.getParameter("action");
                if(action == null){

                }
            }
        }

        // STUDENT OPTIONS
        else if( currentPermissions == Permissions.STUDENT ){

            // User Options
            if( categorie != null && categorie.equals("user")){
                // Nothing
            }
            // Subject Options
            else if( categorie != null && categorie.equals("subject")){
                mainDiv = mainDiv + "<div class='radio-group'><p><b> Subject Options </b></p>"
                        + "<button class=\"option_button\" type=\"submit\" name=\"option\" value=\"associatedCourses\">Associated Courses</button>\n"
                ;
            }
            // Courses Options
            else if( categorie != null && categorie.equals("course")){
                mainDiv = mainDiv + "<div class='radio-group'><p><b> Course Options  </b></p>"
                        + "<button class=\"option_button\" type=\"submit\" name=\"option\" value=\"register\">Register Course</button>\n"
                ;
            }
            // Grades Options
            else if( categorie != null && categorie.equals("grade")){
                mainDiv = mainDiv + "<div class='radio-group'><p><b> Grade Options  </b></p>"
                        + "<button class=\"option_button\" type=\"submit\" name=\"option\" value=\"report\">Generate Report</button>\n"
                ;
            }
            // Profile Options
            else {
                String action = request.getParameter("action");
                if(action == null){

                }
            }
        }

        // TEACHER OPTIONS
        else if( currentPermissions == Permissions.TEACHER ){
            // User Options
            if( categorie != null && categorie.equals("user")){
                // Nothing
            }
            // Subject Options
            else if( categorie != null && categorie.equals("subject")){
                mainDiv = mainDiv + "<div class='radio-group'><p><b> Subject Options </b></p>"
                        + "<button class=\"option_button\" type=\"submit\" name=\"option\" value=\"associatedCourses\">Associated Courses</button>\n"
                ;
            }
            // Courses Options
            else if( categorie != null && categorie.equals("course")){

            }
            // Grades Options
            else if( categorie != null && categorie.equals("grade")){

            }
            // Profile Options
            else {
                String action = request.getParameter("action");
                if(action == null){
                    mainDiv = mainDiv + "<div class='radio-group'><p><b> Course Options  </b></p>"
                            + "<button class=\"option_button\" type=\"submit\" name=\"option\" value=\"grading\">Grade Students</button>\n"
                    ;
                }
            }
        }
    }

    // Closing and printing the main div (options)
    mainDiv = mainDiv + "</aside>";
    response.getWriter().print(mainDiv);
%>