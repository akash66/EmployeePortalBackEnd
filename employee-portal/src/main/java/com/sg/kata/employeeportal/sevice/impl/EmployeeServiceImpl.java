package com.sg.kata.employeeportal.sevice.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sg.kata.employeeportal.model.Employee;
import com.sg.kata.employeeportal.repository.EmployeeRepository;
import com.sg.kata.employeeportal.sevice.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static final String ASC = "ASC";
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	@Transactional
	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	@Transactional
	public List<Employee> getSortedAndOrderedEmployees(String sortBy, String orderBy) {
		if (ASC.equalsIgnoreCase(sortBy))
			return employeeRepository.findAll(Sort.by(Sort.Direction.ASC, orderBy));
		else
			return employeeRepository.findAll(Sort.by(Sort.Direction.DESC, orderBy));

	}

}
