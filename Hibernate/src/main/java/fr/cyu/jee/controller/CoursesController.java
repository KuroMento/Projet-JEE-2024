package fr.cyu.jee.controller;

import fr.cyu.jee.HibernateUtil;
import fr.cyu.jee.ModelValidator;
import fr.cyu.jee.model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.*;

/**
 * This servlet send the according courses to a teacher or a student
 */
public class CoursesController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String option = req.getParameter("option");
        String action = req.getParameter("action");
        try {
            // We verify if there are action to perform
            if (action != null && !action.isEmpty() && !action.isBlank()) {
                Course newCourse = new Course();
                // Choosing the action to perform after initialisation
                if (action.equals("create")) {
                    newCourse = instantiateCourse(req);
                    createCourse(newCourse);
                }
                if (action.equals("update")) {
                    Long courseId = Long.valueOf(String.valueOf(req.getParameter("course")));
                    newCourse = instantiateCourse(req);
                    newCourse.setIdentification(courseId);
                    updateCourse(newCourse);
                }
                if (action.equals("delete")) {
                    Long courseId = Long.valueOf(String.valueOf(req.getParameter("subject")));
                    newCourse.setIdentification(courseId);
                    deleteCourse(newCourse);
                }
            }

            // We want to perform a future action maybe ?
            if (option != null && !option.isBlank() && !option.isEmpty()) {
                if (option.equals("create")) {
                    Course selctedCourse = new Course();
                    req.setAttribute("selectedCourse", selctedCourse);
                }
                if (option.equals("update") && req.getParameter("course") != null) {
                    Long courseId = Long.valueOf(req.getParameter("course"));
                    Course selectedCourse = getCourseById(courseId);
                    req.setAttribute("selectedCourse", selectedCourse);
                }
                if (option.equals("delete") && req.getParameter("course") != null) {
                    Long courseId = Long.valueOf(req.getParameter("course"));
                    Course selectedCourse = getCourseById(courseId);
                    req.setAttribute("selectedCourse", selectedCourse);
                }
                req.getRequestDispatcher("/WEB-INF/course.jsp").forward(req, resp);
            }
        }
        catch (Exception e){
            System.out.println(e);
            req.setAttribute("error",e.getMessage());
            req.getRequestDispatcher("/WEB-INF/course.jsp").forward(req,resp);
        }
        req.setAttribute("courses", getListCourses());
        req.getRequestDispatcher("/WEB-INF/course.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public static Course instantiateCourse(HttpServletRequest request){
        String teacherId = request.getParameter("teacher");
        String subjectId = request.getParameter("subject");

        ModelValidator.validateParameter(teacherId);
        ModelValidator.validateParameter(subjectId);
        ModelValidator.validateDouble(subjectId);

        Teacher teacher = UserController.getTeacher(teacherId);
        Subject subject = SubjectController.getSubjectById(Long.parseLong(subjectId));

        Course newCourse = new Course();
        newCourse.setTeacher(teacher);
        newCourse.setSubject(subject);

        return newCourse;
    }

    /**
     *
     * @return The users in the database as a list.
     */
    public static List<Course> getListCourses() {
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
    public static Course getCourseById(Long id){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Course WHERE id = :id";
            Query<Course> userQuery = session.createQuery(hql)
                    .setParameter("id",id);
            return userQuery.uniqueResult();
        }
    }

    public static List<Course> getCourseWithGradesByStudentId(String id){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT DISTINCT c FROM Course c JOIN FETCH c.grades g WHERE g.student.id = :id";
            Query<Course> userQuery = session.createQuery(hql)
                    .setParameter("id",id);
            return userQuery.getResultList();
        }
    }

    public static List<Course> getSubjectAssociatedCourses(Long id){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT c FROM Course c JOIN FETCH c.students s WHERE c.subject.id = :id";
            Query<Course> userQuery = session.createQuery(hql)
                    .setParameter("id",id);
            return userQuery.getResultList();
        }
    }

    // Creation, Update and Deletion for the Course class
    public static void createCourse(Course course){
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(course);
            transaction.commit();
        }
        catch (Exception e){
            if(transaction != null){ transaction.rollback(); }
            System.out.println(e);
        }
        finally {
            if( session != null){ session.close();}
        }
    }

    public static void updateCourse(Course course){
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(course);
            transaction.commit();
        }
        catch (Exception e){
            if(transaction != null){ transaction.rollback(); }
            System.out.println(e);
        }
        finally {
            if( session != null){ session.close();}
        }
    }

    public static void deleteCourse(Course course){
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(course);
            transaction.commit();
        }
        catch (Exception e){
            if(transaction != null){ transaction.rollback(); }
            System.out.println(e);
        }
        finally {
            if( session != null){ session.close();}
        }
    }

}
