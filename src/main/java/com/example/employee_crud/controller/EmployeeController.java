package com.example.employee_crud.controller;

import com.example.employee_crud.entity.Employee;
import com.example.employee_crud.service.EmployeeService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utility.ExcelGenerator;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return service.getAllEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return service.getEmployeeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/findByFirstName")
    public Employee getEmployeesByFirstName(@RequestParam String firstName) {
        return service.findEmployeesByFirstName(firstName);
    }

    @GetMapping("/findByLastName")
    public Employee getEmployeesByLastName(@RequestParam String lastName) {
        return service.findEmployeesByLastName(lastName);
    }

    @GetMapping("/findByFullName")
    public Employee getEmployeesByFullName(@RequestParam String firstName, @RequestParam String lastName) {
        return service.findEmployeesByFullName(firstName, lastName);
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(service.createEmployee(employee), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        return new ResponseEntity<>(service.updateEmployee(id, employee), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        service.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/export/excel")
    public ResponseEntity<byte[]> exportToExcel() {
        List<Employee> employees = service.getAllEmployees();
        ByteArrayInputStream excelFile = ExcelGenerator.employeesToExcel(employees);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=employees.xlsx");

        try {
            byte[] excelBytes = excelFile.readAllBytes();  // Read properly the ByteArrayInputStream
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                    .body(excelBytes);
        }catch(Exception ioex){
            // Handle in case anything misread
            throw new RuntimeException("Error streaming Excel file response: "+ ioex);
        }
    }
}

