package com.school.school.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Address extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int addressId;

    @NotBlank(message = "Address is mandatory")
    @Size(min = 5,message = "Address must be 5 characters and more")
    private String address1;

    private String address2;

    @NotBlank(message = "City is mandatory")
    @Size(min = 3,message = "City must be 3 characters and more")
    private String city;

    @NotBlank(message = "State is mandatory")
    @Size(min = 3,message = "State must be 3 characters and more")
    private String state;

    @NotBlank(message = "Zip code is mandatory")
    @Pattern(regexp = "^[0-9]{4,6}?$",message = "Zip code must be 4-6 digits")
    private String zipCode;

}
