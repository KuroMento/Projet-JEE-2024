package fr.cyu.jee.controller;

import fr.cyu.jee.HibernateUtil;
import fr.cyu.jee.model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;

import java.io.IOException;
import java.util.List;

/**
 * This servlet send the according courses to a teacher or a student
 */
public class CoursesController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
    public static List<User> getListUsers() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<User> result = session.createQuery("from User").list();
        session.close();
        return result;
    }

    /**
     *
     * @return The students in the database as a list.
     */
    public static List<Student> getListStudents() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Student> result = session.createQuery("from User WHERE rights = student").list();
        session.close();
        return result;
    }

    /**
     *
     * @return The teachers in the database as a list.
     */
    public static List<Teacher> getListTeachers() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Teacher> result = session.createQuery("from User WHERE rights = teacher").list();
        session.close();
        return result;
    }

}
