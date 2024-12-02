package fr.cyu.jee.controller;

import fr.cyu.jee.ModelValidator;
import fr.cyu.jee.model.Permissions;
import fr.cyu.jee.model.User;
import fr.cyu.jee.repository.GradeRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GradeController {
    @Autowired
    private GradeRepository gradeRepository;
    @GetMapping("/grade")
    public String gradePage(HttpSession session, Model model){
        User loggedUser = (User) session.getAttribute("loggedUser");
        try {
            if (loggedUser != null) {
                if (loggedUser.getPermissions() == Permissions.ADMIN) {
                    model.addAttribute("grades", gradeRepository.findAll());
                }
                if (loggedUser.getPermissions() == Permissions.TEACHER) {
                    ModelValidator.validateParameter(loggedUser.getIdentification());
                    model.addAttribute("grades", gradeRepository.findAllByCourse_TeacherIdentificationContaining(loggedUser.getIdentification()));
                }
                if (loggedUser.getPermissions() == Permissions.STUDENT) {
                    ModelValidator.validateParameter(loggedUser.getIdentification());
                    model.addAttribute("grades", gradeRepository.findAllByStudent_Identification(loggedUser.getIdentification()));
                }
            }
            return "grade";
        }
        catch (Exception e){
            System.out.println(e);
            model.addAttribute("error",e.getMessage());
        }
        return null;
    }
}
