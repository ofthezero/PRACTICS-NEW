package com.example.Test.repositories;

import com.example.Test.models.University;
import org.springframework.data.repository.CrudRepository;

public interface UniversityRepository extends CrudRepository<University, Long> {
    University findByName(String name);
}
