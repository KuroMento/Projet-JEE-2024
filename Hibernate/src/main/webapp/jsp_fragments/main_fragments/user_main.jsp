<%@ page import="fr.cyu.jee.model.User" %>
<%@ page import="fr.cyu.jee.model.Permissions" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
    String mainDiv = "<main class='main'>";

    String error = (String) request.getAttribute("error");
    String option = request.getParameter("option");

    User currentUser = (User) session.getAttribute("loggedUser");

    // You are not connected
    if( currentUser == null){
        mainDiv = mainDiv + "<p> You are not <b>connected</b>. Note: Only Admins have access to user data ! </p> ";
    }

    // An error occurred !
    else if(error != null && !error.isBlank() && !error.isEmpty() ){
        mainDiv = mainDiv + "<div style=\"color:red;\"> " + error + "</div>";
    }

    // You are an Admin
    else if( currentUser != null && currentUser.getPermissions() == Permissions.ADMIN){
        User selectedUser = (User) request.getAttribute("selectedUser");
        List<User> users = (List<User>) request.getAttribute("users");

        // You selected an option for an object !
        if( selectedUser != null ){
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
            if( option.equals("create")){
                mainDiv = mainDiv +  "<input type=\"hidden\" name=\"action\" value=\"create\">"
                        + "<input type=\"hidden\" name=\"id\" value=\"" + selectedUser.getIdentification() + "\">"
                        + "<div class=\"form_input\">\n" +
                        "     <label for=\"id\">User Identification | Username </label>\n" +
                        "     <input type=\"text\" id=\"id\" name=\"id\" placeholder=\"Ketk\" required>\n" +
                        "  </div>\n"
                        + "<div class=\"form_input\">\n" +
                        "     <label for=\"pw\">User Password </label>\n" +
                        "     <input type=\"text\" id=\"pw\" name=\"pw\" placeholder=\"qwerty123\" required>\n" +
                        "  </div>\n"
                        + "<div class=\"form_input\">\n" +
                        "     <label for=\"firstName\">First Name </label>\n" +
                        "     <input type=\"text\" id=\"firstName\" name=\"firstName\" placeholder=\"Matthias\" required>\n" +
                        "  </div>\n"
                        + "<div class=\"form_input\">\n" +
                        "     <label for=\"lastName\">Last Name </label>\n" +
                        "     <input type=\"text\" id=\"lastName\" name=\"lastName\" placeholder=\"Franchini\" required>\n" +
                        "  </div>\n"
                        + "<div class=\"form_input\">\n" +
                        "     <label for=\"contact\">Contact | Email </label>\n" +
                        "     <input type=\"text\" id=\"contact\" name=\"contact\" placeholder=\"franchim@cy-tech.fr\" required>\n" +
                        "  </div>\n"
                        + "<div class=\"form_input\">\n" +
                        "     <label for=\"dateOfBirth\">Date of birth </label>\n" +
                        "     <input type=\"date\" id=\"dateOfBirth\" name=\"dateOfBirth\" required>\n" +
                        "  </div>\n"
                        + "<div class=\"form_input\">\n" +
                        "     <label for=\"id\">Status | Permissions </label>\n" +
                            "<select name=\"permissions\" id=\"permissions\" required>\n" +
                        "<option value=\"\" disabled selected>-- Select a status --</option>" +
                        "    <option value=\"ADMIN\">ADMIN</option>\n" +
                        "    <option value=\"TEACHER\">TEACHER</option>\n" +
                        "    <option value=\"STUDENT\">STUDENT</option>\n" +
                        "    </select>"+
                        "  </div>\n"
                        + "<button type=\"submit\" class=\"form_button\">Create User</button>";
            }
            if( option.equals("update")){
                mainDiv = mainDiv +  "<input type=\"hidden\" name=\"action\" value=\"update\">"
                        + "<input type=\"hidden\" name=\"id\" value=\"" + selectedUser.getIdentification() + "\">"
                        + "<div class=\"form_input\">\n" +
                        "     <label for=\"id\">User Identification | Username </label>\n" +
                        "     <input type=\"text\" id=\"id\" name=\"id\" value=\"" + selectedUser.getIdentification() + "\" required>\n" +
                        "  </div>\n"
                        + "<div class=\"form_input\">\n" +
                        "     <label for=\"pw\">User Password </label>\n" +
                        "     <input type=\"text\" id=\"pw\" name=\"pw\" value=\"" + selectedUser.getCryptedPassword() + "\" required>\n" +
                        "  </div>\n"
                        + "<div class=\"form_input\">\n" +
                        "     <label for=\"firstName\">First Name </label>\n" +
                        "     <input type=\"text\" id=\"firstName\" name=\"firstName\" value=\"" + selectedUser.getFirstName() + "\" required>\n" +
                        "  </div>\n"
                        + "<div class=\"form_input\">\n" +
                        "     <label for=\"lastName\">Last Name </label>\n" +
                        "     <input type=\"text\" id=\"lastName\" name=\"lastName\" value=\"" + selectedUser.getLastName() + "\" required>\n" +
                        "  </div>\n"
                        + "<div class=\"form_input\">\n" +
                        "     <label for=\"contact\">Contact | Email </label>\n" +
                        "     <input type=\"text\" id=\"contact\" name=\"contact\" value=\"" + selectedUser.getContact() + "\" required>\n" +
                        "  </div>\n"
                        + "<div class=\"form_input\">\n" +
                        "     <label for=\"dateOfBirth\">Date of birth </label>\n" +
                        "     <input type=\"date\" id=\"dateOfBirth\" name=\"dateOfBirth\" required>\n" +
                        "  </div>\n"
                        + "<div class=\"form_input\">\n" +
                        "     <label for=\"id\">Status | Permissions </label>\n" +
                        "<select name=\"permissions\" id=\"permissions\" required>\n" +
                        "<option value=\"\" disabled selected>-- Select a status --</option>" +
                        "    <option value=\"ADMIN\">ADMIN</option>\n" +
                        "    <option value=\"TEACHER\">TEACHER</option>\n" +
                        "    <option value=\"STUDENT\">STUDENT</option>\n" +
                        "    </select>"+
                        "  </div>\n"
                        + "<button type=\"submit\" class=\"form_button\">Update User</button>";
            }
            if( option.equals("delete")){
                mainDiv = mainDiv + "<label class=\"selectable-label\"> <input class=\"selectable-input\" type=\"radio\" name=\"id\" value=\"" + selectedUser.getIdentification() + "\"> " +
                        "<div class=\"selectable-div\"> Username: " + selectedUser.getIdentification() + " | Full Name : " + selectedUser.getFirstName() + " " + selectedUser.getLastName() + " | Mail " + selectedUser.getContact() + " | Status : " + selectedUser.getPermissions() + "</div> </label>"
                    +  "<input type=\"hidden\" name=\"action\" value=\"delete\">" + "<input type=\"hidden\" name=\"user\" value=\"" + selectedUser.getIdentification() + "\">"
                + "<button type=\"submit\" class=\"form_button\">Delete User</button>";
            }
            if( option.equals("profile") ){
                mainDiv = mainDiv
                        + "<p> Status : " + selectedUser.getPermissions() + "<br>"
                        + " First Name : " + selectedUser.getFirstName() + "<br>"
                        + " Last Name : " + selectedUser.getLastName() + "<br>"
                        + " Contact / Email : " + selectedUser.getContact() + "<br>"
                        + " Date of birth : " + sdf.format(selectedUser.getDateOfBirth()) + "<br></p>";
            }
        }

        // Basic printing of every user ! Also used for search option
        if( users != null && selectedUser == null){
            mainDiv = mainDiv + "<div><input type=\"search\" name=\"search\" placeholder=\"Enter a firstname, lastname or an email...\"/><button class=\"option_button\" type='submit'> Search User </button></div>";
            for(User u : users){
                mainDiv = mainDiv +
                        "<label class=\"selectable-label\"> <input class=\"selectable-input\" type=\"radio\" name=\"id\" value=\"" + u.getIdentification() + "\"> " +
                        "<div class=\"selectable-div\"> Username: " + u.getIdentification() + " | Full Name : " + u.getFirstName() + " " + u.getLastName() + " | Mail " + u.getContact() + " | Status : " + u.getPermissions() + "</div> </label>";
            }
        }
    }

    // You are a Student or a Teacher
    else{
        mainDiv = mainDiv + "<p style=\"color:red;\"> You are not <b>allowed access to the user database</b> as a " + currentUser.getPermissions() + "</p>";
    }

    // Closing the main and printing it
    mainDiv = mainDiv + "</main>";
    response.getWriter().print(mainDiv);
%>