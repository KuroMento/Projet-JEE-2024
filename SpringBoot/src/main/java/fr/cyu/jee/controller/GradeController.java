package fr.cyu.jee.controller;

import fr.cyu.jee.ModelValidator;
import fr.cyu.jee.model.Course;
import fr.cyu.jee.model.Grade;
import fr.cyu.jee.model.Permissions;
import fr.cyu.jee.model.User;
import fr.cyu.jee.repository.GradeRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedList;
import java.util.List;

@Controller
public class GradeController {
    @Autowired
    private GradeRepository gradeRepository;
    @GetMapping("/grade")
    public String gradePage(HttpSession session, Model model, @RequestParam(defaultValue = "") String option){
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
                    //Operations for student
                    if(option.equals("report")){
                        model.addAttribute("option", "report");
                        model.addAttribute("selectedGrade", new Grade());
                        List<Grade> gradeList = gradeRepository.findAllByStudent_Identification(loggedUser.getIdentification());
                        List<Course> courseList = new LinkedList<>();
                        for(Grade grade : gradeList){
                            courseList.add(grade.getCourse());
                        }
                        model.addAttribute("courses", courseList);
                    }
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
