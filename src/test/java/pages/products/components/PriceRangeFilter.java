package pages.products.components;

import pages.products.ProductPage;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static utils.ExtendedHelpers.*;
import static utils.FormatUtils.f;
import static utils.Executor.*;

public final class PriceRangeFilter extends Component {
  @FindBy(xpath = "//h2[text()='Filter by price']")
  private WebElement title;
  @FindBy(xpath = "//button[@type='submit' and text()='Filter']")
  private WebElement button;
  @FindBy(className = "price_label")
  private WebElement label;

  public PriceRangeFilter(WebDriver driver, WebElement root) {
    super(driver, root);
  }

  public boolean hasPriceLabel(int min, int max) {
    String priceLabel = f("Price: ${} — ${}", min, max);
    return hasEvaluatedAndSucceed(() ->
      waitFor(getDriver(), ExpectedConditions.textToBePresentInElement(label, priceLabel), 3));
  }

  public void setRange(double min, double max) {
    executeScript(
      getDriver(),
      "jQuery('input#min_price').val(arguments[0]);" +
        "jQuery('input#max_price').val(arguments[1]);" +
        "jQuery('body').trigger('price_slider_slide', [arguments[0], arguments[1]]);" +
        "jQuery('body').trigger('price_slider_updated', [arguments[0], arguments[1]]);",
      min, max
    );
  }

  public ProductPage applyFilter() {
    button.click();
    waitForDisappearance(getDriver(), getRoot(), 5);
    return new ProductPage(getDriver());
  }

  @Override
  public void ensureIsReady() {
    waitForVisibility(getDriver(), title, 5);
    waitForElementToBeInteractive(getDriver(), button, 2);
    waitForVisibility(getDriver(), label, 2);
  }
}
