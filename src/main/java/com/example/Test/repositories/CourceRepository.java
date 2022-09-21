package com.example.Test.repositories;
import com.example.Test.models.Cource;
import com.example.Test.models.Trailers;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface CourceRepository extends CrudRepository<Cource, Long> {

    public List<Cource> findByNameCourceContains(String nameCource);
}


