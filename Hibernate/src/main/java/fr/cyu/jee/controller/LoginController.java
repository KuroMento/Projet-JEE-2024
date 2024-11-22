package fr.cyu.jee.controller;

import fr.cyu.jee.HibernateUtil;
import fr.cyu.jee.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

import org.hibernate.Session;

/**
 * This servlet checks if the login information are correct.
 */
public class LoginController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String identification = req.getParameter("identification");
        String cryptedPassword = req.getParameter("cryptedPassword");
        if(identification.isEmpty() || cryptedPassword.isEmpty()){ //if the data entered is not valid
            req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
        }
        else{//if the data entered is valid
            if(isUserValid(identification, cryptedPassword)){
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
            }
            else{
                req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
            }
        }
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

    /**
     * Compares all users in the database to the one that tries to log in.
     * @param  id of the user
     * @param password user's crypted password
     * @return True if the user is in the database, else false.
     */
    private Boolean isUserValid(String id , String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<User> userList = getListUsers();
        for (int i = 0; i < userList.size(); i++) {
            if (id.equals(userList.get(i).getIdentification()) && password.equals(userList.get(i).getCryptedPassword())) {//if a user is in the database, then return his id in the base and confirm the login
                session.getTransaction().commit();
                session.close();
                return true;
            }
        }
        session.getTransaction().commit();
        session.close();
        return false;
    }

}
