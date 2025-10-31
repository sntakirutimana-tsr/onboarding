package utils;

import domainobjects.BillingDetails;

import java.util.Map;

public class BillingDetailsUtils {
  public static BillingDetails fromMap(Map<String, String> data) {
    BillingDetails billingDetails = new BillingDetails();
    billingDetails.setFirstName(data.get("first_name"));
    billingDetails.setLastName(data.get("last_name"));
    billingDetails.setCompanyName(data.get("company_name"));
    billingDetails.setCountry(data.get("country"));
    billingDetails.setStreetAddress(data.get("street_address"));
    billingDetails.setApartment(data.get("apartment"));
    billingDetails.setCity(data.get("city"));
    billingDetails.setState(data.get("state"));
    billingDetails.setZipCode(data.get("zip_code"));
    billingDetails.setPhone(data.get("phone"));
    billingDetails.setEmail(data.get("email"));

    return billingDetails;
  }
}
