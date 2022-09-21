package com.example.Test.repositories;
import com.example.Test.models.Cource;
import com.example.Test.models.Dolznost;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface DolznostRepository extends CrudRepository<Dolznost, Long> {

    public List<Dolznost> findByNameDolznostiContains(String nameDolznosti);
}


