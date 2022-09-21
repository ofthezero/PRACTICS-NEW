package com.example.Test.repositories;
import com.example.Test.models.Trailers;
import com.example.Test.models.TypeKomnati;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface TypeKomnatiRepository extends CrudRepository<TypeKomnati, Long> {

    public List<TypeKomnati> findByNameTContains(String nameT);
}