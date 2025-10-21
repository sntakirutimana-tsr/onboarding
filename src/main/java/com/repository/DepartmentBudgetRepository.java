package com.repository;

import com.utils.JsonLoader;

import com.google.gson.reflect.TypeToken;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DepartmentBudgetRepository extends Repository<Map<String, Double>> {
  private static DepartmentBudgetRepository instance;

  public static DepartmentBudgetRepository getInstance() {
    if (Objects.isNull(instance))
      instance = new DepartmentBudgetRepository();
    return instance;
  }

  @Override
  public String location() {
    return "department_budgets.json";
  }

  @Override
  public Map<String, Double> load() {
    Type type = new TypeToken<Map<String, Double>>() {
    }.getType();
    return JsonLoader.extract(location(), type);
  }
}
