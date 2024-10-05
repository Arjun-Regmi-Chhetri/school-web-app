package com.school.school.controller;


import com.school.school.model.Person;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/student")
public class StudentController {


    @GetMapping("/displayCourses")
    public ModelAndView displayCourses(HttpSession session){

        Person person = (Person) session.getAttribute("loggedIn");
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("person", person);

        modelAndView.setViewName("courses_enrolled");

        return modelAndView;
    }

}
