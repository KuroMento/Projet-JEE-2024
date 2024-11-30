package fr.cyu.jee.controller;

import fr.cyu.jee.model.User;
import fr.cyu.jee.repository.CourseRepository;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController extends HttpServlet {
    @Autowired
    private CourseRepository courseRepository;
    @GetMapping("/")
    public String defaultPage(HttpSession session, Model model){
        User loggedUser = (User) session.getAttribute("loggedUser");
        if(loggedUser != null){
            model.addAttribute("courses", courseRepository.findAllByTeacher_Identification(loggedUser.getIdentification()));
        }
        return "course";
    }
}
