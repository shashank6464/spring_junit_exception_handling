package com.spring.junit.exceptio.JunitExceptionHandlingProject.repository;

import com.spring.junit.exceptio.JunitExceptionHandlingProject.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("employeeRepository")
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}