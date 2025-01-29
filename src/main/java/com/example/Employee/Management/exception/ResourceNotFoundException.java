package com.example.Employee.Management.exception;

/**
 * Exception thrown when a requested resource is not found.
 * This custom exception is used to handle situations where the requested resource (e.g., employee, department)
 * is not available in the system.
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constructs a new ResourceNotFoundException with a specific error message.
     *
     * @param message the detail message explaining the cause of the exception
     */
    public ResourceNotFoundException(String message) {
        super(message);  // Passes the message to the parent RuntimeException class
    }
}
