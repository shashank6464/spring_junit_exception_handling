package com.spring.junit.exceptio.JunitExceptionHandlingProject.util;

import com.spring.junit.exceptio.JunitExceptionHandlingProject.model.Employee;
import com.spring.junit.exceptio.JunitExceptionHandlingProject.model.PayloadValidation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class PayloadValidatorTest {


    @Test
    public void validatePayload(){

        Employee employee = new Employee(1, "Alice", "asdf@email.com", "3wergw5erge");

        assertEquals(false, PayloadValidation.createdPayloadValidation(employee));

    }

    @Test
    public void validateInvalidPayload(){
        Employee employee = new Employee(0, "Alice", "asdf@email.com", "3wergw5erge");

        assertEquals(true, PayloadValidation.createdPayloadValidation(employee));

    }


}