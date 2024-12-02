package fr.cyu.jee.controller;

import fr.cyu.jee.HibernateUtil;
import fr.cyu.jee.ModelValidator;
import fr.cyu.jee.model.Subject;
import fr.cyu.jee.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import java.io.IOException;
import java.util.List;

/**
 * Servlet that returns the list of available subjects.
 */
public class SubjectController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String option = req.getParameter("option");
        String action = req.getParameter("action");
        try {
            // We verify if there are action to perform
            if (action != null && !action.isEmpty() && !action.isBlank()) {
                Subject newSubject = new Subject();
                // Choosing the action to perform after initialisation
                if (action.equals("create")) {
                    newSubject = instantiateSubject(req);
                    createSubject(newSubject);
                }
                if (action.equals("update")) {
                    Long subjectId = Long.valueOf(req.getParameter("subject"));
                    newSubject = instantiateSubject(req);
                    newSubject.setIdentification(subjectId);
                    updateSubject(newSubject);
                }
                if (action.equals("delete")) {
                    Long subjectId = Long.valueOf(req.getParameter("subject"));
                    newSubject.setIdentification(subjectId);
                    deleteSubject(newSubject);
                }
            }
            if (option != null && !option.isBlank() && !option.isEmpty()) {
                if (option.equals("create")) {
                    Subject selectedSubject = new Subject();
                    req.setAttribute("selectedSubject", selectedSubject);
                }
                if (option.equals("update") && req.getParameter("subject") != null) {
                    Long subjectId = Long.valueOf(req.getParameter("subject"));
                    Subject selectedSubject = getSubjectById(subjectId);
                    req.setAttribute("selectedSubject", selectedSubject);
                }
                if (option.equals("delete") && req.getParameter("subject") != null) {
                    Long subjectId = Long.valueOf(req.getParameter("subject"));
                    Subject selectedSubject = getSubjectById(subjectId);
                    req.setAttribute("selectedSubject", selectedSubject);
                }
                if (option.equals("associatedCourses") && req.getParameter("subject") != null) {
                    Long subjectId = Long.valueOf(req.getParameter("subject"));
                    req.setAttribute("courses", CoursesController.getSubjectAssociatedCourses(subjectId));
                    req.getRequestDispatcher("/WEB-INF/course.jsp").forward(req, resp);
                }
                req.getRequestDispatcher("/WEB-INF/subject.jsp").forward(req, resp);
            }
        }
        catch (Exception e){
            System.out.println(e);
            req.setAttribute("error",e.getMessage());
            req.getRequestDispatcher("/WEB-INF/subject.jsp").forward(req,resp);
        }
        req.setAttribute("subjects", getListSubjects());
        req.getRequestDispatcher("/WEB-INF/subject.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public static Subject instantiateSubject(HttpServletRequest request){
        String label = request.getParameter("label");
        String description = request.getParameter("description");
        String coefficient = request.getParameter("coefficient");

        ModelValidator.validateParameter(label);
        ModelValidator.validateParameter(description);
        ModelValidator.validateParameter(coefficient);
        ModelValidator.validateDouble(coefficient);

        Subject newSubject = new Subject();
        newSubject.setLabel(label);
        newSubject.setDescription(description);
        newSubject.setCoefficient(Double.parseDouble(coefficient));

        return newSubject;
    }

    /**
     *
     * @return The subjects in the database as a list.
     */
    public static List<Subject> getListSubjects(){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Subject";
            Query<Subject> userQuery = session.createQuery(hql);
            return userQuery.getResultList();
        }
    }

    public static Subject getSubjectById(Long id){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT s FROM Subject s JOIN FETCH s.courses WHERE s.id = :id";
            Query<Subject> userQuery = session.createQuery(hql)
                    .setParameter("id",id);
            return userQuery.uniqueResult();
        }
    }

    // Creation, Update and Deletion for the Subject class
    public static void createSubject(Subject subject){
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(subject);
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

    public static void updateSubject(Subject subject){
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(subject);
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

    public static void deleteSubject(Subject subject){
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(subject);
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
