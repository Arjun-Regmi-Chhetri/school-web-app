package com.school.school.controller;


import com.school.school.model.Contact;
import com.school.school.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

//import java.util.logging.Logger;

@Controller
public class ContactController {

//    private final Logger logger = Logger.getLogger(ContactRestController.class.getName());


    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @RequestMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact";
    }

/*
 @PostMapping("/saveContact")
    public ModelAndView contact(@RequestParam String name, @RequestParam String mobileNum,
                                @RequestParam String email, @RequestParam String subject, @RequestParam String message){


        logger.info("Name: " + name);
        logger.info("Mobile Number: " + mobileNum);
        logger.info("Email: " + email);
        logger.info("Subject: " + subject);
        logger.info("Message: " + message);

        return new ModelAndView("redirect:/contact");


    }*/

    @PostMapping("/saveContact")
    public String contact(@Valid @ModelAttribute("contact") Contact contact, Errors error) {

        if (error.hasErrors()) {
            return "contact";
        }

        contactService.saveContact(contact);
        return "redirect:/contact";
    }

    @RequestMapping("/displayMessages/page/{pageNum}")
    public ModelAndView displayMessages(Model model, @PathVariable(name = "pageNum") int pageNum, @RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir) {
        Page<Contact> contacts = contactService.findMsgsWithOpenStatus(pageNum, sortField, sortDir);

        List<Contact> contactList = contacts.getContent();

        ModelAndView modelAndView = new ModelAndView("messages");
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", contacts.getTotalPages());
        model.addAttribute("totalItems", contacts.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        modelAndView.addObject("contactList", contactList);
        return modelAndView;
    }

    @RequestMapping(value = "/closeMessage", method = RequestMethod.GET)
    public String closeMessage(@RequestParam int id, Authentication authentication) {
        contactService.updateMessage(id, authentication.getName());
        return "redirect:/displayMessages/page/1?sortField=createdAt&sortDir=desc";
    }


}
