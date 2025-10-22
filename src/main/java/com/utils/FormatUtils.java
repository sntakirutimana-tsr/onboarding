package com.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import org.slf4j.helpers.MessageFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FormatUtils {
  public static String f(String template, Object... args) {
    return MessageFormatter.arrayFormat(template, args).getMessage();
  }

  public static double extractPrice(String price) {
    String value = price.replaceAll(".*?(\\d+\\.\\d+).*", "$1");
    return Double.parseDouble(value);
  }
}
