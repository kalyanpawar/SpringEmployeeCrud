package com.example.employee_crud.repository;

import com.example.employee_crud.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByFirstName(String firstName);

    Employee findByLastName(String lastName);

    // Optionally, you could add a method to search by both first and last name
    Employee findByFirstNameAndLastName(String firstName, String lastName);
}

