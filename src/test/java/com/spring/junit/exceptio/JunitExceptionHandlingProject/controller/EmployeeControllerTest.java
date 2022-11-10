package com.spring.junit.exceptio.JunitExceptionHandlingProject.controller;


import com.spring.junit.exceptio.JunitExceptionHandlingProject.JunitExceptionHandlingProjectApplication;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import javax.print.attribute.standard.Media;

@RunWith(SpringJUnit4ClassRunner.class)   // for running this with junit4
@ContextConfiguration(classes = JunitExceptionHandlingProjectApplication.class)  // context setting for the real data (added in main)
@SpringBootTest // spring test
@FixMethodOrder(MethodSorters.NAME_ASCENDING)  // to execute the test methods in order (based on name)
public class EmployeeControllerTest {

    // For controller based mocks (for web layer)
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext employeeContext; // autowired the configuration


    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(employeeContext).build();

    }

    // THESE test cases check the payload details after the controller URI is called;


    @Test
    public void verifyAllEmployees() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/employee/get-employees")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))
                .andDo(print());
    }


    @Test
    public void verifyGetById() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/employee/get-employee-by-id/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.email").exists())
                .andExpect(jsonPath("$.id").value(2))  // checking the expected value and expression value(output response)
                .andExpect(jsonPath("$.name").value("Bob"))
                .andDo(print());
    }


    // EXCEPTION CHECKING
    // bad request when method used should be post instead of get -> exception successfull
    @Test
    public void verifyValidEmployee() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get("/employee/save-employee")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorCode").value(400))
                .andExpect(jsonPath("$.message").value("THE REQUEST CANNOT BE PLACED DUE TO MALFUNCTION SYNTAX"))
                .andDo(print());
    }

    // EXCEPTION CHECKING (INVALID ID)
    @Test
    public void verifyInvalidGetById() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/employee/get-employee-by-id/5")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorCode").value(404))
                .andExpect(jsonPath("$.message").value("EMPLOYEE DOESN'T EXISTS"))
                .andDo(print());
    }

















}
