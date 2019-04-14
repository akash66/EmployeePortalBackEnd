package com.sg.kata.employeeportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sg.kata.employeeportal.model.Employee;
import com.sg.kata.employeeportal.sevice.EmployeeService;

import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping(path = "/employees")
	ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
		return new ResponseEntity<Employee>(employeeService.createEmployee(employee), HttpStatus.CREATED);

	}

	@GetMapping(path = "/employees")
	ResponseEntity<List<Employee>> getEmployees(
			@ApiParam(allowableValues = "ASC,DESC") @RequestParam("sortBy") String sortBy,
			@ApiParam(allowableValues = "firstName,lastName,gender,dateOfBirth,department") @RequestParam("orderBy") String orderBy) {
		List<Employee> employees = employeeService.getSortedAndOrderedEmployees(sortBy, orderBy);
		if (null == employees || employees.isEmpty())
			return new ResponseEntity<List<Employee>>(HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);

	}
}
