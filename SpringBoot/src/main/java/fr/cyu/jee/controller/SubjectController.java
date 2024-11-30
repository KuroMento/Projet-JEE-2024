package fr.cyu.jee.controller;

import fr.cyu.jee.repository.SubjectRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SubjectController {
    @Autowired
    private SubjectRepository subjectRepository;
    @GetMapping("/subject")
    public String subjectPage(HttpSession session, Model model){
        if(session.getAttribute("loggedUser") != null){
            model.addAttribute("subjects", subjectRepository.findAll());
        }
        return "subject";
    }
}
