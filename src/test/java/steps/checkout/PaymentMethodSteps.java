package steps.checkout;

import com.utils.DriverProvider;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class PaymentMethodSteps {
  WebDriver driver = DriverProvider.get();
  @When("Customer selects {string}")
  public void customer_selects_payment_method(String paymentMethod) {
    String methodId = "";

    if (paymentMethod.equalsIgnoreCase("Direct Bank Transfer")) {
      methodId = "payment_method_bacs";
    } else if (paymentMethod.equalsIgnoreCase("Cash on Delivery")) {
      methodId = "payment_method_cod";
    }

    By paymentLabelSelector = By.cssSelector("label[for='" + methodId + "']");
    By paymentBoxSelector = By.cssSelector("div.payment_box." + methodId.replace("payment_method_", "payment_method_"));

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    // wait until the overlay is invisible
    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".blockUI.blockOverlay")));
    // Retry once in case of stale reference
    for (int attempt = 0; attempt < 2; attempt++) {
      try {
        WebElement paymentLabel = wait.until(ExpectedConditions.elementToBeClickable(paymentLabelSelector));
        paymentLabel.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(paymentBoxSelector));
        break; // success, exit retry loop
      } catch (StaleElementReferenceException e) {
        System.out.println("Element went stale, retrying... (" + (attempt + 1) + ")");
        if (attempt == 1) throw e; // rethrow after 2nd failure
      }
    }

  }

  @Then("{string} is selected")
  public void payment_method_is_selected(String paymentMethod) {
    String methodId = switch (paymentMethod.trim().toLowerCase()) {
      case "direct bank transfer" -> "payment_method_bacs";
      case "cash on delivery" -> "payment_method_cod";
      default -> throw new IllegalArgumentException("Unknown payment method: " + paymentMethod);
    };

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    WebElement radio = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(methodId)));

    // ✅ Verify it’s selected
    assertTrue("Expected " + paymentMethod + " radio to be selected but it wasn’t.", radio.isSelected());

  }

  @Then("test mode warning message {string} is displayed")
  public void test_mode_warning_message_displayed(String warningMessage) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    // Only the selected payment method’s box will be visible
    By visibleBoxSelector = By.cssSelector("div.payment_box:not([style*='display:none'])");
    WebElement visibleBox = wait.until(ExpectedConditions.visibilityOfElementLocated(visibleBoxSelector));

    String actualWarning = visibleBox.getText().trim();

    // ✅ Assert the message
    assertEquals(warningMessage, actualWarning);
  }
}


