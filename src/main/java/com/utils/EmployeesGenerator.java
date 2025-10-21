package com.utils;

import com.model.Employee;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EmployeesGenerator {

  public static Employee generateEmployee() {
    Faker faker = new Faker();
    Random random = new Random();

    return Employee.builder()
      .employeeId(random.nextInt(10000))
      .firstName(faker.name().firstName())
      .lastName(faker.name().lastName())
      .age(random.nextInt(43) + 18)
      .department(faker.company().industry())
      .employmentType(random.nextBoolean() ? "Full-Time" : "Part-Time")
      .build();
  }

  public static List<Employee> generateRandomEmployees(int num) {

    List<Employee> employees = new ArrayList<>();
    for (int i = 0; i < num; i++)
      employees.add(generateEmployee());
    return employees;
  }
}
