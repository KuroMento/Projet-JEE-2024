package fr.cyu.jee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GradeController {
    @GetMapping("/grade")
    public String gradePage(){
        return "grade";
    }
}
