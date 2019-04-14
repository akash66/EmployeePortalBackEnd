package com.sg.kata.employeeportal.sevice;

import java.util.List;

import com.sg.kata.employeeportal.model.Employee;

public interface EmployeeService {

	Employee createEmployee(final Employee employee);

	List<Employee> getSortedAndOrderedEmployees(String sortBy, String orderBy);

}
