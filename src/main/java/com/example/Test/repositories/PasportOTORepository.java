package com.example.Test.repositories;

import com.example.Test.models.PasportOTO;
import org.springframework.data.repository.CrudRepository;

public interface PasportOTORepository extends CrudRepository<PasportOTO, Long> {
    PasportOTO findByNumber(String number);
}
