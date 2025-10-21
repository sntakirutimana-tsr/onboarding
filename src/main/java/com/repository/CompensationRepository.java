package com.repository;

import com.model.CompensatedEmployee;
import com.utils.JsonLoader;

import com.google.gson.reflect.TypeToken;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CompensationRepository extends Repository<List<CompensatedEmployee>> {
  private static CompensationRepository instance;

  public static CompensationRepository getInstance() {
    if (Objects.isNull(instance))
      instance = new CompensationRepository();
    return instance;
  }

  @Override
  public String location() {
    return "compensations.json";
  }

  @Override
  public List<CompensatedEmployee> load() {
    Type type = new TypeToken<List<CompensatedEmployee>>() {
    }.getType();
    return JsonLoader.extract(location(), type);
  }
}
