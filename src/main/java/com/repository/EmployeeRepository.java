package com.repository;

import com.model.Employee;
import com.utils.JsonLoader;

import com.google.gson.reflect.TypeToken;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.lang.reflect.Type;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EmployeeRepository extends Repository<List<Employee>> {

  @Override
  public String location() {
    return "employees.json";
  }

  @Override
  public List<Employee> load() {
    Type type = new TypeToken<List<Employee>>() {
    }.getType();
    return JsonLoader.extract(location(), type);
  }
}
