package fr.cyu.jee.controller;

import fr.cyu.jee.model.Permissions;
import fr.cyu.jee.model.User;
import fr.cyu.jee.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/user")
    public String userPage(HttpSession session, Model model){
        if(session.getAttribute("loggedUser") != null && ((User)session.getAttribute("loggedUser")).getPermissions() == Permissions.ADMIN){
            model.addAttribute("users", userRepository.findAll());
        }
        return "user";
    }
}
