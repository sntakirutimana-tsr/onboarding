package customTypes;
import domain.Customer;
import io.cucumber.java.ParameterType;

public class CustomParameterTypes {
  @ParameterType(".*")
  public Customer customer(String username) {
    return new Customer(username.replace("\"", ""));
  }
}
