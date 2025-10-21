package com.utils;

import com.google.gson.Gson;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public final class JsonLoader {

  private static final Gson gson = new Gson();

  public static <T> T extract(String resourcePath, Class<T> type) {
    try (InputStream jis = JsonLoader.class.getClassLoader().getResourceAsStream(resourcePath)) {
      if (Objects.isNull(jis))
        throw new RuntimeException("JSON file not found: " + resourcePath);

      try (InputStreamReader reader = new InputStreamReader(jis, StandardCharsets.UTF_8)) {
        return gson.fromJson(reader, type);
      }

    } catch (Exception e) {
      log.error("Failed to load JSON data: {}", e.getMessage());
      throw new RuntimeException(e);
    }
  }

  public static <T> T extract(String resourcePath, Type type) {
    try (InputStream jis = JsonLoader.class.getClassLoader().getResourceAsStream(resourcePath)) {
      if (Objects.isNull(jis))
        throw new RuntimeException("JSON file not found: " + resourcePath);

      try (InputStreamReader reader = new InputStreamReader(jis, StandardCharsets.UTF_8)) {
        return gson.fromJson(reader, type);
      }

    } catch (Exception e) {
      log.error("Failed to load JSON data: {}", e.getMessage());
      throw new RuntimeException(e);
    }
  }
}
