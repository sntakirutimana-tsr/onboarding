package customtype;

import domainobjects.AccountDetails;
import domainobjects.BillingDetails;
import domainobjects.OrderNote;
import domainobjects.ShippingDetails;
import io.cucumber.java.DataTableType;

import java.util.Map;

public class CustomDataTableTypes {
  @DataTableType
  public BillingDetails billingDetails(Map<String, String> entry) {
    return new BillingDetails(
      entry.get("country"),
      entry.get("company_name"),
      entry.get("first_name"),
      entry.get("last_name"),
      entry.get("street_address"),
      entry.get("apartment"),
      entry.get("city"),
      entry.get("state"),
      entry.get("zip_code"),
      entry.get("phone"),
      entry.get("email")
    );
  }
}

