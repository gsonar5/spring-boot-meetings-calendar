package com.traveloka.calender.emplyoee.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

  Set<Employee> findByNameIn(List<String> names);

  Employee findByName(String name);
}