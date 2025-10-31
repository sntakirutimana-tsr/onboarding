package domainobjects;

public class ShippingDetails {
  private String firstName;
  private String lastName;
  private String companyName;
  private String country;
  private String streetAddress;
  private String apartment;
  private String city;
  private String state;
  private String zipCode;

  public ShippingDetails(){}

  public ShippingDetails(String firstName, String lastName, String companyName, String country, String streetAddress, String apartment, String city, String state, String zipCode) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.companyName = companyName;
    this.country = country;
    this.streetAddress = streetAddress;
    this.apartment = apartment;
    this.city = city;
    this.state = state;
    this.zipCode = zipCode;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getStreetAddress() {
    return streetAddress;
  }

  public void setStreetAddress(String streetAddress) {
    this.streetAddress = streetAddress;
  }

  public String getApartment() {
    return apartment;
  }

  public void setApartment(String apartment) {
    this.apartment = apartment;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }
}
