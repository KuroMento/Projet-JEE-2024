<%@ page import="fr.cyu.jee.model.Subject" %>
<%@ page import="java.util.List" %>
    <%
        List<Subject> subjects = (List<Subject>) request.getAttribute("subjects");
        Subject selectedSubject = (Subject) request.getAttribute("selectedSubject");
        String mainDiv = "<main class=\"main\">";

        if( selectedSubject != null ){
            String option = request.getParameter("option");
            if( option.equals("create") ){
                mainDiv = mainDiv + "<input type=\"hidden\" name=\"action\" value=\"create\">" +
                        "                    <div class=\"form_input\">\n" +
                        "                        <label for=\"label\">Subject Label </label>\n" +
                        "                        <input type=\"text\" id=\"label\" name=\"label\" placeholder=\"Software Engineering\" required>\n" +
                        "                    </div>\n" +
                        "                    <div class=\"form_input\">\n" +
                        "                        <label for=\"description\">Description</label>\n" +
                        "                        <input type=\"text\" id=\"description\" name=\"description\" placeholder=\"About programming and stuff\" required>\n" +
                        "                    </div>\n" +
                        "                    <div class=\"form_input\">\n" +
                        "                        <label for=\"coefficient\">Coefficient</label>\n" +
                        "                        <input type=\"text\" id=\"coefficient\" name=\"coefficient\" placeholder=\"2\" required>\n" +
                        "                    </div>\n" +
                        "                    <button type=\"submit\" class=\"form_button\">Create Subject</button>";
            }
            if( option.equals("update") ){
                mainDiv = mainDiv + "<input type=\"hidden\" name=\"action\" value=\"update\">" + "<input type=\"hidden\" name=\"subject\" value=\"" + selectedSubject.getIdentification() + "\">" +
                        "                    <div class=\"form_input\">\n" +
                        "                        <label for=\"label\">Subject Label </label>\n" +
                        "                        <input type=\"text\" id=\"label\" name=\"label\" value=\" " + selectedSubject.getLabel() + "\" required>\n" +
                        "                    </div>\n" +
                        "                    <div class=\"form_input\">\n" +
                        "                        <label for=\"description\">Description</label>\n" +
                        "                        <input type=\"text\" id=\"description\" name=\"description\" value=\"" + selectedSubject.getDescription() + "\" required>\n" +
                        "                    </div>\n" +
                        "                    <div class=\"form_input\">\n" +
                        "                        <label for=\"coefficient\">Coefficient</label>\n" +
                        "                        <input type=\"text\" id=\"coefficient\" name=\"coefficient\" value=\"" + selectedSubject.getCoefficient() + "\" required>\n" +
                        "                    </div>\n" +
                        "                    <button type=\"submit\" class=\"form_button\">Update Subject</button>";
            }
            if( option.equals("delete") ){
                mainDiv = mainDiv +  "<input type=\"hidden\" name=\"action\" value=\"delete\">" + "<input type=\"hidden\" name=\"subject\" value=\"" + selectedSubject.getIdentification() + "\">" +
                        "<input type=\"hidden\" name=\"description\" value=\"" + selectedSubject.getDescription() + "\">" + "<input type=\"hidden\" name=\"coefficient\" value=\"" + selectedSubject.getCoefficient() + "\">" +
                        "<label class=\"selectable-label\"> <input class=\"selectable-input\" type=\"radio\" name=\"subject\" value=\"" + selectedSubject.getIdentification() +"\">\n" +
                        "            <div class=\"selectable-div\">Label: " + selectedSubject.getLabel() + " <br> Description: " + selectedSubject.getDescription() + " <br> " +
                        "Coefficient: " + selectedSubject.getCoefficient() + "</div>\n" +
                        "</label>" + "<button type=\"submit\" class=\"form_button\">Delete Subject</button>";
            }
        }
        else if ( subjects != null){
            for(Subject s : subjects){
                mainDiv = mainDiv + "<label class=\"selectable-label\"> <input class=\"selectable-input\" type=\"radio\" name=\"subject\" value=\"" + s.getIdentification() +"\">\n" +
                        "            <div class=\"selectable-div\">Label: " + s.getLabel() + " <br> Description: " + s.getDescription() + " <br> " +
                        "Coefficient: " + s.getCoefficient() + "</div>\n" +
                        "</label>";
            }
        }
        else{
            mainDiv = mainDiv + "No subjects currently !";
        }
        mainDiv = mainDiv + "</main>";
        response.getWriter().println(mainDiv);
    %>