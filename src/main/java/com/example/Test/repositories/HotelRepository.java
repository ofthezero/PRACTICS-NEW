package com.example.Test.repositories;
import com.example.Test.models.Hotel;
import com.example.Test.models.Trailers;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface HotelRepository extends CrudRepository<Hotel, Long> {

    public List<Hotel> findByStranaContains(String strana);
}