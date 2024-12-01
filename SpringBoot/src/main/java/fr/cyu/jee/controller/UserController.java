package fr.cyu.jee.controller;

import fr.cyu.jee.model.Permissions;
import fr.cyu.jee.model.Subject;
import fr.cyu.jee.model.User;
import fr.cyu.jee.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Transactional
    @GetMapping("/user")
    public String userPage(HttpSession session, Model model, @RequestParam(defaultValue = "") String option, @RequestParam(defaultValue = "") String action,
                           @RequestParam(defaultValue = "") String user, @RequestParam(defaultValue = "") String id, @RequestParam(defaultValue = "") String pw,
                           @RequestParam(defaultValue = "") String firstName, @RequestParam(defaultValue = "") String lastName,
                           @RequestParam(defaultValue = "") String contact,@RequestParam(defaultValue = "") String dateOfBirth,
                           @RequestParam(defaultValue = "") String permissions){
        if(session.getAttribute("loggedUser") != null && ((User)session.getAttribute("loggedUser")).getPermissions() == Permissions.ADMIN){
            if(!option.equals("")){
                switch(option){
                    case "create":
                        model.addAttribute("option", "create");
                        model.addAttribute("selectedUser", new User());
                        break;
                    case "delete":
                        System.out.println("DELETE");
                        if(!id.equals("")) {
                            System.out.println(id);
                            System.out.println("NOW");
                            deleteUser(user);
                        }
                        break;
                    case "update":
                        if(!id.equals("")) {
                            model.addAttribute("option", "update");
                            model.addAttribute("selectedSubject", userRepository.findUserByIdentification(user));
                        }
                        break;
                }
            }
            //If an option from the CRUD settings needs to be applied
            if(!action.equals("")){
                switch(action){
                    case "create":
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
                        try {
                            createUser(id, pw, contact, Permissions.valueOf(permissions), firstName, lastName, formatter.parse(dateOfBirth));
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case "update":
                        updateUser(user);
                        break;
                }
            }
            model.addAttribute("users", userRepository.findAll());
        }
        return "user";
    }
    /**
     * Delete the subject with id as primary key
     * @param id Long identifiant of subject
     */
    public void deleteUser(String id){
        userRepository.deleteUserByIdentification(id);
    }

    /**
     * Create a new subject with a specific label, description and coefficient
     */
    public void createUser(String id, String pw, String contact, Permissions permissions, String firstName, String lastName, Date dateOfBirth){
        User newUser = new User();
        newUser.setIdentification(id);
        newUser.setCryptedPassword(pw);
        newUser.setContact(contact);
        newUser.setPermissions(permissions);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setDateOfBirth(dateOfBirth);
        userRepository.save(newUser);
    }

    /**
     * Update a specific subject of primary key id with its new data
     * @param id Long id, primary key of subject
     */
    public void updateUser(String id){
        User user = userRepository.findUserByIdentification(id);
        userRepository.save(user);
    }

}
