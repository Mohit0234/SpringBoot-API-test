package com.example.Employee.Management.service;

import com.example.Employee.Management.entity.Department;
import com.example.Employee.Management.respository.DepartmentRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service class that provides business logic for managing departments.
 * This service interacts with the DepartmentRepository to perform CRUD operations on the 'departments' table.
 */
@Service
public class DepartmentService {

    /**
     * Repository for accessing department data from the database.
     */
    @Autowired
    private DepartmentRepository departmentRepository;

    /**
     * Initializes the departments by adding default departments if the repository is empty.
     * This method is called after the service bean is created and injected.
     */
    @PostConstruct
    public void initDepartments(){
        if(departmentRepository.count() == 0) {
            Department department1 = new Department();
            department1.setName("HR");

            Department department2 = new Department();
            department2.setName("Finance");

            Department department3 = new Department();
            department3.setName("Engineering");

            departmentRepository.save(department1);
            departmentRepository.save(department2);
            departmentRepository.save(department3);
        }
    }

    /**
     * Retrieves all departments from the database.
     *
     * @return a list of all departments
     */
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }
}
