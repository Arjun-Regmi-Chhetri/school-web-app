package com.school.school.model;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Profile {

    @NotBlank(message="Name must not be blank")
    @Size(min=3, message="Name must be at least 3 characters long")
    private String name;

    @NotBlank(message="Mobile number must not be blank")
    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @NotBlank(message="Email must not be blank")
    @Email(message = "Please provide a valid email address" )
    private String email;

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
