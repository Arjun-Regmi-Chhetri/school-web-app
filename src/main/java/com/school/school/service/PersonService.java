package com.school.school.service;

import com.school.school.constants.SchoolConstants;
import com.school.school.model.Person;
import com.school.school.model.Roles;
import com.school.school.repository.PersonRepository;
import com.school.school.repository.RolesRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonService {


    private static final Logger log = LoggerFactory.getLogger(PersonService.class);
    private final PersonRepository personRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PersonService(PersonRepository personRepository, RolesRepository rolesRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean savePerson(Person person) {

        boolean isPerson = false;


        // Check if email or mobile number already exists
        if (personRepository.readByEmail(person.getEmail()) != null) {
           log.info("Email already exists");
        }

//        if (personRepository.readByEmail(person.getMobileNumber())) {
//            log.info("Mobile number already exists");
//        }


            Roles role = rolesRepository.findByRoleName(SchoolConstants.STUDENT_ROLE);

            log.info("Role: " + role.toString());

            person.setRoles(role);
            person.setPassword(passwordEncoder.encode(person.getPassword()));
            person = personRepository.save(person);

            if (person.getPersonId() > 0) {
                isPerson = true;
            }

        return isPerson;
    }


}
