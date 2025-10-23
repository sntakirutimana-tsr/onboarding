package com.utils;

import org.slf4j.helpers.MessageFormatter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FormatUtils {
  public static String f(String template, Object... args) {
    return MessageFormatter.arrayFormat(template, args).getMessage();
  }
}
