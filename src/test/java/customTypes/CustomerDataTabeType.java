package customTypes;

import io.cucumber.java.DataTableType;

import java.util.Map;

public class CustomerDataTabeType {
  @DataTableType
  public LoginDto customer(Map<String, String> entry) {
    return new LoginDto(entry.get("username_or_email"), entry.get("password"));
  }
}
