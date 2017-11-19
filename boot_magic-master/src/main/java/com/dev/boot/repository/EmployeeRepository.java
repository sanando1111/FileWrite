package com.dev.boot.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.dev.boot.entity.Employee;

@RepositoryRestResource
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    List<Employee> findByLastName(@Param("lastName") String lastName);

}
