package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
  private int employeeId;
  private String firstName;
  private String lastName;
  private int age;
  private String department;
  private String employmentType;

  @Override
  public String toString() {
    return "Employee{" +
      "employeeId=" + employeeId +
      ", firstName='" + firstName + '\'' +
      ", lastName='" + lastName + '\'' +
      ", age=" + age +
      ", department='" + department + '\'' +
      ", employmentType='" + employmentType + '\'' +
      '}';
  }
}
