package org.wefine.spring.web.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wefine.spring.model.User;
import org.wefine.spring.model.UserProfile;
import org.wefine.spring.web.service.UserProfileService;
import org.wefine.spring.web.service.UserService;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
public class UserController extends AbstractController {

    @Resource
    private UserProfileService userProfileService;

    @Resource
    private UserService userService;

    @RequestMapping(value = "/newUser", method = RequestMethod.GET)
    public String newRegistration(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);

        return "user/new";
    }

    /*
     * This method will be called on form submission, handling POST request It
     * also validates the user input
     */
    @RequestMapping(value = "/newUser", method = RequestMethod.POST)
    public String saveRegistration(@Valid User user,
                                   BindingResult result, ModelMap model) {

        if (result.hasErrors()) {
            log.info("There are errors");
            return "user/new";
        }
        userService.save(user);

        log.info(user.toString());
        if (user.getUserProfiles() != null) {
            for (UserProfile profile : user.getUserProfiles()) {
                log.info("Profile : " + profile.getType());
            }
        }

        model.addAttribute("success", "User " + user.getFirstName() + " has been registered successfully");
        return "user/success";
    }

    @ModelAttribute("roles")
    public List<UserProfile> initializeProfiles() {
        return userProfileService.findAll();
    }

    @ModelAttribute("user")
    public User user() {
        return new User();
    }

    @RequestMapping(value = { "/list" }, method = RequestMethod.GET)
    public String listAllUsers(ModelMap model) {

        List<User> users = userService.findAll();
        model.addAttribute("users", users);

        return "user/list";
    }

    @RequestMapping(value = { "/edit-user-{id}" }, method = RequestMethod.GET)
    public String editUser(@PathVariable int id, ModelMap model) {
        User user  = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("edit", true);

        return "user/new";
    }

    @RequestMapping(value = { "/edit-user-{id}" }, method = RequestMethod.POST)
    public String updateUser(User user, ModelMap model, @PathVariable int id) {
        userService.updateUser(user);
        model.addAttribute("success", "User " + user.getFirstName()  + " updated successfully");

        return "user/success";
    }

    @RequestMapping(value = { "/delete-user-{id}" }, method = RequestMethod.GET)
    public String deleteUser(@PathVariable int id) {
        userService.deleteUser(id);

        return "redirect:/list";
    }
}
