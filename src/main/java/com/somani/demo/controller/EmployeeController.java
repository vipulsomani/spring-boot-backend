package com.somani.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.somani.demo.model.Employee;
import com.somani.demo.repository.EmployeeRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;

	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}
	
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	@GetMapping("/employees/{id}")
	public  ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(()->new RuntimeException());
		return ResponseEntity.ok(employee);
	}
	
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable int id,@RequestBody Employee employee2){
	Employee employee = employeeRepository.findById(id)
			.orElseThrow(()->new RuntimeException());
	employee.setFirstName(employee2.getFirstName());
	employee.setLastName(employee2.getLastName());
	employee.setEmail(employee2.getEmail());
	employee.setSalary(employee2.getSalary());
	employee.setProfilepic(employee2.getProfilepic());
	
	Employee updatedEmployee= employeeRepository.save(employee);
	return ResponseEntity.ok(updatedEmployee);
	}
	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity< Map<String, Boolean>> deleteEmployee(@PathVariable int id){
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(()->new RuntimeException());
		employeeRepository.delete(employee);
		Map<String , Boolean> response = new HashMap<>();
		response.put("deleted",Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	
	/*public List<Employee> searchEmployees(String theSearchName) {
		// TODO Auto-generated method stub
		 Session currentSession = sessionFactory.getCurrentSession();
		 Query theQuery = null;
		 if (theSearchName != null && theSearchName.trim().length() > 0) {

	            // search for firstName or lastName ... case insensitive
	            theQuery =currentSession.createQuery("from Employee where lower(Name) like :theName", Employee.class);
	            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");

	        }
	        else {
	         
	            theQuery =currentSession.createQuery("from Employee", Employee.class);            
	        }
	        
	        // execute query and get result list
	        List<Employee> employees = theQuery.getResultList();
	                
	        // return the results        
	        return employees;
	}*/
	
	/*@GetMapping("/search")
	  //  public  ResponseEntity<Employee>searchEmployees(@RequestParam("theSearchName") String theSearchName,
	    //                                Model theModel) {

		 public String searchEmployees(@RequestParam("theSearchName") String theSearchName,
                 Model theModel) {

	        // search customers from the service
	        List<Employee> theEmployees =searchEmployees(theSearchName);
	                
	        // add the customers to the model
	        theModel.addAttribute("employees", theEmployees);

	        return "list-employee";        
	}*/
	/*List<Employee> findByRatingNotContaining(String rating);
	
	List<Employee> results = employeeRepository.findByRatingNotContaining("PG");


	@PutMapping("/employees")
	public ResponseEntity<Employee> searchEmployee(String searchValue,@RequestBody Employee employee2){
	Employee employee = employeeRepository.findAll(searchValue)
			.orElseThrow(()->new RuntimeException());
	employee.setFirstName(employee2.getFirstName());
	employee.setLastName(employee2.getLastName());
	employee.setEmail(employee2.getEmail());
	
	Employee updatedEmployee= employeeRepository.save(employee);
	return ResponseEntity.ok(updatedEmployee);
	}*/
}
