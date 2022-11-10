package com.spring.junit.exceptio.JunitExceptionHandlingProject;

import com.spring.junit.exceptio.JunitExceptionHandlingProject.model.Employee;
import com.spring.junit.exceptio.JunitExceptionHandlingProject.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JunitExceptionHandlingProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(JunitExceptionHandlingProjectApplication.class, args);
	}

	@Bean
	public CommandLineRunner setup(EmployeeRepository repository){
		return args -> {
			repository.save(new Employee(1,"Alice","alice@gmail.com","alice123"));
			repository.save(new Employee(2, "Bob", "bob@gmail.com", "bob123"));
			repository.save(new Employee(3, "Jake", "jake@gmail.com", "jake123"));
		};
	}

}
