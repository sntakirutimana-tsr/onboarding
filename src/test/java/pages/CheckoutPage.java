package pages;

import domainobjects.BillingDetails;
import domainobjects.OrderNote;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage extends BasePage {
  public CheckoutPage(WebDriver driver) {
    super(driver);
  }

  // Billing fields locators
  private final By firstNameField = By.id("billing_first_name");
  private final By lastNameField = By.id("billing_last_name");
  private final By companyNameField = By.id("billing_company");
  private final By countryDropdown = By.id("billing_country");
  private final By streetAddressField = By.id("billing_address_1");
  private final By apartmentField = By.id("billing_address_2");
  private final By cityField = By.id("billing_city");
  private final By stateDropdown = By.id("billing_state");
  private final By zipField = By.id("billing_postcode");
  private final By phoneField = By.id("billing_phone");
  private final By emailField = By.id("billing_email");


  private final By orderNoteField = By.id("order_comments");

  // PAYMENT METHODS
  private final By bankTransferRadio = By.id("payment_method_bacs");
  private final By cashOnDeliveryRadio = By.id("payment_method_cod");


  private final By placeOrderBtn = By.id("place_order");
  private final By confirmationMsg = By.cssSelector("p.woocommerce-notice--success");
  private final By paymentMethodConfirmationMsg = By.cssSelector(".woocommerce-order-overview__payment-method strong");
  private final By orderDetailsHeader = By.cssSelector("h2.woocommerce-order-details__title");
  private final By errorMsg = By.cssSelector("ul.woocommerce-error li");

  public void fillField(By locator, String value) {
    WebElement field = waitFor(ExpectedConditions.elementToBeClickable(locator));
    field.clear();
    if (value != null && !value.isEmpty()) type(field,value); ;
  }



  public void safeClick(By locator) {
    waitFor(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".blockUI.blockOverlay")));
    try {
      WebElement element = waitFor(ExpectedConditions.elementToBeClickable(locator));
      element.click();
    } catch (StaleElementReferenceException e) {
      WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
      element.click();
    }
  }
  public void navigateToCheckoutPage(String productId) {
    Actions actions = new Actions(driver);

    // 1️⃣ Open homepage (update with your base URL)
    load("/");

    // 2️⃣ Add product to cart
    By addToCartButton = By.cssSelector("[data-product_id='" + productId + "']");
    WebElement addCartBtn = waitFor(ExpectedConditions.elementToBeClickable(addToCartButton));
    assert addCartBtn != null;
    actions.moveToElement(addCartBtn).click().perform();

    // 3️⃣ Wait for the cart count to update
    By cartCount = By.xpath("//span[@class='count' and contains(text(),'1')]");
    waitFor(ExpectedConditions.visibilityOfElementLocated(cartCount));

    // 4️⃣ Hover over cart icon
    WebElement cartIcon = findBy(By.cssSelector("a.cart-container"));
    actions.moveToElement(cartIcon).perform();

    // 5️⃣ Wait for checkout button to appear and click it
    By checkoutBtn = By.xpath("//a[contains(@class,'checkout') and text()='Checkout']");
    WebElement panelCheckoutBtn = waitFor(ExpectedConditions.elementToBeClickable(checkoutBtn));

    assert panelCheckoutBtn != null;
    click(panelCheckoutBtn);

    // 6️⃣ Wait for checkout page to load
    waitFor(ExpectedConditions.visibilityOfElementLocated(firstNameField));
  }

  public void selectField(By locator,String value){
    new Select(findBy(locator)).selectByVisibleText(value);
  }
  // Billing
  public void fillBillingDetails(BillingDetails billing) {

    type(findBy(firstNameField), billing.getFirstName());
    type(findBy(lastNameField), billing.getLastName());
    type(findBy(companyNameField), billing.getCompanyName());
    new Select(findBy(countryDropdown)).selectByVisibleText(billing.getCountry());
    type(findBy(streetAddressField), billing.getStreetAddress());
    type(findBy(apartmentField), billing.getApartment());
    type(findBy(cityField), billing.getCity());
    new Select(findBy(stateDropdown)).selectByVisibleText(billing.getState());
    type(findBy(zipField), billing.getZipCode());
    type(findBy(phoneField), billing.getPhone());
    type(findBy(emailField), billing.getEmail());
  }


  // Order Note methods
  public void addOrderNote(OrderNote note) {
    type(findBy(orderNoteField), note.getNote());
  }

  // Payment Selection
  public void selectPaymentMethod(String paymentMethod) {
    waitFor(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".blockUI.blockOverlay")));

    switch (paymentMethod.toLowerCase()) {
      case "direct bank transfer":
//        findBy(bankTransferRadio).click();
        safeClick(bankTransferRadio);
        break;
      case "cash on delivery":
//        findBy(cashOnDeliveryRadio).click();
        safeClick(cashOnDeliveryRadio);
        break;
      default:
        throw new IllegalArgumentException("Unknown payment method: " + paymentMethod);
    }
  }

  public void clickPlaceOrder() {
    safeClick(placeOrderBtn);
  }
  public boolean isOnOrderConfirmationPage() {
    // Wait for the URL to indicate successful redirection
    waitFor(ExpectedConditions.urlContains("order-received"));

    // Verify that the current URL contains the confirmation path
    String currentUrl = driver.getCurrentUrl();
    return currentUrl.contains("order-received");
  }
  public String getConfirmationMessage() {
    WebElement message = waitFor(ExpectedConditions.visibilityOfElementLocated(confirmationMsg));
    return getText(findBy(confirmationMsg));
  }
  public String getPaymentConfirmationMessage() {
    WebElement message = waitFor(ExpectedConditions.visibilityOfElementLocated(confirmationMsg));
    return getText(findBy(paymentMethodConfirmationMsg));
  }

  public String getErrorMessage() {
    WebElement errorElement = waitFor(ExpectedConditions.visibilityOfElementLocated(errorMsg));

    return errorElement.getText().trim();
  }

}
