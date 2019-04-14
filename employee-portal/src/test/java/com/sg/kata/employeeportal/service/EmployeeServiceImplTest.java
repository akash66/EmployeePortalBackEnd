package com.sg.kata.employeeportal.service;

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
import org.springframework.data.domain.Sort;

import com.sg.kata.employeeportal.model.Employee;
import com.sg.kata.employeeportal.repository.EmployeeRepository;
import com.sg.kata.employeeportal.sevice.impl.EmployeeServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceImplTest {

	@InjectMocks
	private EmployeeServiceImpl employeeService;
	
	@Mock
	private EmployeeRepository employeeRepository;
	
	private List<Employee> employeeList;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		employeeList = new ArrayList<Employee>();
		Employee employeeOne = new Employee(1L, "Akash", "Panda", "Male", new Date(), "RMZ");
		Employee employeeTwo = new Employee(2L, "Amit", "Mittal", "Male", new Date(), "EMZ");
		Employee employeeThree = new Employee(3L, "Preetham", "Rai", "Male", new Date(), "KMZ");
         
		employeeList.add(employeeOne);
		employeeList.add(employeeTwo);
		employeeList.add(employeeThree);
	}

	@Test
	public final void testCreateEmployee() {
		Employee employee = new Employee(1L, "Akash", "Panda", "Male", new Date(), "RMZ");
		when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
		
		Employee createdEmployee = employeeService.createEmployee(employee);
		
		assertEquals(employee, createdEmployee);
	}

	@Test
	public final void testGetEmployeesInAscendingOrder() {
        when(employeeRepository.findAll(any(Sort.class))).thenReturn(employeeList);
         
        //test
        List<Employee> empList = employeeService.getSortedAndOrderedEmployees("ASC", "firstName");
        assertEquals(3, empList.size());
	}

	@Test
	public final void testGetEmployeesInDescendingOrder() {
		when(employeeRepository.findAll(any(Sort.class))).thenReturn(employeeList);
        
        //test
        List<Employee> empList = employeeService.getSortedAndOrderedEmployees("DESC", "firstName");
        assertEquals(3, empList.size());
	}

}
