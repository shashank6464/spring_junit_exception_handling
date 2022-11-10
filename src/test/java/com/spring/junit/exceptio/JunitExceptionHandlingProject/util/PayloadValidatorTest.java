package com.spring.junit.exceptio.JunitExceptionHandlingProject.util;

import com.spring.junit.exceptio.JunitExceptionHandlingProject.model.Employee;
import com.spring.junit.exceptio.JunitExceptionHandlingProject.model.PayloadValidation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class PayloadValidatorTest {


    @Test
    public void validatePayload(){

        Employee employee = new Employee(1, "Alice", "alice@email.com", "alice123");

        assertEquals(false, PayloadValidation.createdPayloadValidation(employee));

    }

    @Test
    public void validateInvalidPayload(){
        Employee employee = new Employee(0, "Alice", "alice@email.com", "alice123");

        assertEquals(true, PayloadValidation.createdPayloadValidation(employee));

    }


}