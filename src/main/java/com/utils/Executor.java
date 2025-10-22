package com.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.function.BooleanSupplier;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public final class Executor {
  public static boolean hasEvaluatedAndSucceed(Runnable runnable) {
    try {
      runnable.run();
      return true;
    } catch (Exception ignored) {
      return false;
    }
  }

  public static void raiseIf(BooleanSupplier supplier, String error) {
    if (!supplier.getAsBoolean()) {
      log.error(error);
      throw new RuntimeException(error);
    }
  }

  public static void raiseIf(BooleanSupplier supplier) {
    raiseIf(supplier, "Condition failed");
  }
}
