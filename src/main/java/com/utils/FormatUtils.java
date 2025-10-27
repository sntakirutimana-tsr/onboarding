package com.utils;

import org.slf4j.helpers.MessageFormatter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FormatUtils {
  public static String f(String template, Object... args) {
    return MessageFormatter.arrayFormat(template, args).getMessage();
  }

  public static double extractPrice(String price) {
    String value = price.replaceAll(".*?(\\d+\\.\\d+).*", "$1");
    return Double.parseDouble(value);
  }

  public static List<String> extractFormattedCategories(String classAttr) {
    List<String> formattedCategories = new ArrayList<>();

    Pattern pattern = Pattern.compile("product_cat-([a-z0-9-]+)");
    Matcher matcher = pattern.matcher(classAttr);

    while (matcher.find()) {
      String rawCategory = matcher.group(1);
      String[] parts = rawCategory.split("-");

      if (parts.length > 0) {
        if (parts[0].equals("mens"))
          parts[0] = "men’s";
        else if (parts[0].equals("womens"))
          parts[0] = "women’s";
      }

      String formatted = String.join(" ", parts);
      formattedCategories.add(formatted.toLowerCase());
    }
    return formattedCategories;
  }
}
