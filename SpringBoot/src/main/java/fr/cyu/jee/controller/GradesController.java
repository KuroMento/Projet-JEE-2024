package fr.cyu.jee.controller;

import fr.cyu.jee.model.Grade;
import fr.cyu.jee.model.User;
import fr.cyu.jee.repository.GradeRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.Optional;

/**
 * This servlet gives the grades to a user
 */
@Controller
public class GradesController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object loggedUser = req.getSession().getAttribute("loggedUser");
        User user = (User) loggedUser;
        Grade grade =getGrade(user.getIdentification());
        req.setAttribute("grade",grade.getValue());
        req.setAttribute("gradeCoefficient",grade.getCoefficient());
        req.getRequestDispatcher("WEB-INF/grade.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Autowired
    private GradeRepository gradeRepository;

    /**
     *
     * @param id user's id, linked by a foreign key to the grade's id
     * @return the grade of the user
     */
    public Grade getGrade(String id){
        Optional<Grade> gradeById = gradeRepository.findById(id);
        Grade grade = gradeById.get();
        return grade;
    }

}
