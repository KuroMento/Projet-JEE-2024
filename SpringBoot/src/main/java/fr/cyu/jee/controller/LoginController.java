package fr.cyu.jee.controller;

import fr.cyu.jee.model.User;
import fr.cyu.jee.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * This servlet checks if the login information are correct.
 */
@Controller
public class LoginController extends HttpServlet{
    @GetMapping("/")
    public String home(){
        return "/WEB-INF/jsp/index.jsp";
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String pw = req.getParameter("pw");

        if( id == null || pw == null ){
            req.setAttribute("error", "A parameter is missing");
        }
        else{
            User loggedUser = getUser(id,pw);
            if (loggedUser == null ){
                req.setAttribute("error", "The id or the password is incorrect");
            }
            else {
                req.getSession().setAttribute("loggedUser", loggedUser);
            }
        }
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Autowired
    private UserRepository userRepository;

    /**
     *
     * @param id the user's id
     * @param pw the user's password
     * @return the user in the database if the logging information are correct
     */
    public User getUser(String id, String pw){
        Optional<User> userById = userRepository.findById(id);
        User user = userById.get();
        //compare the user with the database
        if(user.getIdentification().equals(id) && user.getCryptedPassword().equals(pw)) {
            return user;
        }
        return null;
    }
}
