package fr.cyu.jee.controller;

import fr.cyu.jee.HibernateUtil;
import fr.cyu.jee.model.Subject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

/**
 * Servlet that returns the list of available subjects.
 */
@Controller
public class SubjectController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/subject.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    /**
     *
     * @return The subjects in the database as a list.
     */
    public static List<Subject> getListSubjects() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Subject> result = session.createQuery("from Subject").getResultList();
        session.close();
        return result;
    }
}
