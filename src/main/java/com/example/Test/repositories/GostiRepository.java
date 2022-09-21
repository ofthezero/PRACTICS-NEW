package com.example.Test.repositories;
import com.example.Test.models.Gosti;
import com.example.Test.models.Trailers;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface GostiRepository extends CrudRepository<Gosti, Long> {

    public List<Gosti> findByFamiliaContains(String familia);
}