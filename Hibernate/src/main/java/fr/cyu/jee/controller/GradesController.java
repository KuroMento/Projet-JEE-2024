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
import java.util.List;

/**
 * This servlet gives the grades to a user
 */
public class GradesController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String option = req.getParameter("option");
        String action = req.getParameter("action");
        try {
            // We verify if there are action to perform
            if (action != null && !action.isEmpty() && !action.isBlank()) {
                Grade newGrade = null;
                // Choosing the action to perform after initialisation
                if (action.equals("create")) {
                    newGrade = instantiateGrade(req);
                    createGrades(newGrade);
                }
                if (action.equals("update")) {
                    Long gradeId = Long.valueOf(String.valueOf(req.getParameter("grade")));
                    newGrade = instantiateGrade(req);
                    newGrade.setIdentification(gradeId);
                    updateGrade(newGrade);
                }
                if (action.equals("delete")) {
                    Long gradeId = Long.valueOf(String.valueOf(req.getParameter("grade")));
                    newGrade = instantiateGrade(req);
                    newGrade.setIdentification(gradeId);
                    deleteGrade(newGrade);
                }
            }
            if (option != null && !option.isBlank() && !option.isEmpty()) {
                System.err.println("Option == " + option);
                if (option.equals("create")) {
                    Grade selectedGrade = new Grade();
                    req.setAttribute("selectedGrade", selectedGrade);
                }
                if (option.equals("update") && req.getParameter("grade") != null) {
                    Long gradeId = Long.valueOf(String.valueOf(req.getParameter("grade")));
                    Grade selectedGrade = getGradeById(gradeId);
                    req.setAttribute("selectedGrade", selectedGrade);
                }
                if (option.equals("delete") && req.getParameter("grade") != null) {
                    Long gradeId = Long.valueOf(String.valueOf(req.getParameter("grade")));
                    Grade selectedGrade = getGradeById(gradeId);
                    req.setAttribute("selectedGrade", selectedGrade);
                }
                if ( option.equals("report") ) {
                    User currentUser = (User) req.getSession().getAttribute("loggedUser");
                    Grade selectedGrade = new Grade();
                    req.setAttribute("selectedGrade", selectedGrade);
                    req.setAttribute("courses", CoursesController.getCourseWithGradesByStudentId(currentUser.getIdentification()));
                }
                req.getRequestDispatcher("/WEB-INF/grade.jsp").forward(req, resp);
            }
        }
        catch (Exception e){
            System.out.println(e);
            System.out.println(e.getCause());
            req.setAttribute("error",e.getMessage());
            req.getRequestDispatcher("/WEB-INF/grade.jsp").forward(req,resp);
        }
        User currentUser = (User) req.getSession().getAttribute("loggedUser");

        if( currentUser != null ) {
            req.setAttribute("grades", getUserListGrades(currentUser.getIdentification()));
        }

        req.getRequestDispatcher("WEB-INF/grade.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public static Grade instantiateGrade(HttpServletRequest request){
        String label = request.getParameter("label");
        String coefficient = request.getParameter("coefficient");
        String studentId = request.getParameter("student");
        String value = request.getParameter("value");
        String courseId = request.getParameter("course");

        ModelValidator.validateParameter(label);
        ModelValidator.validateParameter(value);
        ModelValidator.validateParameter(coefficient);
        ModelValidator.validateParameter(studentId);
        ModelValidator.validateParameter(courseId);
        ModelValidator.validateDouble(value);
        ModelValidator.validateDouble(coefficient);

        Student student = UserController.getStudent(studentId);
        Course course = CoursesController.getCourseById(courseId);

        Grade newGrade = new Grade();
        newGrade.setLabel(label);
        newGrade.setStudent(student);
        newGrade.setValue(Double.parseDouble(value));
        newGrade.setCourse(course);
        newGrade.setCoefficient(Double.parseDouble(coefficient));

        return newGrade;
    }

    /**
     *
     * @return The grades in the database for a specific user.
     */
    public static List<Grade> getUserListGrades(String id){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Grade WHERE student_id = :id";
            Query<Grade> userQuery = session.createQuery(hql)
                    .setParameter("id",id);
            return userQuery.getResultList();
        }
    }

    public static Grade getGradeById(Long id){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT g FROM Grade JOIN FETCH s.courses WHERE id = :id";
            Query<Grade> userQuery = session.createQuery(hql)
                    .setParameter("id",id);
            return userQuery.uniqueResult();
        }
    }

    // Creation, Update and Deletion for the Subject class
    public static void createGrades(Grade grade){
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(grade);
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

    public static void updateGrade(Grade grade){
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(grade);
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

    public static void deleteGrade(Grade grade){
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(grade);
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
