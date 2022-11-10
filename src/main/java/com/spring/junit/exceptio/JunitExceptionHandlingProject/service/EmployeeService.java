package com.spring.junit.exceptio.JunitExceptionHandlingProject.service;

import com.spring.junit.exceptio.JunitExceptionHandlingProject.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getEmployees();

    Employee getEmployeeById(Long id);

    Employee saveEmployee(Employee employee);

    List<Employee> saveAllEmployees(List<Employee> employees);

    void removeEmployee(Long id);
}