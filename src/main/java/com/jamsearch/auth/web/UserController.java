package com.jamsearch.auth.web;

import com.jamsearch.auth.model.User;
import com.jamsearch.auth.repository.PostRepository;
import com.jamsearch.auth.repository.UserRepository;
import com.jamsearch.auth.service.SecurityService;
import com.jamsearch.auth.service.UserService;
import com.jamsearch.auth.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @GetMapping({"/", "/welcome"})
    public String welcome(Model model, Principal principal) {

        model.addAttribute("user", userService.findByUsername(principal.getName()));
        return "welcome";
    }

    @PostMapping("/welcome")
    public String welcomeMessage(@ModelAttribute("userForm") User userForm, BindingResult bindingResult)  {

        return "/welcome";
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> users() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/searchpage")
    public String searchpage(Model model, Principal principal) {
        model.addAttribute("user", userService.findByUsername(principal.getName()));

        List<User> users = userService.findAll();
        model.addAttribute("users", users);

        return "searchpage";
    }

    @GetMapping("/messageCreation")
    public String messageCreation(Model model) {
        User userForm = new User();
        model.addAttribute("userForm", userForm);

        return "messageCreation";
    }

    @PutMapping("/messageCreation")
    public String messageCreation(@ModelAttribute("userForm") User userForm, Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("testBefore", userForm);
        model.addAttribute("testBeforeSave", user);
        user.setMessage(userForm.getMessage());
        userService.save(user);
        model.addAttribute("testAfterSave", user);

        return "redirect:/welcome";
    }

}
