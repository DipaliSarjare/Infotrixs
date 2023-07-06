package com.Ems.Dao;

import java.util.*;

import com.Ems.EmployeeAccess.AccessEmployeeDetails;
import com.Ems.Exception.EmployeeException;
import com.Ems.Model.Employee;

public class EmployeeManagementSystem {
    private List<Employee> employees;
    private AccessEmployeeDetails dataAccess;



    public EmployeeManagementSystem(String filePath) {
        dataAccess = new AccessEmployeeDetails(filePath);
        employees = dataAccess.getAllEmployees();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
        dataAccess.saveAllEmployees(employees);
        System.out.println("Employee added successfully!");
        
    }

    public void viewAllEmployees() throws EmployeeException {
        if (employees.isEmpty()) {
//            System.out.println("No employees found.");
        	throw new EmployeeException("No employees found.");
        } else {
            for (Employee employee : employees) {
                System.out.println(employee);
                System.out.println("-----------------------------------------------------------------------------");

            }
            
         }
    }

    public void deleteEmployee(int id)throws EmployeeException {
        boolean found = false;
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                employees.remove(employee);
                found = true;
                break;
            }
        }
        if (found) {
            dataAccess.saveAllEmployees(employees);
            System.out.println("Employee with ID " + id + " deleted successfully!");
        } else {
            throw new EmployeeException ("Employee not found with ID: " + id);
        }
    }

    public void updateEmployeeDetails(int id, Employee updatedEmployee) throws EmployeeException{
        boolean found = false;
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                employee.setName(updatedEmployee.getName());
                employee.setAge(updatedEmployee.getAge());
                employee.setAddress(updatedEmployee.getAddress());
                employee.setSalary(updatedEmployee.getSalary());
                employee.setDesignation(updatedEmployee.getDesignation());
                found = true;
                break;
            }
        }
        if (found) {
            dataAccess.saveAllEmployees(employees);
            System.out.println("Employee with ID " + id + " updated successfully!");
        } else {
        	 throw new EmployeeException("Employee not found with ID: " + id);
        }
    }
}

