package com.sg.kata.employeeportal.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.sg.kata.employeeportal.model.Employee;
import com.sg.kata.employeeportal.sevice.impl.EmployeeServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {

	@InjectMocks
	private EmployeeController employeeController;
	
	@Mock
	private EmployeeServiceImpl employeeService;
	
	private List<Employee> employeeList;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		employeeList = new ArrayList<Employee>();
		Employee employeeTwo = new Employee(2L, "Amit", "Mittal", "Male", new Date(), "EMZ");
		Employee employeeThree = new Employee(3L, "Preetham", "Rai", "Male", new Date(), "KMZ");
         
		employeeList.add(employeeTwo);
		employeeList.add(employeeThree);
	}

	
	@Test
	public final void testCreateEmployee() {
		Employee employee = new Employee(1L, "Akash", "Panda", "Male", new Date(), "RMZ");
		ResponseEntity<Employee> employeeResponseEntity = new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
		when(employeeService.createEmployee(any(Employee.class))).thenReturn(employee);
		
		ResponseEntity<Employee> createdEmployee = employeeController.createEmployee(employee);
		assertEquals(createdEmployee.getBody(), employee);
		assertEquals(createdEmployee.getStatusCode(), employeeResponseEntity.getStatusCode());
	}
	
	@Test
	public final void testGetSortedAndOrderedEmployees() {
		ResponseEntity<List<Employee>> employeeResponseEntity = new ResponseEntity<List<Employee>>(employeeList, HttpStatus.OK);
		when(employeeService.getSortedAndOrderedEmployees(any(), any())).thenReturn(employeeList);
		
		ResponseEntity<List<Employee>> createdEmployee = employeeController.getSortedAndOrderedEmployees("ASC", "firstName");
		assertEquals(createdEmployee.getBody().size(), employeeList.size());
		assertEquals(createdEmployee.getStatusCode(), employeeResponseEntity.getStatusCode());
	}
	
	@Test
	public final void testEmptyGetSortedAndOrderedEmployees() {
		ResponseEntity<List<Employee>> employeeResponseEntity = new ResponseEntity<List<Employee>>(HttpStatus.BAD_REQUEST);
		when(employeeService.getSortedAndOrderedEmployees(any(), any())).thenReturn(new ArrayList<Employee>());
		
		ResponseEntity<List<Employee>> createdEmployee = employeeController.getSortedAndOrderedEmployees("ASC", "firstName");
		assertEquals(createdEmployee.getStatusCode(), employeeResponseEntity.getStatusCode());
	}
	
}
