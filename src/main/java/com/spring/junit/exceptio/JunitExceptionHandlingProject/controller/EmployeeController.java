package com.spring.junit.exceptio.JunitExceptionHandlingProject.controller;

import com.spring.junit.exceptio.JunitExceptionHandlingProject.exceptionHandling.EmployeeException;
import com.spring.junit.exceptio.JunitExceptionHandlingProject.model.Employee;
import com.spring.junit.exceptio.JunitExceptionHandlingProject.model.PayloadValidation;
import com.spring.junit.exceptio.JunitExceptionHandlingProject.model.Response;
import com.spring.junit.exceptio.JunitExceptionHandlingProject.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService service;

    @RequestMapping(value = "/get-employees", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> getEmployees(){
        return new ResponseEntity<>(service.getEmployees(), HttpStatus.OK);
    }

    @RequestMapping(value = "/get-employee-by-id/{id}", method = RequestMethod.GET)
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) throws EmployeeException{
//        Employee emp = service.getEmployeeById(id);
//        if(emp==null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//
//        return new ResponseEntity<>(emp, HttpStatus.OK);

        Employee employee = service.getEmployeeById(id);
        if(employee==null || employee.getId()<=0){
            throw new EmployeeException("EMPLOYEE DOESN'T EXISTS");
        }

        return new ResponseEntity<Employee>(service.getEmployeeById(id), HttpStatus.OK);

    }

    @RequestMapping(value = "/save-employee", method = RequestMethod.POST)
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee payload) throws EmployeeException{

        if(!PayloadValidation.createdPayloadValidation(payload)) throw new EmployeeException("PAYLOAD MALFORMED. ID MUST NOT BE DEFINED");

        return new ResponseEntity<Employee>(service.saveEmployee(payload), HttpStatus.OK);
    }

    @RequestMapping(value = "/save-all-employee", method = RequestMethod.POST)
    public ResponseEntity<List<Employee>> saveAllEmployees(@RequestBody List<Employee> employees) {
        return new ResponseEntity<>(service.saveAllEmployees(employees), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete-employee/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Response> removeEmployee(@PathVariable("id") Long id) throws EmployeeException{

        Employee employee = service.getEmployeeById(id);
        if(employee==null || employee.getId()<=0){
            throw new EmployeeException("EMPLOYEE DOESN'T EXISTS");
        }

        service.removeEmployee(id);
        return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), "SUCCESSFULLY DELETED EMPLOYEE"), HttpStatus.OK);
    }




}
