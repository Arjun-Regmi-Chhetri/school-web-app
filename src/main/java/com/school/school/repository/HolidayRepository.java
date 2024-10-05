package com.school.school.repository;

import com.school.school.model.Holiday;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface HolidayRepository extends CrudRepository<Holiday, Integer> {



}
