package fr.cyu.jee.controller;

import fr.cyu.jee.HibernateUtil;
import fr.cyu.jee.model.Permissions;
import fr.cyu.jee.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.tool.ant.QueryExporterTask;
import org.springframework.stereotype.Controller;

/**
 * This servlet checks if the login information are correct.
 */
@Controller
public class LoginController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String pw = req.getParameter("pw");

        if( id == null || pw == null ){
            req.setAttribute("error", "A parameter is missing");
        }
        else{
            User loggedUser = getUser(id,pw);
            if (loggedUser == null ){
                req.setAttribute("error", "The id or the password is incorrect");
            }
            req.getSession().setAttribute("user", loggedUser);
        }
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
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
        // Session session = HibernateUtil.getSessionFactory().openSession();
        // session.beginTransaction();
        // List<User> result = session.createQuery("from User").list();
        // session.close();
        // return result;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM User";
            Query<User> query = session.createQuery(hql, User.class);
            return query.getResultList();
        }
    }

    public static User getUser(String id, String pw){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM User WHERE id = :id AND encryptedPassword = :pw";
            Query<User> userQuery = session.createQuery(hql)
                    .setParameter("id",id)
                    .setParameter("pw",pw);
            return userQuery.uniqueResult();
        }
    }

}
