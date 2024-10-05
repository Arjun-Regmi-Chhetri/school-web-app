package com.school.school.controller;


import com.school.school.model.ClassLevel;
import com.school.school.model.Courses;
import com.school.school.model.Person;
import com.school.school.repository.ClassRepository;
import com.school.school.repository.CoursesRepository;
import com.school.school.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
@Slf4j
public class AdminController {

    private final ClassRepository classRepository;
    private final PersonRepository personRepository;
    private final CoursesRepository coursesRepository;

    @Autowired
    public AdminController(ClassRepository classRepository, PersonRepository personRepository, CoursesRepository coursesRepository) {
        this.classRepository = classRepository;
        this.personRepository = personRepository;
        this.coursesRepository = coursesRepository;
    }

    @GetMapping("/displayClasses")
    public ModelAndView displayClasses(Model model){
        List<ClassLevel> classList = classRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("classes");
        modelAndView.addObject("classList", classList);
        modelAndView.addObject("classLevel", new ClassLevel());
        return modelAndView;
    }


    @RequestMapping("/addNewClass")
    public ModelAndView addClasses(Model model, @ModelAttribute("classLevel") ClassLevel classLevel){
        classRepository.save(classLevel);
        return new ModelAndView("redirect:/admin/displayClasses");
    }

    @RequestMapping("/deleteClass")
    public ModelAndView deleteClass(Model model,@RequestParam int id){

        Optional<ClassLevel> classLevelOptional = classRepository.findById(id);

        if(classLevelOptional.isPresent()){
            ClassLevel classLevel = classLevelOptional.get();
            for(Person person: classLevel.getPersons()){
                person.setClasses(null);
                personRepository.save(person);
            }
            classRepository.deleteById(id);
        }

        return new ModelAndView("redirect:/admin/displayClasses");
    }


    @GetMapping("/displayStudents")
    public ModelAndView displayStudents(Model model, @RequestParam int classId, HttpSession session,
                                        @RequestParam(value = "error", required = false) String error,
                                        @RequestParam(value = "deleted", required = false) String deleted){
        String errorMessage = null;
        String successMessage = null;
        ModelAndView modelAndView = new ModelAndView("students");
        Optional<ClassLevel> classLevelOptional = classRepository.findById(classId);

        if (classLevelOptional.isPresent()){


            modelAndView.addObject("classLevel", classLevelOptional.get());
            modelAndView.addObject("person", new Person());
            session.setAttribute("classLevel", classLevelOptional.get());

        }


        if(error != null){
            errorMessage = "Invalid email entered";
            modelAndView.addObject("errorMessage", errorMessage);
        }

        if(deleted != null){
            successMessage = "Student deleted successfully";
            modelAndView.addObject("successMessage", successMessage);
        }


        return modelAndView;
    }


    @PostMapping("/addStudent")
    public ModelAndView addStudent(@ModelAttribute("person") Person person, HttpSession session){

        ModelAndView modelAndView = new ModelAndView();

        ClassLevel classLevel = (ClassLevel) session.getAttribute("classLevel");

        log.info("Class level: {}", classLevel);

       Person personEntity = personRepository.readByEmail(person.getEmail());

       if(personEntity == null){
           modelAndView.setViewName("redirect:/admin/displayStudents?classId="+classLevel.getClassId()+"&error=true");
           return modelAndView;
       }

       personEntity.setClasses(classLevel);
       personRepository.save(personEntity);

       classLevel.getPersons().add(personEntity);
       classRepository.save(classLevel);

       modelAndView.setViewName("redirect:/admin/displayStudents?classId="+classLevel.getClassId());

         return modelAndView;




    }


    @RequestMapping("/deleteStudent")   //removing student from the class, it will not remove entire student from the database
    public ModelAndView modelAndView(@RequestParam int personId, HttpSession session){
        ClassLevel classLevel = (ClassLevel) session.getAttribute("classLevel");
        Optional<Person> person = personRepository.findById(personId);

        if(person.isPresent()){
            person.get().setClasses(null);
            personRepository.save(person.get());
            classLevel.getPersons().remove(person.get());
            classRepository.save(classLevel);
        }

        session.setAttribute("classLevel", classLevel);
        return new ModelAndView("redirect:/admin/displayStudents?classId="+classLevel.getClassId()+"&deleted=true");


    }



