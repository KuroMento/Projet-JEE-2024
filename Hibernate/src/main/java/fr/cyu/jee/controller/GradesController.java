package fr.cyu.jee.controller;

import fr.cyu.jee.HibernateUtil;
import fr.cyu.jee.model.Grade;
import fr.cyu.jee.model.Subject;
import fr.cyu.jee.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

/**
 * This servlet gives the grades to a user
 */
public class GradesController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = (User) req.getSession().getAttribute("loggedUser");
        req.setAttribute("grades", getListGrades(currentUser.getIdentification()));
        req.getRequestDispatcher("WEB-INF/grade.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    /**
     *
     * @return The grades in the database for a specific user.
     */
    public static List<Grade> getListGrades(String id){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Grade WHERE studentId = :id";
            Query<Grade> userQuery = session.createQuery(hql)
                    .setParameter("id",id);
            return userQuery.getResultList();
        }
    }

}
