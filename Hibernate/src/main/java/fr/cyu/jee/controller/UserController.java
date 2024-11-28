package fr.cyu.jee.controller;

import fr.cyu.jee.HibernateUtil;
import fr.cyu.jee.model.Course;
import fr.cyu.jee.model.Permissions;
import fr.cyu.jee.model.Subject;
import fr.cyu.jee.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Servlet that returns the list of users.
 */
public class UserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String option = req.getParameter("option");
        String action = req.getParameter("action");
        User loggedUser = (User) req.getSession().getAttribute("loggedUser");

        if(loggedUser != null && loggedUser.getPermissions() == Permissions.ADMIN){
            req.setAttribute("users",getListUsers());
            if(action != null && !action.isBlank() && !action.isEmpty()){
                String id = req.getParameter("id");
                String firstName = req.getParameter("firstName");
                String lastName = req.getParameter("lastName");
                String pw = req.getParameter("pw");
                Date dateOfBirth = new Date(req.getParameter("dateOfBirth"));
                List<Course> courses = new ArrayList<>();
                //Permissions permissions = Permissions(req.getParameter("permissions"));

                String[] courseIds = req.getParameterValues("course_ids");
                for(String cid : courseIds){
                    courses.add(CoursesController.getCourseById(cid));
                }

                if( action.equals("create") ){
                    createUser(new User());
                }
            }
        }
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
    public static List<User> getListUsers(){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM User";
            Query<User> userQuery = session.createQuery(hql);
            return userQuery.getResultList();
        }
    }

    public static User getUserById(String id){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM User WHERE id = :id";
            Query<User> userQuery = session.createQuery(hql)
                    .setParameter("id",id);
            return userQuery.uniqueResult();
        }
    }

    // Creation, Update and Deletion for the Subject class
    public static void createUser(User user){
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(user);
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

    public static void updateUser(User user){
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(user);
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

    public static void deleteUser(User user){
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(user);
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
