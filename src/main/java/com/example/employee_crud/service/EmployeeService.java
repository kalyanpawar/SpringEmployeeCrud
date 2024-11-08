package com.example.employee_crud.service;

import com.example.employee_crud.entity.Employee;
import com.example.employee_crud.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return repository.findById(id);
    }

    public Employee findEmployeesByFirstName(String firstName) {
        return repository.findByFirstName(firstName);
    }

    public Employee findEmployeesByLastName(String lastName) {
        return repository.findByLastName(lastName);
    }

    public Employee findEmployeesByFullName(String firstName, String lastName) {
        return repository.findByFirstNameAndLastName(firstName, lastName);
    }

    public Employee createEmployee(Employee employee) {
        return repository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee employeeDetails) {
        return repository.findById(id)
                .map(employee -> {
                    employee.setFirstName(employeeDetails.getFirstName());
                    employee.setLastName(employeeDetails.getLastName());
                    employee.setAge(employeeDetails.getAge());
                    employee.setSalary(employeeDetails.getSalary());
                    employee.setDateOfJoining(employeeDetails.getDateOfJoining());
                    employee.setDob(employeeDetails.getDob());
                    employee.setEmails(employeeDetails.getEmails());
                    return repository.save(employee);
                })
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }
}

