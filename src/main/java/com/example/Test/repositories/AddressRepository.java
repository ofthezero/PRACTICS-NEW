package com.example.Test.repositories;

import com.example.Test.models.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
    Address findByStreet(String street);
}
