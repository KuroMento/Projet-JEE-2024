package fr.cyu.jee.controller;

import fr.cyu.jee.HibernateUtil;
import fr.cyu.jee.model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.tool.ant.QueryExporterTask;

/**
 * This servlet checks if the login information are correct.
 */
public class LoginController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String pw = req.getParameter("pw");

        if( id == null || pw == null ){
            req.setAttribute("error", "A parameter is missing");
            req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
        }
        else{
            User loggedUser = getUser(id,pw);
            if (loggedUser == null ){
                req.setAttribute("error", "The id or the password is incorrect");
                req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
            }

            if( loggedUser.getPermissions() == Permissions.STUDENT ){
                Student student = UserController.getStudent(loggedUser);
                req.getSession().setAttribute("loggedUser",student);
            }
            if( loggedUser.getPermissions() == Permissions.TEACHER ){
                Teacher teacher = UserController.getTeacher(loggedUser);
                req.getSession().setAttribute("loggedUser",teacher);
            }
            if( loggedUser.getPermissions() == Permissions.ADMIN ){
                req.getSession().setAttribute("loggedUser",loggedUser);
            }

            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
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
