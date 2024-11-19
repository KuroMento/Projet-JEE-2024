package fr.cyu.jee.controller;

import fr.cyu.jee.HibernateUtil;
import fr.cyu.jee.model.Subject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.hibernate.Session;

import java.io.IOException;
import java.util.List;

/**
 * Servlet that returns the list of available subjects.
 */
public class SubjectController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Subject> listSubjects = getListSubjects();
        req.setAttribute("ListSubjects",listSubjects);
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
        List<Subject> result = session.createQuery("from Subjects").list();
        session.close();
        return result;
    }
}
