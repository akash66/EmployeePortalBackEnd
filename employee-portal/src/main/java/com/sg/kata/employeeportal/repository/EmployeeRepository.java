package com.sg.kata.employeeportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sg.kata.employeeportal.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

}
