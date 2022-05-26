package com.example.demo.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Employee;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.EmployeeRepository;


@Service
@Transactional
public class EmployeeService {

	@Autowired
	EmployeeRepository repo;
	
	public List<Employee> getEmployees(){
		
		return repo.getEmployees();
	}
	
//	@Retryable(value = ResourceNotFoundException.class, maxAttempts = 3, backoff = @Backoff(delay = 3000))
	public Employee getEmployee(int eId) {
		Employee emp = repo.getEmployee(eId);
		if(emp==null) {
			throw new ResourceNotFoundException("Employee with the id "+eId+" is not present");
		}
		return emp;
	}
	
	public Employee saveEmployee(Employee emp) {
		
		return repo.save(emp);
		
	}
	
	@Retryable(value = ResourceNotFoundException.class, maxAttempts = 3, backoff = @Backoff(delay = 3000))
	public String deleteEmployee (int eId) {
		Employee emp = repo.getEmployee(eId);
		if(emp==null) {
			throw new ResourceNotFoundException("Employee with the id "+eId+" is not present");

		}
		repo.deleteById(eId);
		return "Employee with employee Id "+eId+" is deleted successfully";
				
	}
	
	@Retryable(value = ResourceNotFoundException.class, maxAttempts = 3, backoff = @Backoff(delay = 3000))
	public String deleteEmployeeSoft(int eId) {
		Employee emp = repo.getEmployee(eId);
		if(emp==null) {
			throw new ResourceNotFoundException("Employee with the id "+eId+" is not present");

		}
		
		repo.deleteEmployee(eId);
		return "Employee with Id "+eId+" is deleted successfully";
	}
	
	public Employee updateEmployee(Employee emp) {
		
		  Employee existingEmployee=repo.findById(emp.geteId()).orElse(new Employee());
		  existingEmployee.seteName(emp.geteName());
		  existingEmployee.seteDesignation(emp.geteDesignation());
		  existingEmployee.seteMail(emp.geteMail());
		  existingEmployee.setLocation(emp.getLocation());

		 
		return repo.save(existingEmployee);
	}
	
	@Retryable(value = ResourceNotFoundException.class, maxAttempts = 3, backoff = @Backoff(delay = 3000))
	public Employee updateEmployee1(int eId,Employee emp) {
		Employee existingEmployee=repo.findById(eId).orElseThrow(()-> new ResourceNotFoundException("Employee with the id "+eId+" is not present"));
		existingEmployee.seteName(emp.geteName());
		existingEmployee.seteDesignation(emp.geteDesignation());
		existingEmployee.setLocation(emp.getLocation());
		existingEmployee.seteMail(emp.geteMail());
		return repo.save(existingEmployee);
	}
	
	/*
	 * @Scheduled(cron = "1 * * * * *") public void printToFileDaily() throws
	 * IOException { SimpleDateFormat formatter = new
	 * SimpleDateFormat("dd/MMM/yyyy HH:mm:ss"); Date date = new Date();
	 * 
	 * File file = new File("employee.txt");
	 * 
	 * List<Employee> list = getEmployees(); BufferedWriter buffer = new
	 * BufferedWriter(new FileWriter(file,true));
	 * buffer.write("Time of printing "+formatter.format(date)+"\n"); for(Employee
	 * emp: list) { buffer.write(emp.toString()+"\n"); } buffer.write("\n");
	 * buffer.flush();
	 * buffer.close();
	 *  }
	 */
}
