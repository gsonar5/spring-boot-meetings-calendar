package com.traveloka.calender.emplyoee.controller;

import com.traveloka.calender.contract.CreateEmplyoeeContract;
import com.traveloka.calender.emplyoee.persistence.Employee;
import com.traveloka.calender.emplyoee.service.EmployeeService;
import com.traveloka.calender.utils.ApiConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

  @Autowired
  private EmployeeService employeeService;

  public void setEmployeeService(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @GetMapping(ApiConstants.API_EMPLOYEES)
  public List<Employee> getEmployees() {
    List<Employee> employees = employeeService.retrieveEmployees();
    return employees;
  }

  @GetMapping(ApiConstants.API_EMPLOYEES_BY_EMPLOYEE_ID)
  public Employee getEmployee(@PathVariable(name = "employeeId") Long employeeId) {
    return employeeService.getEmployee(employeeId);
  }

  @PostMapping(ApiConstants.API_EMPLOYEES)
  public void saveEmployee(@RequestBody CreateEmplyoeeContract createEmplyoeeContract) {
    employeeService.saveEmployee(createEmplyoeeContract);
  }

}