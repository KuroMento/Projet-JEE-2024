package fr.cyu.jee.controller;

import fr.cyu.jee.HibernateUtil;
import fr.cyu.jee.model.Permissions;
import fr.cyu.jee.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.hibernate.Session;

/**
 * This servlet checks if the login information are correct.
 */
public class LoginController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lastName = req.getParameter("lastName");
        String firstName = req.getParameter("firstName");
        String contact = req.getParameter("contact");
        String identification = req.getParameter("identification");
        String cryptedPassword = req.getParameter("cryptedPassword");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd", Locale.FRENCH);
        String dateOfBirthString = req.getParameter("dateOfBirth");
        Date dateOfBirth = null;
        Permissions permissions = null;
        try {
            dateOfBirth = formatter.parse(dateOfBirthString); //Caution : there might be errors if the incorrect date format is entered
        }
        catch (ParseException e){
            req.setAttribute("dateError","The date format is not correct! (it should be 'YYYY/MM/DD')");
        }
        if(lastName.isEmpty() || firstName.isEmpty() || contact.isEmpty() || identification.isEmpty() || cryptedPassword.isEmpty() || dateOfBirth == null){ //if the data entered is not valid
            req.setAttribute("error", "All data must be completed"); //pas la bonne méthode, à changer mais reste la pour l'instant
            req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
        }
        else{//if the data entered is valid
            User user = new User(lastName, firstName, contact, identification, cryptedPassword, dateOfBirth, permissions);
            if(isUserValid(user)){
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
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
        List<User> result = session.createQuery("from Users").list();
        session.close();
        return result;
    }

    /**
     * Compares all users in the database to the one that tries to log in.
     * @param user The user that tries to log in.
     * @return True if the user is in the database, else false.
     */
    private Boolean isUserValid(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<User> userList;
        userList = getListUsers();
        for (int i = 0; i < userList.size(); i++) {
            if (user.getFirstName().equals(userList.get(i).getFirstName()) && user.getLastName().equals(userList.get(i).getLastName()) && user.getContact().equals(userList.get(i).getContact()) && user.getIdentification().equals(userList.get(i).getIdentification()) && user.getCryptedPassword().equals(userList.get(i).getCryptedPassword()) && user.getDateOfBirth().equals(userList.get(i).getDateOfBirth())) {//if a user is in the database, then return his id in the base and confirm the login
                givePermissions(user, i);
                session.getTransaction().commit();
                session.close();
                return true;
            }
        }
        session.getTransaction().commit();
        session.close();
        return false;
    }

    /**
     * Give the according role to the user once the login is successful.
     * @param user The user that successfully logged in.
     * @param id User id in the database.
     */
    private void givePermissions(User user, int id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<User> userList;
        userList = getListUsers();
        if(userList.get(id).getPermissions().equals(Permissions.ADMIN)){
            user.setPermissions(Permissions.ADMIN);
        }
        if(userList.get(id).getPermissions() == Permissions.TEACHER){
            user.setPermissions(Permissions.TEACHER);
        }
        if(userList.get(id).getPermissions() == Permissions.STUDENT){
            user.setPermissions(Permissions.STUDENT);
        }
    }

}