    @GetMapping("/displayCourses")
    public ModelAndView displayCourses(Model model,
                                       @RequestParam(value = "success", required = false) String success,
                                       @RequestParam(value = "deleteCourses", required = false) String deleteCourses){

        String successMessage = null;

//        List<Courses> courses = coursesRepository.findAll();

//        List<Courses> courses = coursesRepository.findByOrderByName();

//        List<Courses> courses = coursesRepository.findByOrderByNameDesc();


//        dynamic sorting

//        List<Courses> courses = coursesRepository.findAll(Sort.by("name").descending().and(Sort.by("fees").ascending()));


        List<Courses> courses = coursesRepository.findAll(Sort.by("fees").ascending());

        ModelAndView modelAndView = new ModelAndView("courses_secure");

        modelAndView.addObject("courses", courses);

        modelAndView.addObject("course", new Courses());

        if (success != null){
            successMessage = "Course added successfully";
            modelAndView.addObject("successMessage", successMessage);
        }

        if (deleteCourses != null){
            successMessage = "Course deleted successfully";
            modelAndView.addObject("successMessage", successMessage);
        }

        return modelAndView;
    }

    @PostMapping("/addNewCourse")
    public ModelAndView addCourse(@ModelAttribute("course") Courses course){
        coursesRepository.save(course);
        return new ModelAndView("redirect:/admin/displayCourses?success=true");
    }

    @RequestMapping("/deleteCourse")
    public ModelAndView deleteCourse(@RequestParam int id){
        Optional<Courses> course = coursesRepository.findById(id);

        if(course.isPresent()){
            Courses courses = course.get();
            for(Person person: courses.getPersons()){
                person.setCourses(null);
                personRepository.save(person);
            }

            coursesRepository.deleteById(id);
        }

        return new ModelAndView("redirect:/admin/displayCourses?deleteCourses=true");
    }



    @GetMapping("/viewStudents")
    public ModelAndView viewStudents(Model model,@RequestParam int id, HttpSession session,
                                     @RequestParam(value = "error", required = false) String error,
                                     @RequestParam(value = "success", required = false) String success,
                                     @RequestParam(value = "exist", required = false) String exist,
                                     @RequestParam(value = "deleted", required = false) String deleted){

        String errorMessage = null;
        String successMessage = null;
        String warningMessage = null;
        ModelAndView modelAndView = new ModelAndView("courses_student");
        Optional<Courses> coursesOptional = coursesRepository.findById(id);

        if(coursesOptional.isPresent()){
            Courses courses = coursesOptional.get();
            modelAndView.addObject("courses", courses);
            modelAndView.addObject("person", new Person());
            session.setAttribute("courses", courses);
        }

        if(error != null){
            errorMessage = "Invalid email entered";
            modelAndView.addObject("errorMessage", errorMessage);
        }

        if (success != null){
            successMessage = "Student added successfully";
            modelAndView.addObject("successMessage", successMessage);
        }

        if (exist != null){
            warningMessage = "Student already exists";
            modelAndView.addObject("warningMessage", warningMessage);
        }

        if (deleted != null){
            successMessage = "Student deleted successfully";
            modelAndView.addObject("successMessage", successMessage);
        }


        return modelAndView;
    }

    @PostMapping("/addStudentToCourse")
    public ModelAndView addStudentToCourse(@ModelAttribute("person") Person person, HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        Courses courses = (Courses) session.getAttribute("courses");

        log.info("Courses level: {}", courses);

        Person personOptional = personRepository.readByEmail(person.getEmail());

        if(personOptional == null){
            modelAndView.setViewName("redirect:/admin/viewStudents?id="+courses.getCourseId()+"&error=true");
            return modelAndView;
        }

        if(courses.getPersons().contains(personOptional)){
            modelAndView.setViewName("redirect:/admin/viewStudents?id="+courses.getCourseId()+"&exist=true");
            return modelAndView;
        }

        personOptional.getCourses().add(courses);
        personRepository.save(personOptional);


        courses.getPersons().add(personOptional);
        coursesRepository.save(courses);

        modelAndView.setViewName("redirect:/admin/viewStudents?id="+courses.getCourseId()+"&success=true");
        return modelAndView;




    }


    @RequestMapping("/deleteStudentFromCourse")
    public ModelAndView deleteStudent(@RequestParam int personId, HttpSession session){

        ModelAndView modelAndView = new ModelAndView();

        Courses courses = (Courses) session.getAttribute("courses");

        Optional<Person> personOptional = personRepository.findById(personId);

        if (personOptional.isPresent()){
           personOptional.get().setCourses(null);
           personRepository.save(personOptional.get());

           courses.getPersons().remove(personOptional.get());
              coursesRepository.save(courses);

        }

        session.setAttribute("courses", courses);

        modelAndView.setViewName("redirect:/admin/viewStudents?id="+courses.getCourseId()+"&deleted=true");

        return modelAndView;


    }



}
