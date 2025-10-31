package customTypes;

import domain.Customer;
import io.cucumber.java.DataTableType;

import java.util.Map;

public class CustomerDataTableTypes {
  @DataTableType
  public LoginDto loginDto(Map<String, String> entry) {
    return new LoginDto(entry.get("username_or_email"), entry.get("password"));
  }

  @DataTableType
  public Customer customer(Map<String, String> entry) {
    return new Customer(entry.get("username"), entry.get("email"), entry.get("password"));
  }
}
