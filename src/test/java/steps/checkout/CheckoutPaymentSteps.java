package steps.checkout;

import com.utils.DriverProvider;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class CheckoutPaymentSteps {

    WebDriver driver = DriverProvider.get();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


    // --- Shared helper to fill fields ---
    private void fillField(String fieldId, String value) {
        WebElement field = wait.until(ExpectedConditions.elementToBeClickable(By.id(fieldId)));
        field.clear();
        if (value != null && !value.isEmpty()) field.sendKeys(value);
    }
    private void safeClick(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".blockUI.blockOverlay")));
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
        } catch (StaleElementReferenceException e) {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
        }
    }

    @When("Customer enters First Name as {string}")
    public void customer_enters_first_name(String firstName) {
        fillField("billing_first_name", firstName);
    }

    @When("Customer enters Last Name as {string}")
    public void customer_enters_last_name(String lastName) {
        fillField("billing_last_name", lastName);
    }

    @When("Customer enters Company Name as {string}")
    public void customer_enters_company_name(String company) {
        fillField("billing_company", company);
    }

    @When("Customer selects Country Region as {string}")
    public void customer_selects_country_region(String country) {
//        WebElement countryDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("billing_country")));
//        Select select = new Select(countryDropdown);
//        select.selectByVisibleText(country);
        if (!country.isEmpty()) {
            WebElement countryDropdown = wait.until(
                    ExpectedConditions.elementToBeClickable(By.id("billing_country"))
            );
            Select select = new Select(countryDropdown);
            select.selectByVisibleText(country);
        }
    }

    @When("Customer enters Street Address as {string}")
    public void customer_enters_street_address(String address) {
        fillField("billing_address_1", address);
    }

    @When("Customer enters Apartment, Suite, Unit as {string}")
    public void customer_enters_apartment_suite_unit(String apartment) {
        fillField("billing_address_2", apartment);
    }

    @When("Customer enters Town City as {string}")
    public void customer_enters_city(String city) {
        fillField("billing_city", city);
    }

    @When("Customer selects State as {string}")
    public void customer_selects_state(String state) {
//        WebElement stateDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("billing_state")));
//        Select select = new Select(stateDropdown);
//        select.selectByVisibleText(state);

        if (!state.isEmpty()) {
            WebElement stateDropdown = wait.until(
                    ExpectedConditions.elementToBeClickable(By.id("billing_state"))
            );
            Select select = new Select(stateDropdown);
            select.selectByVisibleText(state);
        }
    }

    @When("Customer enters ZIP Code as {string}")
    public void customer_enters_zip_code(String zip) {
        fillField("billing_postcode", zip);
    }

    @When("Customer enters Phone as {string}")
    public void customer_enters_phone(String phone) {
        fillField("billing_phone", phone);
    }

    @When("Customer enters Email Address as {string}")
    public void customer_enters_email(String email) {
        fillField("billing_email", email);
    }

    @When("Customer clicks on {string} button")
    public void customer_clicks_on_button(String buttonName) {
        if (buttonName.equalsIgnoreCase("Place Order")) {
            safeClick(By.id("place_order"));
        }
    }

    @Then("Customer is redirected to the order confirmation page")
    public void customer_is_redirected_to_confirmation_page() {
        wait.until(ExpectedConditions.urlContains("order-received"));
        String currentUrl = driver.getCurrentUrl();
        assertTrue("Not redirected to order confirmation page!", currentUrl.contains("order-received"));

    }


    @Then("the message {string} should be displayed")
    public void message_should_be_displayed(String confirmationMsg) {

        By confirmationMsgLocator = By.cssSelector("p.woocommerce-notice--success");
        WebElement msgElement = wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationMsgLocator));
        assertEquals(confirmationMsg.trim(),
          msgElement.getText().trim());
    }

    @Then("the confirmation page should display detailed order information")
    public void confirmation_page_should_display_order_details() {
        By orderDetailsHeader = By.cssSelector("h2.woocommerce-order-details__title");
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(orderDetailsHeader));
        assertEquals("Order details header not found or incorrect.", "Order details", header.getText().trim());
    }

    @Then("the error message {string} should be displayed")
    public void error_message_should_be_displayed(String errorMsg) {
        By errorLocator = By.cssSelector("ul.woocommerce-error li");
        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(errorLocator));
        String actualError = errorElement.getText().trim();
        assertTrue("Expected error message not found. Got: " + actualError,
          actualError.contains(errorMsg));
    }

}














