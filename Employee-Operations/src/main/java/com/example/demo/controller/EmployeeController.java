package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;

@RestController
@EnableCaching
@CacheConfig(cacheNames = {"employees"})
public class EmployeeController {

	
	@Autowired
	EmployeeService empService;
	
	@GetMapping("/")
	public String welcome() {
		return "Welcome to the welcome page";
	}
	
	@GetMapping("/getEmployee/{eId}")
	@Cacheable(key = "#eId")
	public Employee getEmployee(@PathVariable(value = "eId") int eId) {
		return empService.getEmployee(eId);
	}
	
	@GetMapping("/getEmployees")
	@Cacheable
	public List<Employee> getEmployees(){
		return empService.getEmployees();
	}
	
	@PostMapping("/saveEmployee")
	public Employee saveEmployee(@RequestBody Employee employee) {
		return empService.updateEmployee(employee);
	}
	
	@PutMapping("/saveEmployee/{eId}")
	@CachePut(key = "#eId")
	public Employee updateEmployee(@PathVariable(value = "eId") int eId,@RequestBody Employee employee) {
		return empService.updateEmployee1(eId,employee);
	}
	
	@DeleteMapping("/deleteEmployee/{eId}/hard")
	@CacheEvict(key = "#eId", allEntries = true)
	public String deleteEmployee(@PathVariable(value = "eId") int eId) {
		return empService.deleteEmployee(eId);
		
	}
	
	@DeleteMapping("/deleteEmployee/{eId}")
	@CacheEvict(key = "#eId", allEntries = true)
	public String deleteEmployeeSoft(@PathVariable(value = "eId") int eId) {
		return empService.deleteEmployeeSoft(eId);
	}
	
}
