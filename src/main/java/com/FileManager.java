package com;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.model.Employee;

public class FileManager {
  private static final Gson gson = new Gson();

  public static void write(String fileName, List<? extends Employee> employees) {
    String jsonEmployees = gson.toJson(employees);

    try (FileWriter writer = new FileWriter(fileName)) {
      writer.write(jsonEmployees);
      System.out.println("Employees written successfully to " + fileName);
    } catch (IOException e) {
      System.err.println("Error writing file: " + e.getMessage());
    }
  }

  public static List<Employee> read(String fileName) {
    try (FileReader reader = new FileReader(fileName)) {

      System.out.println(fileName);

      // Construct Type using an anonymous subclass of TypeToke
      Type listType = new com.google.gson.reflect.TypeToken<List<Employee>>() {
      }.getType();

      List<Employee> employees = gson.fromJson(reader, listType);
      return employees;
    } catch (Exception e) {
      System.err.println("Error reading file: " + e.getMessage());
      return null;
    }
  }
}
