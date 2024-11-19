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
        String isLoggedString = req.getParameter("logged");
        String userIdString = req.getParameter("userId");
        List<Course> coursesList = null;
        int intId = 0;
        List<User> userList = getListUsers();
        if(isLoggedString.equals("True")){
            for(int i = 0; i < userList.size(); i++) {
                if (userIdString.equals(userList.get(i).getIdentification())) {
                    intId = i;//get the id in the userList
                }
            }
            if(userList.get(intId).getPermissions() == Permissions.STUDENT){
                List<Student> studentList = getListStudents();
                for(int i = 0; i < studentList.size(); i++) {
                    if (userIdString.equals(studentList.get(i).getIdentification())) {
                        intId = i;//get the id in the studentList
                    }
                }
                coursesList = studentList.get(intId).getCourses();
            }
            if(userList.get(intId).getPermissions() == Permissions.TEACHER){
                List<Teacher> teacherList = getListTeachers();
                for(int i = 0; i < teacherList.size(); i++) {
                    if (userIdString.equals(teacherList.get(i).getIdentification())) {
                        intId = i;//get the id in the teacherList
                    }
                }
                coursesList = teacherList.get(intId).getCourses();
            }
            req.setAttribute("CoursesList", coursesList);
        }
        else{
            req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp); //if the user is not logged, send him back to connexion page
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
