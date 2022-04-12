package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.retry.annotation.EnableRetry;

import com.example.demo.entity.Employee;
import com.example.demo.entity.UserDAO;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.UserRepository;

@SpringBootApplication
@EnableJpaRepositories
@EnableRetry
//@EnableScheduling
public class EmployeeOperationsApplication implements CommandLineRunner {

	@Autowired
	EmployeeRepository repo;
	
	@Autowired
	UserRepository userRepo;
	public static void main(String[] args) {
		SpringApplication.run(EmployeeOperationsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Employee emp = new Employee("Peter","ASE","peter@gmail.com","Chennai");
		Employee emp1 = new Employee("mani","ASE","mani@gmail.com","hyderabad");
		Employee emp2 = new Employee("Jagan","ASE","jagan@gmail.com","Delhi");
		Employee emp3 = new Employee("Kovin","ASE","kovin@gmail.com","Pune");
		Employee emp4 = new Employee("Josh","ASE","josh@gmail.com","Bengaluru");
		Employee emp5 = new Employee("Jensy","ASE","jensy@gmail.com","kolkata");
		Employee emp6 = new Employee("talin","ASE","talin@gmail.com","Chennai");
		Employee emp7 = new Employee("Mohan","ASE","mohan@gmail.com","Mumbai");
		Employee emp8 = new Employee("mani","ASE","mani@gmail.com","Mumbai");
		Employee emp9 = new Employee("mandy","ASE","mandy@gmail.com","gurugran");
		Employee emp10= new Employee("Joshi","ASE","joshi@gmail.com","Delhi");
		repo.save(emp);
		repo.save(emp1);
		repo.save(emp2);
		repo.save(emp3);
		repo.save(emp4);
		repo.save(emp5);
		repo.save(emp6);
		repo.save(emp7);
		repo.save(emp8);
		repo.save(emp9);
		repo.save(emp10);
		
		UserDAO user1 = new UserDAO("peter","pass","ROLE_ADMIN");
		UserDAO user2 = new UserDAO("jo","jens","ROLE_USER");
		userRepo.save(user1);
		userRepo.save(user2);
		
		
	}

}
