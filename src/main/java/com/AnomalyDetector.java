package com;

import com.model.CompensatedEmployee;
import com.repository.CompensationRepository;
import com.repository.DepartmentBudgetRepository;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@Slf4j
public class AnomalyDetector {
  private final List<String> reportLines;
  private final CompensationRepository compensationRepository;
  private final DepartmentBudgetRepository departmentBudgetRepository;

  public AnomalyDetector() {
    reportLines = new ArrayList<>();
    compensationRepository = CompensationRepository.getInstance();
    departmentBudgetRepository = DepartmentBudgetRepository.getInstance();
  }

  public static void main(String...args) {
    AnomalyDetector detector = new AnomalyDetector();
    detector.runAudit();
  }

  private void analyzePayDiscrepancy(List<CompensatedEmployee> payroll) {
    OptionalDouble lowestJuniorPay = payroll.stream()
      .filter(e -> e.getEmploymentType().equalsIgnoreCase("Junior"))
      .mapToDouble(CompensatedEmployee::getTotalAnnualCompensation)
      .min();

    if (lowestJuniorPay.isPresent()) {
      double pay = lowestJuniorPay.getAsDouble();
      payroll.stream()
        .filter(e -> e.getEmploymentType().equalsIgnoreCase("Intern"))
        .filter(e -> e.getTotalAnnualCompensation() > pay)
        .forEach(e -> reportLines.add(String.format(
          "Pay Discrepancy: Intern %s %s earns ($%.2f) more than lowest-paid Junior ($%.2f)",
          e.getFirstName(), e.getLastName(), e.getTotalAnnualCompensation(), pay
        )));
    }
  }

  private void analyzeBudgetDominator(List<CompensatedEmployee> payroll, Map<String, Double> budgets) {
    payroll.forEach(e -> {
      double budget = budgets.getOrDefault(e.getDepartment(), 0.0);
      if (budget > 0 && e.getTotalAnnualCompensation() / budget > 0.4)
        reportLines.add(String.format(
          "Budget Dominator: %s %s's compensation (%.2f) exceeds 40%% of %s department budget (%.2f)",
          e.getFirstName(), e.getLastName(), e.getTotalAnnualCompensation(), e.getDepartment(), budget
        ));
    });
  }

  private void analyzeSeniorityPayInversion(List<CompensatedEmployee> payroll) {
    List<CompensatedEmployee> seniorsPayroll = payroll.stream()
      .filter(e -> e.getEmploymentType().equalsIgnoreCase("Senior"))
      .toList();

    if (!seniorsPayroll.isEmpty()) {
      payroll.stream()
        .filter(e -> e.getEmploymentType().equalsIgnoreCase("Mid-Level"))
        .forEach(mid -> {
          List<CompensatedEmployee> lowerPaidSeniors = seniorsPayroll.stream()
            .filter(senior -> mid.getTotalAnnualCompensation() > senior.getTotalAnnualCompensation())
            .toList();

          if (!lowerPaidSeniors.isEmpty()) {
            lowerPaidSeniors.forEach(senior -> reportLines.add(String.format(
              "Seniority-Pay Inversion: Mid-level %s %s ($%.2f) earns more than Senior %s %s ($%.2f)",
              mid.getFirstName(), mid.getLastName(), mid.getTotalAnnualCompensation(),
              senior.getFirstName(), senior.getLastName(), senior.getTotalAnnualCompensation()
            )));
          }
        });
    }
  }

  public void runAudit() {
    List<CompensatedEmployee> payroll = compensationRepository.load();
    Map<String, Double> budgets = departmentBudgetRepository.load();

    analyzePayDiscrepancy(payroll);
    analyzeBudgetDominator(payroll, budgets);
    analyzeSeniorityPayInversion(payroll);

    // Write report
    if (reportLines.isEmpty())
      reportLines.add("All checks passed. No anomalies detected.");
    saveReportToClasspath();
  }

  private void saveReportToClasspath() {
    try {
      File resourceDir = new File(Objects.requireNonNull(getClass().getClassLoader().getResource(".")).getFile());
      File reportFile = new File(resourceDir, "audit_report.txt");

      try (FileWriter writer = new FileWriter(reportFile)) {
        for (String line : reportLines)
          writer.write(line + "\n");
      }
      log.info("Audit complete. Report generated at: {}", reportFile.getAbsoluteFile());
    } catch (IOException e) {
      log.error("Failed to save the audit report~{}", e.getMessage());
    }
  }
}
