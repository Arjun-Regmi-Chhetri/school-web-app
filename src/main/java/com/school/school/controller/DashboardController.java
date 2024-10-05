package com.school.school.controller;

import com.school.school.model.Person;
import com.school.school.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {

    private static final Logger log = LoggerFactory.getLogger(DashboardController.class);
    @Autowired
    private PersonRepository personRepository;

    @Transactional
    @RequestMapping("/dashboard")
    public String dashboard(Authentication authentication, Model model, HttpSession session){


        Person person = personRepository.readByEmail(authentication.getName());

        log.info("Person: {}", person.toString());

        model.addAttribute("username", person.getName());
        model.addAttribute("authorities", authentication.getAuthorities());

        if(null != person.getClasses() && null != person.getClasses().getClassName()){
            model.addAttribute("enrolledClass", person.getClasses().getClassName());
        }

        session.setAttribute("loggedIn", person);
//        throw new RuntimeException("This is a runtime exception");
        return "dashboard";
    }

}
