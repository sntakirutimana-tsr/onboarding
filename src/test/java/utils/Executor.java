package utils;

import java.util.function.BooleanSupplier;

public class Executor {
  private Executor() {
  }

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
      System.err.println(error);
      throw new RuntimeException(error);
    }
  }

  public static void raiseIf(BooleanSupplier supplier) {
    raiseIf(supplier, "Condition failed");
  }
}
