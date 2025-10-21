package com;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model.CompensatedEmployee;
import com.model.Employee;
import com.utils.EmployeesGenerator;

public class Main {
    public static void loadEmployees(int num) {
        FileManager.write("employees.json",
                EmployeesGenerator.generateRandomEmployees(num));
    }

    public static void main(String[] args) throws IOException {

        // loadEmployees(5);

        // Read
        List<Employee> employees = FileManager.read("employees.json");

        List<Employee> juniors = new ArrayList<>();
        List<Employee> mids = new ArrayList<>();
        List<Employee> seniors = new ArrayList<>();

        for (Employee emp : employees) {
            int age = emp.getAge();

            if (age >= 18 && age <= 29) {
                juniors.add(emp);

            } else if (age >= 30 && age <= 45) {
                mids.add(emp);
            } else {
                seniors.add(emp);
            }
        }

        // Rite categories to json files
        FileManager.write("juniors.json", juniors);
        FileManager.write("mids.json", mids);
        FileManager.write("seniors.json", seniors);

        // Read
        List<Employee> juniorEmployees = FileManager.read("juniors.json");
        List<Employee> midsEmployees = FileManager.read("mids.json");
        List<Employee> seniorEmployees = FileManager.read("seniors.json");

        System.out.println("Juniors " + juniorEmployees.size());
        System.out.println("mids " + mids.size());
        System.out.println("seniors " + seniorEmployees.size());

        Map<String, List<CompensatedEmployee>> compensatedEmployees = new HashMap<>();

        compensatedEmployees.put("juniors", createCompensatedEmployees(juniorEmployees, "junior"));
        compensatedEmployees.put("mids", createCompensatedEmployees(midsEmployees, "mid"));
        compensatedEmployees.put("seniors", createCompensatedEmployees(seniorEmployees, "senior"));

        // Write them to compensation.json
        List<CompensatedEmployee> compensations = new ArrayList<>();
        compensations.addAll(compensatedEmployees.get("juniors"));
        compensations.addAll(compensatedEmployees.get("mids"));
        compensations.addAll(compensatedEmployees.get("seniors"));

        FileManager.write("compensation.json", compensations);

    }

    // From Employees to CompensatedEmployees
    public static List<CompensatedEmployee> createCompensatedEmployees(List<Employee> employees, String level) {
        List<CompensatedEmployee> compensatedEmployees = new ArrayList<>();

        // Juniors
        if (level.equals("junior")) {
            for (Employee emp : employees) {
                CompensatedEmployee compensatedEmployee = CompensatedEmployee.build(emp);
                calculateCompensation(compensatedEmployee, "junior");
                compensatedEmployees.add(compensatedEmployee);

            }

        } else if (level.equals("mid")) {
            for (Employee emp : employees) {
                CompensatedEmployee compensatedEmployee = CompensatedEmployee.build(emp);
                calculateCompensation(compensatedEmployee, "mid");
                compensatedEmployees.add(compensatedEmployee);
            }

        } else if (level.equals("senior")) {
            for (Employee emp : employees) {
                CompensatedEmployee compensatedEmployee = CompensatedEmployee.build(emp);
                calculateCompensation(compensatedEmployee, "senior");
                compensatedEmployees.add(compensatedEmployee);

            }

        } else {
            throw new RuntimeException("Invalid Level: " + level);
        }

        return compensatedEmployees;
    }

    public static CompensatedEmployee calculateCompensation(CompensatedEmployee compensatedEmployees, String level) {
        compensatedEmployees.setTotalAnnualCompensation(level);
        return compensatedEmployees;
    }
}