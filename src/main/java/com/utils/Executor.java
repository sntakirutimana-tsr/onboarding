package com.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

import java.util.function.BooleanSupplier;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Executor {
  private static final Logger logger = LoggerFactoryUtil.getLogger(Executor.class);
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
      logger.error(error);
      throw new RuntimeException(error);
    }
  }

  public static void raiseIf(BooleanSupplier supplier) {
    raiseIf(supplier, "Condition failed");
  }
}
