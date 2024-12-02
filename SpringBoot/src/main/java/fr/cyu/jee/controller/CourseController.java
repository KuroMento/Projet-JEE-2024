package fr.cyu.jee.controller;

import fr.cyu.jee.ModelValidator;
import fr.cyu.jee.model.*;
import fr.cyu.jee.repository.CourseRepository;
import fr.cyu.jee.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class CourseController{
    @Autowired
    private CourseRepository courseRepository;
    @GetMapping("/course")
    public String coursePage(HttpSession session, Model model, @RequestParam(defaultValue = "") String option,  @RequestParam(defaultValue = "") String action,
                             @RequestParam(defaultValue = "") String id){
        if(session.getAttribute("loggedUser") != null){
            //If an option was selected in the CRUD settings
            System.out.println("LOGGED");
            try {
                if (((User) session.getAttribute("loggedUser")).getPermissions() == Permissions.ADMIN && !option.equals("")) {
                    System.out.println("OPTION");
                    switch (option) {
                        case "create":
                            model.addAttribute("option", "create");
                            model.addAttribute("selectedCourse", new Course());
                            break;
                        case "delete":
                            if (!id.equals("")) {
                                ModelValidator.validateDouble(id);
                                deleteCourse(Long.parseLong(id));
                            }
                            break;
                        case "update":
                            if (!id.equals("")) {
                                ModelValidator.validateDouble(id);
                                model.addAttribute("option", "update");
                                model.addAttribute("selectedSubject", courseRepository.findCourseByIdentification(Long.parseLong(id)));
                            }
                            break;
                    }
                }
                return "course";
            }
            catch (Exception e){
                System.out.println(e);
                model.addAttribute("error",e.getMessage());
            }
            model.addAttribute("courses", courseRepository.findAll());
        }
        return null;
    }

    /**
     * Delete the course with id as primary key
     * @param id Long identifiant of course
     */
    public void deleteCourse(Long id){
        courseRepository.deleteCourseByIdentification(id);
    }

    /**
     * Create a new course with specific parameters
     */
    public void createCourse(){
        Course newCourse = new Course();
        courseRepository.save(newCourse);
    }

    /**
     * Update a specific course of primary key id with its new data
     * @param id Long id, primary key of course
     */
    public void updateCourse(Long id){
        Course course = courseRepository.findCourseByIdentification(id);
        courseRepository.save(course);
    }
}
