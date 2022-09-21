package com.example.Test.repositories;
import com.example.Test.models.Trailers;
import com.example.Test.models.Type_pitanie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface TypePitanieRepository extends CrudRepository<Type_pitanie, Long> {

    public List<Type_pitanie> findByNameContains(String name);
}