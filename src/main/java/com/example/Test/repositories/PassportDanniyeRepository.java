package com.example.Test.repositories;


import com.example.Test.models.Passportd;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PassportDanniyeRepository extends CrudRepository<Passportd, Long> {

    public List<Passportd> findBySeriaPassporta(String seriaPassporta);
}