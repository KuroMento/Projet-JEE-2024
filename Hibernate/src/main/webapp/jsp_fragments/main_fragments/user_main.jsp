<%@ page import="fr.cyu.jee.model.User" %>
<%@ page import="fr.cyu.jee.model.Permissions" %>
<%@ page import="java.util.List" %>
<%
    String mainDiv = "<main class='main'>";
    User currentUser = (User) session.getAttribute("loggedUser");

    if( currentUser == null){
        mainDiv = mainDiv + "<p> You are not <b>connected</b>. Note: Only Admins have access to user data ! </p> ";
    }
    else if( currentUser != null && currentUser.getPermissions() == Permissions.ADMIN){
        User selectedUser = (User) request.getAttribute("selectedUser");
        List<User> users = (List<User>) request.getAttribute("users");
        if( selectedUser != null ){
            String option = request.getParameter("option");
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
                        "<div class=\"selectable-div\"> Username: " + selectedUser.getIdentification() + "</div> </label>"
                    +  "<input type=\"hidden\" name=\"action\" value=\"delete\">" + "<input type=\"hidden\" name=\"user\" value=\"" + selectedUser.getIdentification() + "\">"
                + "<button type=\"submit\" class=\"form_button\">Delete User</button>";
            }
        }
        if( users != null && selectedUser == null){
            mainDiv = mainDiv + "<div><input type=\"search\" name=\"search\" placeholder=\"Enter a firstname, lastname or an email...\"/><button class=\"option_button\" type='submit'> Search User </button></div>";
            for(User u : users){
                mainDiv = mainDiv +
                        "<label class=\"selectable-label\"> <input class=\"selectable-input\" type=\"radio\" name=\"id\" value=\"" + u.getIdentification() + "\"> " +
                        "<div class=\"selectable-div\"> Username: " + u.getIdentification() + "</div> </label>";
            }
        }
    }
    else{
        mainDiv = mainDiv + "<p style=\"color:red;\"> You are not <b>allowed access to the user database</b> as a " + currentUser.getPermissions() + "</p>";
    }
    mainDiv = mainDiv + "</main>";
    response.getWriter().print(mainDiv);
%>