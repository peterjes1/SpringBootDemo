package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.retry.annotation.EnableRetry;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;

@SpringBootApplication
@EnableJpaRepositories
@EnableRetry
//@EnableScheduling
public class EmployeeOperationsApplication implements CommandLineRunner {

	@Autowired
	EmployeeRepository repo;
	public static void main(String[] args) {
		SpringApplication.run(EmployeeOperationsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Employee emp = new Employee("Peter","ASE");
//		Employee emp1 = new Employee("mani","ASE");
//		Employee emp2 = new Employee("Jagan","ASE");
//		Employee emp3 = new Employee("Kovin","ASE");
//		Employee emp4 = new Employee("Josh","ASE");
//		Employee emp5 = new Employee("Jensy","ASE");
//		Employee emp6 = new Employee("talin","ASE");
//		Employee emp7 = new Employee("Mohan","ASE");
//		Employee emp8 = new Employee("mani","ASE");
//		Employee emp9 = new Employee("mandy","ASE");
//		Employee emp10= new Employee("Joshi","ASE");
//		repo.save(emp);
//		repo.save(emp1);
//		repo.save(emp2);
//		repo.save(emp3);
//		repo.save(emp4);
//		repo.save(emp5);
//		repo.save(emp6);
//		repo.save(emp7);
//		repo.save(emp8);
//		repo.save(emp9);
//		repo.save(emp10);
		
		
	}

}
