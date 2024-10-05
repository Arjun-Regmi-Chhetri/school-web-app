package com.school.school.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "contact_msg")


@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "SqlResultSetMapping.count",
                columns = @ColumnResult(name = "cnt")
        )
})

@NamedQueries({
        @NamedQuery(name="Contact.findContactStatus", query ="SELECT c from Contact c WHERE c.status = :status2"),
        @NamedQuery(name="Contact.updateContactStatus2", query ="UPDATE Contact c SET c.status = :status, c.updatedAt = :updatedAt, c.updatedBy = :updatedBy WHERE c.contactId = :id")
})

@NamedNativeQueries({
        @NamedNativeQuery(
                name="Contact.findContactStatusNative",
                query = "SELECT * FROM contact_msg c WHERE c.status = :status",
                resultClass = Contact.class
        ),

        @NamedNativeQuery(
                name ="Contact.findContactStatusNative.count",
                query = "select count(*) as cnt from contact_msg c where c.status = :status",
                resultSetMapping = "SqlResultSetMapping.count"
        ),

        @NamedNativeQuery(
                name = "Contact.updateContactStatusNative",
                query = "UPDATE contact_msg c SET c.status = :status, c.updated_At = :updatedAt, c.updated_By = :updatedBy WHERE c.contact_id = :id",
                resultClass = Contact.class
        )
})

public class Contact extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int contactId;

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp="(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNum;

    @NotBlank(message = "Email is required")
    @Email(message = "Email is invalid")
    private String email;

    @NotBlank(message = "Subject is required")
    @Size(min = 5, max = 50, message = "Subject must be between 3 and 50 characters")
    private String subject;

    @NotBlank(message = "Message is required")
    @Size(min = 10, max = 500, message = "Message must be between 10 and 500 characters")
    private String message;

    private String status;
}