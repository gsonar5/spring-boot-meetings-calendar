package com.traveloka.calender.emplyoee.service;

import com.traveloka.calender.contract.CreateEmplyoeeContract;
import com.traveloka.calender.emplyoee.persistence.Employee;

import java.util.List;

public interface EmployeeService {
  public List<Employee> retrieveEmployees();

  public Employee getEmployee(Long employeeId);

  public void saveEmployee(CreateEmplyoeeContract employee);

}