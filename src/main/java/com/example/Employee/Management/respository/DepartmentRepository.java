package com.example.Employee.Management.respository;

import com.example.Employee.Management.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for accessing Department entities.
 * Extends JpaRepository to provide CRUD operations for the Department entity.
 * This interface allows interaction with the 'departments' table in the database.
 */
public interface DepartmentRepository extends JpaRepository<Department,Long> {
}
