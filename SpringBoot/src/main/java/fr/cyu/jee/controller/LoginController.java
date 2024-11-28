package fr.cyu.jee.controller;

import fr.cyu.jee.model.User;
import fr.cyu.jee.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController extends HttpServlet{

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @PostMapping("/login/enter")
    public String loginResponse(@RequestParam(name = "id") String id, @RequestParam(name = "pw") String pw, Model model){
        if( id == null || pw == null ){
            model.addAttribute("error", "A parameter is missing");
        }
        else{
            User loggedUser = getUser(id,pw);
            if (loggedUser == null ){
                model.addAttribute("error", "The id or the password is incorrect");
            }
            else {
                model.addAttribute("loggedUser", loggedUser);
            }
        }
        return "redirect:/index";
    }

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
