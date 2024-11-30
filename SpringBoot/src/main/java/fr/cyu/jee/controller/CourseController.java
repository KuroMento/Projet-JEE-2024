package fr.cyu.jee.controller;

import fr.cyu.jee.repository.CourseRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CourseController{
    @Autowired
    private CourseRepository courseRepository;
    @GetMapping("/course")
    public String coursePage(HttpSession session, Model model){
        if(session.getAttribute("loggedUser") != null){
            model.addAttribute("courses", courseRepository.findAll());
        }
        return "course";
    }
}
