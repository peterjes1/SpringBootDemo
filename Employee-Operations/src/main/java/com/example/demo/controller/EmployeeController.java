package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Employee;
import com.example.demo.entity.JwtRequest;
import com.example.demo.entity.JwtResponse;
import com.example.demo.entity.UserDAO;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.MyUserDetailsService;
import com.example.demo.util.JWTUtility;

@RestController
@EnableCaching
@CrossOrigin
@CacheConfig(cacheNames = {"employees"})
public class EmployeeController {

	@Autowired
	private JWTUtility jwtUtility;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	
	
	
	
	@Autowired
	EmployeeService empService;
	
	@GetMapping("/")
	public String welcome() {
		return "Welcome to the welcome page";
	}
	
	@PostMapping("/authenticate")
	@Cacheable
	public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtRequest.getUserName(), jwtRequest.getPassword())
					);
		}
		catch (BadCredentialsException e) {
			throw new BadCredentialsException("INVALID_CREDENTIALS",e);
			// TODO: handle exception
		}
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUserName());
		
		final String token = jwtUtility.generateToken(userDetails);
		
		return new JwtResponse(token);
		
	}
	
	@PostMapping("/register")
	public UserDAO register(@RequestBody UserDAO user) {
		return userDetailsService.saveUser(user);
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
	@CacheEvict(allEntries = true)
	public Employee saveEmployee(@RequestBody Employee employee) {
		return empService.updateEmployee(employee);
	}
	
	@PutMapping("/saveEmployee/{eId}")
	@CachePut(key = "#eId")
	@CacheEvict(key="#eId", allEntries = true)
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
