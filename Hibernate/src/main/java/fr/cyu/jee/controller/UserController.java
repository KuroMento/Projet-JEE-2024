package fr.cyu.jee.controller;

import fr.cyu.jee.HibernateUtil;
import fr.cyu.jee.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.hibernate.Session;

import java.io.IOException;
import java.util.List;

/**
 * Servlet that returns the list of users.
 */
public class UserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/user.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    /**
     *
     * @return The users in the database as a list.
     */
    public static List<User> getListUsers() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<User> result = session.createQuery("from User").list();
        session.close();
        return result;
    }

}
