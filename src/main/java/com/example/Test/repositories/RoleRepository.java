package com.example.Test.repositories;
import com.example.Test.models.RoleName;
import com.example.Test.models.Trailers;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface RoleRepository extends CrudRepository<RoleName, Long> {

    public List<RoleName> findByNameRoleContains(String nameRole);
}