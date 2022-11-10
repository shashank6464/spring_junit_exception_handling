package com.spring.junit.exceptio.JunitExceptionHandlingProject.service;

import com.spring.junit.exceptio.JunitExceptionHandlingProject.model.Employee;
import com.spring.junit.exceptio.JunitExceptionHandlingProject.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service("employeeService")
public class EmployeeServiceImpl implements  EmployeeService{

    @Autowired
    private EmployeeRepository repository;


    @Override
    public List<Employee> getEmployees() {
        return repository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        Optional<Employee> emp = repository.findById(id);
        if(emp.isPresent()) return emp.get();
        return null;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public List<Employee> saveAllEmployees(List<Employee> employees) {
        return repository.saveAll(employees);
    }

    @Override
    public void removeEmployee(Long id) {
//        if(repository.existsById(id)) { repository.deleteById(id); return "Delete Successfully!"; }
//        return "Employee NOT FOUND";
        repository.deleteById(id);
    }
}