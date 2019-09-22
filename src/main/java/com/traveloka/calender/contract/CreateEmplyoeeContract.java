package com.traveloka.calender.contract;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.validation.constraints.NotNull;

@JsonDeserialize
public class CreateEmplyoeeContract {
  private Long empId;

  @NotNull
  private String name;

  public Long getEmpId() {
    return empId;
  }

  public void setEmpId(Long empId) {
    this.empId = empId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "{" +
        "empId=" + empId +
        ", name='" + name + '\'' +
        '}';
  }
}
