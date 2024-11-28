<%
  String mainDiv = "";
  String error = (String) request.getAttribute("error");

  mainDiv = mainDiv + "<main class=\"main\">\n" +
          "            <div class=\"login_form\">\n" +
          "                <form method=\"post\" action=\"/LoginController\">\n" +
          "                    <div class=\"form_input\">\n" +
          "                        <label for=\"id\">Username / Identification </label>\n" +
          "                        <input type=\"text\" id=\"id\" name=\"id\" placeholder=\"Enter your username (e.g. admin)\" required>\n" +
          "                    </div>\n" +
          "                    <div class=\"form_input\">\n" +
          "                        <label for=\"pw\">Password</label>\n" +
          "                        <input type=\"password\" id=\"pw\" name=\"pw\" placeholder=\"Enter your password (e.g. 1234)\" required>\n" +
          "                    </div>\n" +
          "                    <button type=\"submit\" class=\"form_button\">Send credentials</button>";
  if( error != null && !error.isEmpty() && !error.isBlank()){
    mainDiv = mainDiv + "<p style=\"color: red\"> Your Username or your password is incorrect ! </p>";
  }

  mainDiv = mainDiv + "</form>\n" +
          "                <div>\n" +
          "                    <h1>Connexion Form</h1>\n" +
          "                    <p>You can use multiples credentials to test the different functionalities :</p>\n" +
          "                    <ul>\n" +
          "                        <li>To connect as an admin, use : Admin | 1234</li>\n" +
          "                        <li>To connect as a student, use : Student | cytech0001</li>\n" +
          "                        <li>To connect as a teacher, use : Teacher | cytech0001</li>\n" +
          "                    </ul>\n" +
          "                </div>\n" +
          "\n" +
          "            </div>\n" +
          "        </main>";

  response.getWriter().print(mainDiv);
%>