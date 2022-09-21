package com.example.Test.repositories;
import com.example.Test.models.Komnati;
import com.example.Test.models.Trailers;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface KomnatiRepository extends CrudRepository<Komnati, Long> {

    public List<Komnati> findByNomerKomnatiContains(String nomerKomnati);
}