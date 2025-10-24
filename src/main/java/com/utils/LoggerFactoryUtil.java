package com.utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class LoggerFactoryUtil {

  private LoggerFactoryUtil() {
    throw new UnsupportedOperationException("LoggerFactoryUtil is a utility class and cannot be instantiated.");
  }

  /**
   * Provides a type-safe logger for the specified class.
   *
   * @param clazz The class for which the logger is being created.
   * @return Logger instance.
   */
  public static Logger getLogger(Class<?> clazz) {
    return LoggerFactory.getLogger(clazz);
  }
}

