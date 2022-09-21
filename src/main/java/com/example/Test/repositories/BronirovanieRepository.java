package com.example.Test.repositories;
import com.example.Test.models.Bronirovanie;
import com.example.Test.models.Trailers;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface BronirovanieRepository extends CrudRepository<Bronirovanie, Long> {

    public List<Bronirovanie> findByDataContains(String data);
}
