package com.holiday.finder.controller;

import com.holiday.finder.model.Authority;
import com.holiday.finder.model.User;
import com.holiday.finder.service.AuthorityService;
import com.holiday.finder.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AuthorityService authorityService;

    @GetMapping("/signup")
    public String showSignUp(Model model) {

        // If the user is authenticated redirect to homepage, else retrieve login page
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.getPrincipal().equals("anonymousUser")) {
            return "redirect:/";
        }

        model.addAttribute("newUser", new User());
        return "sign_up";
    }

    @PostMapping("/new")
    public String saveUser(@ModelAttribute("newUser") @Valid User newUser, BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {
            log.info("User form has errors!" + result.getAllErrors());
            return "sign_up";
        } else {
            log.info("Inserting new user");

            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String clearPass = newUser.getPassword();
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            newUser.setEnabled(1);
            userService.saveUser(newUser);

            Authority newAuthority = new Authority();
            newAuthority.setUsername(newUser.getUsername());
            newAuthority.setAuthority("USER");
            authorityService.saveAuthority(newAuthority);

            try {
                request.login(newUser.getUsername(), clearPass);
            } catch (ServletException e) {
                log.info("Error while autologin ", e);
            }

            return "redirect:/";
        }
    }
}
