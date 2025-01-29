package com.example.Employee.Management.controller;

import com.example.Employee.Management.entity.Employee;
import com.example.Employee.Management.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * This controller handles HTTP requests related to employee management.
 * It exposes endpoints for creating, updating, deleting, and retrieving employee details.
 */
@RestController
@RequestMapping("/employees")  // Maps all incoming requests to "/employees" URL
public class EmployeeController {

    // Autowired service to handle business logic for employee operations
    @Autowired
    private EmployeeService employeeService;

    /**
     * Retrieves a list of all employees.
     *
     * @return a ResponseEntity containing a list of all employees or a 204 No Content response if no employees are found.
     */
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();

        // If no employees are found, return a "No Content" response (HTTP 204)
        if (employees.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        // If employees are found, return them with an HTTP 200 OK status
        return ResponseEntity.ok(employees);
    }

    /**
     * Retrieves an employee by their ID.
     *
     * @param id the ID of the employee to retrieve
     * @return a ResponseEntity containing the employee if found or a 404 Not Found response if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);

        // If employee is found, return the employee details with an HTTP 200 OK status
        if (employee.isPresent()) {
            return ResponseEntity.ok(employee.get());
        }

        // If employee is not found, return a "Not Found" response (HTTP 404)
        return ResponseEntity.notFound().build();
    }

    /**
     * Creates a new employee.
     *
     * @param employee the employee details to create
     * @return a ResponseEntity containing the created employee details with an HTTP 200 OK status or a 400 Bad Request if the input is invalid
     */
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        if (employee == null) {
            // If the provided employee is null, return a "Bad Request" response (HTTP 400)
            return ResponseEntity.badRequest().build();
        }

        Employee createdEmployee = employeeService.createEmployee(employee);
        return ResponseEntity.ok(createdEmployee);
    }

    /**
     * Updates an existing employee's details.
     *
     * @param id the ID of the employee to update
     * @param employee the updated employee details
     * @return a ResponseEntity containing the updated employee details with an HTTP 200 OK status or a 404 Not Found if the employee doesn't exist
     */
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        // Check if the employee exists before updating
        if (!employeeService.getEmployeeById(id).isPresent()) {
            // If employee doesn't exist, return a "Not Found" response (HTTP 404)
            return ResponseEntity.notFound().build();
        }

        Employee updatedEmployee = employeeService.updateEmployee(id, employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    /**
     * Deletes an employee by their ID.
     *
     * @param id the ID of the employee to delete
     * @return a ResponseEntity with a success message if the deletion was successful, or a 404 Not Found if the employee doesn't exist
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        // Check if the employee exists before deleting
        if (!employeeService.getEmployeeById(id).isPresent()) {
            // If employee doesn't exist, return a "Not Found" response (HTTP 404)
            return ResponseEntity.notFound().build();
        }

        employeeService.deleteEmployee(id);
        // Return a success message after successful deletion
        return ResponseEntity.ok("Employee deleted successfully");
    }
}
