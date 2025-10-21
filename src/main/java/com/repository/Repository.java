package com.repository;

import com.utils.JsonWriter;

public abstract class Repository<T> {
  abstract String location();

  public abstract T load();

  public void dump(Object records) {
    JsonWriter.write(location(), records);
  }
}
