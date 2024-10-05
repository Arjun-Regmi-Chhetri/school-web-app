package com.school.school.service;


import com.school.school.constants.SchoolConstants;
import com.school.school.model.Contact;
import com.school.school.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ContactService {

//    private final Logger logger = Logger.getLogger(ContactService.class.getName());

    @Autowired
    private ContactRepository contactRepository;

    public boolean saveContact(Contact contact){
        boolean isSaved = false;
        contact.setStatus(SchoolConstants.OPEN);
        contact.setCreatedBy(SchoolConstants.ANONYMOUS);
        contact.setCreatedAt(LocalDateTime.now());
        log.info(contact.toString());

        Contact result = contactRepository.save(contact);
        if(result.getContactId() > 0){
            isSaved = true;
        }
        return isSaved;
    }



    public Page<Contact> findMsgsWithOpenStatus(int pageNum, String sortField, String sortDir) {
        Pageable pageable = PageRequest.of(pageNum - 1, 5, sortDir.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending());
        return contactRepository.findContactStatusNative(SchoolConstants.OPEN, pageable);
    }


    public boolean updateMessage(int id, String name) {

        boolean isUpdate = false;
//        Optional<Contact> contact = contactRepository.findById(id);
//
//        if (contact.isPresent()) {
//            Contact value = contact.get();
//            value.setUpdatedBy(name);
//            value.setUpdatedAt(LocalDateTime.now());
//            value.setStatus(SchoolConstants.CLOSE);
//            Contact contact1 = contactRepository.save(value);
//            if (contact1.getUpdatedBy() != null) {
//
//                isUpdate = true;
//            }
//            }

        int updatedRows = contactRepository.updateContactStatusNative(id, SchoolConstants.CLOSE, LocalDateTime.now(), name);
        if (updatedRows > 0) {
            isUpdate = true;
        }



        return isUpdate;

        }


}
