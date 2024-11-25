package fr.cyu.jee.controller;

import fr.cyu.jee.HibernateUtil;
import fr.cyu.jee.model.Grade;
import fr.cyu.jee.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

/**
 * This servlet gives the grades to a user
 */
@Controller
public class GradesController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/grade.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    /**
     *
     * @return The users in the database as a list.
     */
    public static List<Grade> getListGrades() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Grade> result = session.createQuery("from Grade").list();
        session.close();
        return result;
    }

}
