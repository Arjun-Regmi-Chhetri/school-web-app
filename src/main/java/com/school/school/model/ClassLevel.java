package com.school.school.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity

public class ClassLevel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int classId;

    @NotBlank(message = "ClassLevel name is mandatory")
    private String className;

    @OneToMany(mappedBy = "classes", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, targetEntity = Person.class)
    private Set<Person> persons;

}
