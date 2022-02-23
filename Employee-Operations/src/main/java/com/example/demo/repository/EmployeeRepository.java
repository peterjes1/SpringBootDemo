package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	
	@Query("select e from Employee e where e.activeYN='Y'")
	public List<Employee> getEmployees();
	
	@Modifying
	@Query("update Employee e set e.activeYN='N' where e.eId=?1")
	public void deleteEmployee(int eId);
	
	@Query("select e from Employee e where e.activeYN='Y' and e.eId=?1")
	public Employee getEmployee(int eId);
}
