package com.school.school.controller;

import com.school.school.model.Person;
import com.school.school.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/public")
public class PublicController {

    private final PersonService personService;

    @Autowired
    public PublicController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(value ="/register")
    public String displayRegisterPage(Model model) {
        model.addAttribute("person", new Person());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("person") Person person, Errors errors) {
        if (errors.hasErrors()) {
            return "register";
        }
        personService.savePerson(person);
        return "redirect:/login?register=true";
    }
}