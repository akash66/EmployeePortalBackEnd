package com.sg.kata.employeeportal.sevice;

import java.util.List;

import com.sg.kata.employeeportal.model.Employee;

public interface EmployeeService {

	Employee create(final Employee employee);
	
	List<Employee> getEmployees(String sortBy,String orderBy);

}
