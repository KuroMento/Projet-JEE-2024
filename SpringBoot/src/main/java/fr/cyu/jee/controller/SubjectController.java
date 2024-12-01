package fr.cyu.jee.controller;

import fr.cyu.jee.model.Permissions;
import fr.cyu.jee.model.Subject;
import fr.cyu.jee.model.User;
import fr.cyu.jee.repository.SubjectRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SubjectController {
    @Autowired
    private SubjectRepository subjectRepository;

    /**
     * Controller of subject
     * @param session http session argument
     * @param model spring model argument
     * @param option attribute option
     * @param subject attribute subject
     * @param action attribute action
     * @param label attribute description
     * @param description attribute description
     * @param coefficient attribute coefficient
     * @return
     */
    @Transactional
    @GetMapping("/subject")
    public String subjectPage(HttpSession session, Model model, @RequestParam(defaultValue = "") String option, @RequestParam(defaultValue = "") String subject,
                              @RequestParam(defaultValue = "") String action, @RequestParam(defaultValue = "") String label,
                              @RequestParam(defaultValue = "") String description, @RequestParam(defaultValue = "") String coefficient){
        User loggedUser = (User) session.getAttribute("loggedUser");
        if(loggedUser != null){
            //If an option was selected in the CRUD settings
            if(loggedUser.getPermissions() == Permissions.ADMIN && !option.equals("")){
                switch(option){
                    case "create":
                        model.addAttribute("option", "create");
                        model.addAttribute("selectedSubject", new Subject());
                        break;
                    case "delete":
                        if(!subject.equals("")) {
                            deleteSubject(Long.parseLong(subject));
                        }
                        break;
                    case "update":
                        if(!subject.equals("")) {
                            model.addAttribute("option", "update");
                            model.addAttribute("selectedSubject", subjectRepository.findSubjectByIdentification(Long.parseLong(subject)));
                        }
                        break;
                }
            }
            //If an option from the CRUD settings needs to be applied
            if(loggedUser.getPermissions() == Permissions.ADMIN && !action.equals("")){
                switch(action){
                    case "create":
                        createSubject(label, description, Double.parseDouble(coefficient));
                        break;
                    case "update":
                        updateSubject(label, description, Double.parseDouble(coefficient), Long.parseLong(subject));
                        break;
                }
            }
            //Adds the viewed subjects
            model.addAttribute("subjects", subjectRepository.findAll());
        }
        return "subject";
    }

    /**
     * Delete the subject with id as primary key
     * @param id Long identifiant of subject
     */
    public void deleteSubject(Long id){
        subjectRepository.deleteSubjectByIdentification(id);
    }

    /**
     * Create a new subject with a specific label, description and coefficient
     * @param label String label of the subject
     * @param description String description of the subject
     * @param coefficient Double coefficient of the subject
     */
    public void createSubject(String label, String description, Double coefficient){
        Subject newSubject = new Subject(label, description, coefficient);
        subjectRepository.save(newSubject);
    }

    /**
     * Update a specific subject of primary key id with its new data
     * @param label String label of the subject
     * @param description String description of the subject
     * @param coefficient Double coefficient of the subject
     * @param id Long id, primary key of subject
     */
    public void updateSubject(String label, String description, Double coefficient, Long id){
        Subject subject = subjectRepository.findSubjectByIdentification(id);
        subject.setLabel(label);
        subject.setDescription(description);
        subject.setCoefficient(coefficient);
        subjectRepository.save(subject);
    }
}
