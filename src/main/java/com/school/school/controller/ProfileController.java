package com.school.school.controller;

import com.school.school.model.Address;
import com.school.school.model.Person;
import com.school.school.model.Profile;
import com.school.school.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProfileController {

    private static final Logger log = LoggerFactory.getLogger(ProfileController.class);
    private final PersonRepository personRepository;

    @Autowired
    public ProfileController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @RequestMapping("/displayProfile")
    public ModelAndView displayProfile(Model model, HttpSession session){
        Person person = (Person) session.getAttribute("loggedIn");
        if (person == null) {
            return new ModelAndView("redirect:/login");
        }
        Profile profile = new Profile();
        profile.setName(person.getName());
        profile.setEmail(person.getEmail());
        profile.setMobileNumber(person.getMobileNumber());
        if(person.getAddress() != null && person.getAddress().getAddressId() > 0){
            profile.setAddress1(person.getAddress().getAddress1());
            profile.setAddress2(person.getAddress().getAddress2());
            profile.setCity(person.getAddress().getCity());
            profile.setState(person.getAddress().getState());
            profile.setZipCode(person.getAddress().getZipCode());
        }
        ModelAndView modelAndView = new ModelAndView("profile");
        modelAndView.addObject("profile", profile);
        return modelAndView;
    }

    @PostMapping(value = "/updateProfile")
    public String updateProfile(@Valid @ModelAttribute("profile") Profile profile, Errors errors, HttpSession session){
        if(errors.hasErrors()){
            return "profile";
        }
        Person person = (Person) session.getAttribute("loggedIn");
        if (person == null) {
            return "redirect:/login";
        }
        person.setName(profile.getName());
        person.setEmail(profile.getEmail());
        person.setMobileNumber(profile.getMobileNumber());
        if(person.getAddress() == null){
            person.setAddress(new Address());
        }
        person.setUpdatedBy(person.getName());
        person.getAddress().setAddress1(profile.getAddress1());
        person.getAddress().setAddress2(profile.getAddress2());
        person.getAddress().setCity(profile.getCity());
        person.getAddress().setState(profile.getState());
        person.getAddress().setZipCode(profile.getZipCode());

        log.info("Saving person with address1: {}", person.getAddress().getAddress1());

        Person savedPerson = personRepository.save(person);
        session.setAttribute("loggedIn", savedPerson);
        return "redirect:/displayProfile";
    }
}