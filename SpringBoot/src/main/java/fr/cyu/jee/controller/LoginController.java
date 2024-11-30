package fr.cyu.jee.controller;

import fr.cyu.jee.model.User;
import fr.cyu.jee.repository.UserRepository;
import jakarta.servlet.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @GetMapping("/logout")
    public String logoutResponse(HttpSession session){
        session.removeAttribute("loggedUser");
        return "redirect:/";
    }

    @PostMapping("/login/enter")
    public String loginResponse(@RequestParam(name = "id") String id, @RequestParam(name = "pw") String pw,  HttpSession session){
        System.out.println("Check de passage");
        if( id == null || pw == null ){
            System.out.println("A parameter is missing");
        }
        else{
            System.out.println("Get User : ");
            User loggedUser = getUser(id,pw);
            if (loggedUser == null ){
                System.out.println("Incorrect");
            }
            else {
                if(loggedUser.getCryptedPassword().equals(pw) && loggedUser.getIdentification().equals(id)){
                    session.setAttribute("loggedUser", loggedUser);
                }
            }
        }
        return "redirect:/";
    }

    /**
     *
     * @param id the user's id
     * @param pw the user's password
     * @return the user in the database if the logging information are correct
     */
    public User getUser(String id, String pw){
        User user = userRepository.findUserByIdentification(id);
        if(user.getIdentification().equals(id) && user.getCryptedPassword().equals(pw)) {
            return user;
        }
        return null;
    }
}
