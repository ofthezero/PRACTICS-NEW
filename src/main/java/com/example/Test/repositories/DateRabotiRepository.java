package com.example.Test.repositories;
import com.example.Test.models.DateRaboti;
import com.example.Test.models.Trailers;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface DateRabotiRepository extends CrudRepository<DateRaboti, Long> {

    public List<DateRaboti> findByDaysRabotiContains(String daysRaboti);
}