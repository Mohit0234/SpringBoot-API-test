package com.example.Employee.Management.respository;

import com.example.Employee.Management.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for accessing Employee entities.
 * Extends JpaRepository to provide CRUD operations for the Employee entity.
 * This interface allows interaction with the 'employees' table in the database.
 */
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
