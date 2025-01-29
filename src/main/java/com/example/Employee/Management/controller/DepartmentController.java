package com.example.Employee.Management.controller;

import com.example.Employee.Management.entity.Department;
import com.example.Employee.Management.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This controller handles requests related to departments.
 * It exposes the API endpoint to retrieve all departments from the system.
 */
@RestController
@RequestMapping("/departments")  // Maps all incoming requests to "/departments" URL
public class DepartmentController {

    // Autowired service to handle the business logic related to departments
    @Autowired
    private DepartmentService departmentService;

    /**
     * This method retrieves all the departments.
     *
     * @return a ResponseEntity containing a list of all departments or a 204 No Content response if no departments are found.
     */
    @GetMapping  // Maps GET requests for retrieving departments
    public ResponseEntity<List<Department>> getAllDepartments() {
        // Fetching all departments from the service
        List<Department> departments = departmentService.getAllDepartments();

        // If no departments are found, return a "No Content" response (HTTP 204)
        if (departments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        // If departments are found, return them with an HTTP 200 OK status
        return ResponseEntity.ok(departments);
    }
}
