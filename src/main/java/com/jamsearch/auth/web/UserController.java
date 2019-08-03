package com.jamsearch.auth.web;

import com.jamsearch.auth.model.Comment;
import com.jamsearch.auth.model.Post;
import com.jamsearch.auth.model.User;
import com.jamsearch.auth.repository.PostRepository;
import com.jamsearch.auth.service.SecurityService;
import com.jamsearch.auth.service.UserService;
import com.jamsearch.auth.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
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

    @GetMapping("/searchpage")
    public String searchpage(Model model, Principal principal) {
        model.addAttribute("user", userService.findByUsername(principal.getName()));

        List<User> users = userService.findAll();
        model.addAttribute("users" ,users);

        return "searchpage";
    }

}
