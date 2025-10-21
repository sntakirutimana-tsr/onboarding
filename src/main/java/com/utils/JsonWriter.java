package com.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public final class JsonWriter {

  private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

  public static void write(String outputPath, Object data) {
    File resourceDir = new File(Objects.requireNonNull(JsonWriter.class.getClassLoader().getResource(".")).getFile());
    try (Writer writer = new FileWriter(new File(resourceDir, outputPath))) {
      gson.toJson(data, writer);
    } catch (Exception e) {
      log.error("Failed to write JSON data to {} due to {}", outputPath, e.getMessage());
      throw new RuntimeException(e);
    }
  }
}

