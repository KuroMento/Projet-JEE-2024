package fr.cyu.jee.controller;

import fr.cyu.jee.HibernateUtil;
import fr.cyu.jee.model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

/**
 * This servlet send the according courses to a teacher or a student
 */
public class CoursesController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("courses",getAllCourses());
        req.getRequestDispatcher("WEB-INF/course.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    /**
     *
     * @return The users in the database as a list.
     */
    public static List<Course> getAllCourses() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT c FROM Course c JOIN FETCH c.students";
            Query<Course> userQuery = session.createQuery(hql);
            return userQuery.getResultList();
        }
    }

    /**
     *
     * @return The corresponding course according to the id
     */
    public static Course getCourseById(String id){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Course WHERE id = :id";
            Query<Course> userQuery = session.createQuery(hql)
                    .setParameter("id",id);
            return userQuery.uniqueResult();
        }
    }

}
