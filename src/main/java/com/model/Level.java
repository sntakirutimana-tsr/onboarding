package com.model;

import lombok.Getter;

public enum Level {
  JUNIOR("Junior"), ASSOCIATE("Mid-Level"), SENIOR("Senior");

  @Getter
  private final String level;

  Level(String level) {
    this.level = level;
  }
}
