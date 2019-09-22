package com.traveloka.calender.emplyoee.service;

import com.traveloka.calender.contract.CreateEmplyoeeContract;
import com.traveloka.calender.emplyoee.persistence.Employee;
import com.traveloka.calender.emplyoee.persistence.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  @Autowired
  private EmployeeRepository employeeRepository;

  public void setEmployeeRepository(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  public List<Employee> retrieveEmployees() {
    List<Employee> employees = employeeRepository.findAll();
    return employees;
  }

  public Employee getEmployee(Long employeeId) {
    Optional<Employee> optEmp = employeeRepository.findById(employeeId);
    return optEmp.get();
  }

  public void saveEmployee(CreateEmplyoeeContract employee) {
    Employee empToSave = new Employee();
    empToSave.setEmpId(employee.getEmpId());
    empToSave.setName(employee.getName());

    employeeRepository.save(empToSave);
  }
}