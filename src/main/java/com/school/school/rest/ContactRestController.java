package com.school.school.rest;

import com.school.school.constants.SchoolConstants;
import com.school.school.model.Contact;
import com.school.school.model.Response;
import com.school.school.repository.ContactRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/contact", produces = {"application/json", "application/xml"})
@CrossOrigin("*")
public class ContactRestController {


    private static final Logger log = LoggerFactory.getLogger(ContactRestController.class);
    private final ContactRepository contactRepository;

    public ContactRestController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @GetMapping("/getContactByStatus")
    public List<Contact> getContact(@RequestParam("status") String status){
        return contactRepository.findByStatus(status);
    }

    @GetMapping("/getAllContactByStatus")
    public List<Contact> getAllContactByStatus(@RequestBody Contact contact){
        if(contact.getStatus() != null){
            return contactRepository.findByStatus(contact.getStatus());
        }else{
            return (List<Contact>) contactRepository.findAll();
        }
    }

    @GetMapping("getAllContact")
    public ResponseEntity<List<Contact>> getAllContact(){
        return ResponseEntity.ok((List<Contact>) contactRepository.findAll());
    }



    @PostMapping("/saveContact")
    public ResponseEntity<Response> saveContact(@RequestHeader("invokeFrom") String invokeFrom,
                                                @Valid @RequestBody Contact contact){

        log.info("Invoke from {}" , invokeFrom);

        contactRepository.save(contact);

        Response response = new Response();
        response.setStatusCode("200");
        response.setStatusMsg("Message saved successfully");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("isMessageSaved", "true")
                .body(response);





    }



    @DeleteMapping("/deleteContact")
    public ResponseEntity<Response> deleteContact(RequestEntity<Contact> requestEntity) {

        HttpHeaders httpHeaders = requestEntity.getHeaders();
        httpHeaders.forEach((key, value) -> {
            log.info(String.format(
                    "Header '%s' = %s", key, String.join("|", value)));
        });

        Contact contact = requestEntity.getBody();

        if (contact != null) {
            contactRepository.deleteById(contact.getContactId());
        }

        Response response = new Response();
        response.setStatusCode("200");
        response.setStatusMsg("Message deleted successfully");

        return ResponseEntity
                .status(HttpStatus.OK)
                .header("isMessageDeleted", "true")
                .body(response);

    }


    @PatchMapping("/closeContact")
    public ResponseEntity<Response> closeContact(@RequestBody Contact contact, Authentication authentication) {

        Response response = new Response();
        Optional<Contact> contactOptional = contactRepository.findById(contact.getContactId());

        if(contactOptional.isPresent()){
            Contact contact1 = contactOptional.get();
            contact1.setStatus(SchoolConstants.CLOSE);
            contact1.setUpdatedAt(LocalDateTime.now());
            contact1.setUpdatedBy(authentication.getName());
            contactRepository.save(contact1);
        }else {
            response.setStatusCode("404");
            response.setStatusMsg("Message not found");
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .header("isMessageClosed", "false")
                    .body(response);
        }

        response.setStatusCode("200");
        response.setStatusMsg("Message closed successfully");
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("isMessageClosed", "true")
                .body(response);

    }

}
