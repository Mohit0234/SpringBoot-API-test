package com.example.Employee.Management.service;

import com.example.Employee.Management.entity.Department;
import com.example.Employee.Management.entity.Employee;
import com.example.Employee.Management.exception.ResourceNotFoundException;
import com.example.Employee.Management.respository.DepartmentRepository;
import com.example.Employee.Management.respository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * Service class that contains the business logic for managing employees.
 * This service interacts with EmployeeRepository and DepartmentRepository
 * to perform CRUD operations on employee data.
 */
@Service
public class EmployeeService {

    /**
     * Repository for accessing employee data from the database.
     */
    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Repository for accessing department data from the database.
     */
    @Autowired
    private DepartmentRepository departmentRepository;

    /**
     * Retrieves all employees from the database.
     *
     * @return a list of all employees
     */
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    /**
     * Retrieves an employee by their ID.
     *
     * @param id the ID of the employee
     * @return an Optional containing the employee if found, otherwise empty
     */
    public Optional<Employee> getEmployeeById(Long id){
        return employeeRepository.findById(id);
    }

    /**
     * Creates a new employee and assigns them to a department.
     * If the department doesn't exist, an exception will be thrown.
     *
     * @param employee the employee to create
     * @return the created employee
     * @throws RuntimeException if the department is not found
     */
    public Employee createEmployee(Employee employee) {

        Department department = departmentRepository.findById(employee.getDepartment().getId())
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + employee.getDepartment().getId()));

        employee.setDepartment(department);

        return employeeRepository.save(employee);
    }

    /**
     * Updates an existing employee's details.
     * If the employee or the department is not found, an exception will be thrown.
     *
     * @param id the ID of the employee to update
     * @param employeeDetails the new details to update the employee with
     * @return the updated employee
     * @throws ResourceNotFoundException if the employee or department is not found
     */
    public Employee updateEmployee(Long id, Employee employeeDetails) {

        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));

        existingEmployee.setName(employeeDetails.getName());
        existingEmployee.setEmail(employeeDetails.getEmail());
        existingEmployee.setJoiningDate(employeeDetails.getJoiningDate());

        Department department = departmentRepository.findById(employeeDetails.getDepartment().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + employeeDetails.getDepartment().getId()));

        existingEmployee.setDepartment(department);

        return employeeRepository.save(existingEmployee);
    }

    /**
     * Deletes an employee by their ID.
     *
     * @param id the ID of the employee to delete
     */
    public void deleteEmployee(Long id){
        employeeRepository.deleteById(id);
    }
}
