package com.sg.kata.employeeportal.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;

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
		Employee employeeCreated = employeeService.createEmployee(employee);
		verify(employeeRepository, times(1)).save(employee);
		
		
		/*Employee employee = new Employee(1L, "Akash", "Panda", "Male", new Date(), "RMZ");
		when(employeeRepositoryMock.save(any(Employee.class))).thenReturn(employee);

		Employee employeeCreated = employeeService.createEmployee(employee);
		ArgumentCaptor<Employee> employeeArgument = ArgumentCaptor.forClass(Employee.class);
		verify(employeeRepositoryMock, times(1)).save(employeeArgument.capture());
		verifyNoMoreInteractions(employeeRepositoryMock);

		//assertPerson(employee, employeeArgument.getValue());
		assertEquals(employeeCreated, employee);*/
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
