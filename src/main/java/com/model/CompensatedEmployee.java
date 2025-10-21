package com.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CompensatedEmployee extends Employee {
  private static final double JUNIOR_BASE_SALARY = 50000;
  private static final double MID_BASE_SALARY = 90000;
  private static final double SENIOR_BASE_SALARY = 140000;

  @Getter
  private double totalAnnualCompensation;

  public static CompensatedEmployee build(Employee employee) {
    return CompensatedEmployee.builder()
      .employeeId(employee.getEmployeeId())
      .firstName(employee.getFirstName())
      .lastName(employee.getLastName())
      .age(employee.getAge())
      .department(employee.getDepartment())
      .employmentType(employee.getEmploymentType())
      .totalAnnualCompensation(0)
      .build();
  }

  public void setTotalAnnualCompensation(String level) {
    this.totalAnnualCompensation = switch (Level.valueOf(level).getLevel().toLowerCase()) {
      case "junior" -> JUNIOR_BASE_SALARY;
      case "mid-level" -> 1.1 * MID_BASE_SALARY;
      case "senior" -> 1.2 * SENIOR_BASE_SALARY;
      default -> throw new IllegalArgumentException("Unknown level: " + level);
    };
  }
}
