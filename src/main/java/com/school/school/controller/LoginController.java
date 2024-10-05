package com.school.school.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {


    @RequestMapping(value="/login", method = {RequestMethod.GET, RequestMethod.POST})
    public String login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            @RequestParam(value = "register", required = false) String register,
            Model model
    ){
        String errorMessage = null;
        String successMessage = null;

        if (error != null) {
            errorMessage = "Username or Password is incorrect !!";
        }

        if (logout != null) {
            successMessage = "You have been successfully logged out !!";
        }

        if (register != null) {
            successMessage = "You have been successfully registered !!";
        }


        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("successMessage", successMessage);


        return "login";
    }


    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logout(Authentication authentication, HttpServletRequest request, HttpServletResponse response){

        if(authentication != null){
             new SecurityContextLogoutHandler().logout(request, response, authentication);
        }

        return "redirect:/login?logout=true";
    }


}
