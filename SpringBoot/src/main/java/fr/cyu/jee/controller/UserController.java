
package fr.cyu.jee.controller;

import fr.cyu.jee.ModelValidator;
import fr.cyu.jee.model.Permissions;
import fr.cyu.jee.model.User;
import fr.cyu.jee.repository.UserRepository;
import jakarta.persistence.EntityManager;
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

    /**
     *
     * @param session
     * @param model
     * @param option attribute option
     * @param action attribute action
     * @param user attribute user
     * @param id attribute id
     * @param pw attribute pw
     * @param firstName attribute firstName
     * @param lastName attribute lastName
     * @param contact attribute contact
     * @param dateOfBirth attribute dateOfBirth
     * @param permissions attribute permissions
     * @return
     */
    @Transactional
    @GetMapping("/user")
    public String userPage(HttpSession session, Model model, @RequestParam(defaultValue = "") String option, @RequestParam(defaultValue = "") String action,
                           @RequestParam(defaultValue = "") String user, @RequestParam(defaultValue = "") String id, @RequestParam(defaultValue = "") String pw,
                           @RequestParam(defaultValue = "") String firstName, @RequestParam(defaultValue = "") String lastName,
                           @RequestParam(defaultValue = "") String contact,@RequestParam(defaultValue = "") String dateOfBirth,
                           @RequestParam(defaultValue = "") String permissions) {
        try {
            if (session.getAttribute("loggedUser") != null && ((User) session.getAttribute("loggedUser")).getPermissions() == Permissions.ADMIN) {
                //If an option was selected in the CRUD settings
                if (!option.equals("")) {
                    switch (option) {
                        case "create":
                            model.addAttribute("option", "create");
                            model.addAttribute("selectedUser", new User());
                            break;
                        case "delete":
                            if (!id.equals("")) {
                                ModelValidator.validateParameter(id);
                                deleteUser(id);
                            }
                            break;
                        case "update":
                            if (!id.equals("")) {
                                ModelValidator.validateParameter(id);
                                model.addAttribute("option", "update");
                                model.addAttribute("selectedUser", userRepository.findUserByIdentification(id));
                            }
                            break;
                    }
                }
                //If an option from the CRUD settings needs to be applied
                if (!action.equals("")) {
                    switch (action) {
                        case "create":
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
                            ModelValidator.validateParameter(id);
                            ModelValidator.validateParameter(firstName);
                            ModelValidator.validateParameter(lastName);
                            ModelValidator.validateParameter(permissions);
                            ModelValidator.validateParameter(contact);
                            ModelValidator.validateParameter(pw);
                            ModelValidator.validateParameter(dateOfBirth);
                            try {
                                createUser(id, pw, contact, Permissions.valueOf(permissions), firstName, lastName, formatter.parse(dateOfBirth));
                            } catch (ParseException e) {
                                throw new RuntimeException(e);
                            }
                            break;
                        case "update":
                            ModelValidator.validateParameter(id);
                            ModelValidator.validateParameter(user);
                            ModelValidator.validateParameter(firstName);
                            ModelValidator.validateParameter(lastName);
                            ModelValidator.validateParameter(permissions);
                            ModelValidator.validateParameter(contact);
                            ModelValidator.validateParameter(pw);
                            ModelValidator.validateParameter(dateOfBirth);
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
                            updateUser(id, user, firstName, lastName, Permissions.valueOf(permissions), contact, pw, format.parse(dateOfBirth));
                            break;
                    }
                }
                model.addAttribute("users", userRepository.findAll());
            }
            return "user";
        }
        catch (Exception e){
            System.out.println(e);
            model.addAttribute("error",e.getMessage());
        }
        return null;
    }

    /**
     * Delete the user with id as primary key
     * @param id String identifiant of user
     */
    public void deleteUser(String id){
        userRepository.deleteUserByIdentification(id);
    }

    /**
     * Create a new user with specific parameters
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
     * Update a specific user of primary key id with its new data
     * @param id String id, primary key of user
     * @param firstName String firstName, first name of user
     * @param lastName String lastName, last name of user
     * @param permissions Permission permissions, permission of user
     * @param contact String contact, contact of user
     * @param cryptedPassword String cryptedPassword, password of user
     */
    public void updateUser(String id, String newId, String firstName, String lastName, Permissions permissions, String contact, String cryptedPassword, Date dateOfBirth){
        User oldUser = userRepository.findUserByIdentification(id);
        createUser(newId,cryptedPassword,contact,permissions,firstName,lastName,dateOfBirth);
        userRepository.delete(oldUser);
    }

}
