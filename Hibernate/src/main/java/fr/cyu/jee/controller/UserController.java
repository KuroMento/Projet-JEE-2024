package fr.cyu.jee.controller;

import fr.cyu.jee.HibernateUtil;
import fr.cyu.jee.model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

        // Only Admins can perform action on the database and have access to options
        if(loggedUser != null && loggedUser.getPermissions() == Permissions.ADMIN){

            // IF ACTION
            if(action != null && !action.isBlank() && !action.isEmpty()){
                User newUser = new User();
                String id = req.getParameter("id");
                String firstName = req.getParameter("firstName");
                String lastName = req.getParameter("lastName");
                String pw = req.getParameter("pw");
                String contact = req.getParameter("contact");
                SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
                Date dateOfBirth = null;
                try {
                    dateOfBirth = sdf.parse(req.getParameter("dateOfBirth"));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

                Permissions permissions = null;
                switch (req.getParameter("permissions")){
                    case "ADMIN":
                        permissions = Permissions.ADMIN;
                        break;
                    case "TEACHER":
                        permissions = Permissions.TEACHER;
                        break;
                    default:
                        permissions = Permissions.STUDENT;
                        break;
                }

                newUser.setIdentification(id);
                newUser.setFirstName(firstName);
                newUser.setLastName(lastName);
                newUser.setContact(contact);
                newUser.setCryptedPassword(pw);
                newUser.setDateOfBirth(dateOfBirth);
                newUser.setPermissions(permissions);

                if( action.equals("create") ){
                    createUser(newUser);
                }
                if( action.equals("update") ){
                    updateUser(newUser);
                }
                if( action.equals("delete") ){
                    deleteUser(newUser);
                }
            }
            if( option != null && !option.isEmpty() && !option.isBlank()){
                if(option.equals("create")){
                    User selectedUser = new User();
                    req.setAttribute("selectedUser",selectedUser);
                }
                if( option.equals("update")){
                    User selectedUser = getUserById(req.getParameter("id"));
                    req.setAttribute("selectedUser",selectedUser);
                }
                if( option.equals("delete")){
                    User selectedUser = getUserById(req.getParameter("id"));
                    req.setAttribute("selectedUser",selectedUser);
                }
            }
            else{
                req.setAttribute("users",getListUsers());
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

    // Creation, Update and Deletion for the User class
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

    public static Student getStudent(User student){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT DISTINCT s FROM Student s JOIN FETCH s.courses WHERE s.id = :id";
            Query<Student> userQuery = session.createQuery(hql)
                    .setParameter("id",student.getIdentification());
            return userQuery.uniqueResult();
        }
    }

    public static Student getStudent(String studentId){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT DISTINCT s FROM Student s JOIN FETCH s.courses WHERE s.id = :id";
            Query<Student> userQuery = session.createQuery(hql)
                    .setParameter("id",studentId);
            return userQuery.uniqueResult();
        }
    }

    public static Teacher getTeacher(User teacher){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT DISTINCT t FROM Teacher t JOIN FETCH t.courses WHERE t.id = :id";
            Query<Teacher> userQuery = session.createQuery(hql)
                    .setParameter("id",teacher.getIdentification());
            return userQuery.uniqueResult();
        }
    }

    public static Teacher getTeacher(String teacherId){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT DISTINCT t FROM Teacher t JOIN FETCH t.courses WHERE t.id = :id";
            Query<Teacher> userQuery = session.createQuery(hql)
                    .setParameter("id",teacherId);
            return userQuery.uniqueResult();
        }
    }

}
