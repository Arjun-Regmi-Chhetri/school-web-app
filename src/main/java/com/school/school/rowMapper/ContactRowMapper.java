package com.school.school.rowMapper;

import com.school.school.model.Contact;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactRowMapper implements RowMapper<Contact> {

    @Override
    public Contact mapRow(ResultSet row, int rowNum) throws SQLException {
        Contact contact = new Contact();
        contact.setContactId(row.getInt("CONTACT_ID"));
        contact.setName(row.getString("NAME"));
        contact.setMobileNum(row.getString("MOBILE_NUM"));
        contact.setEmail(row.getString("EMAIL"));
        contact.setSubject(row.getString("SUBJECT"));
        contact.setMessage(row.getString("MESSAGE"));
        contact.setStatus(row.getString("STATUS"));
        contact.setCreatedAt(row.getTimestamp("CREATED_AT").toLocalDateTime());
        contact.setCreatedBy(row.getString("CREATED_BY"));

        if (row.getTimestamp("UPDATED_AT") != null) {
            contact.setUpdatedAt(row.getTimestamp("UPDATED_AT").toLocalDateTime());
        }

        contact.setUpdatedBy(row.getString("UPDATED_BY"));
        return contact;
    }
}