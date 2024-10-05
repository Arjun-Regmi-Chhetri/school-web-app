package com.school.school.repository;


import com.school.school.model.Contact;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface ContactRepository extends CrudRepository<Contact, Integer> {

    List<Contact> findByStatus(String status);


    @Query("SELECT c from Contact c WHERE c.status = :status")
//    @Query(value = "SELECT * FROM contact_msg c WHERE c.status = :status", nativeQuery = true)
    Page<Contact> findByStatus(@Param("status") String status, Pageable pageable);


    @Transactional
    @Modifying
    @Query("UPDATE Contact c SET c.status = :status, c.updatedAt = :updatedAt, c.updatedBy = :updatedBy WHERE c.contactId = :id")
    int updateContactStatus(@Param("id") int id, @Param("status") String status, @Param("updatedAt") LocalDateTime updatedAt, @Param("updatedBy") String updatedBy);



    Page<Contact> findContactStatus(@Param("status2") String status, Pageable pageable);
    @Transactional
    @Modifying
    int updateContactStatus2(@Param("id") int id, @Param("status") String status, @Param("updatedAt") LocalDateTime updatedAt, @Param("updatedBy") String updatedBy);


    @Query(nativeQuery = true)
    Page<Contact> findContactStatusNative(@Param("status") String status, Pageable pageable);

    @Transactional
    @Modifying
    @Query(nativeQuery = true)
    int updateContactStatusNative(@Param("id") int id, @Param("status") String status, @Param("updatedAt") LocalDateTime updatedAt, @Param("updatedBy") String updatedBy);
}
