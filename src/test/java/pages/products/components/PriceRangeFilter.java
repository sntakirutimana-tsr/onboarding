package pages.products.components;

import pages.products.ProductPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static utils.Formatters.f;
import static utils.Executor.*;

public final class PriceRangeFilter extends Component {
  private final By titleLocator = By.xpath("//h2[text()='Filter by price']");
  private final By buttonLocator = By.xpath("//button[@type='submit' and text()='Filter']");
  private final By labelLocator = By.className("price_label");

  public PriceRangeFilter(WebDriver driver, By root) {
    super(driver, root, null);
  }

  public boolean hasPriceLabel(int min, int max) {
    return hasEvaluatedSuccessfully(() -> getWait()
      .until(ExpectedConditions.textToBePresentInElementLocated(labelLocator, f("Price: ${} — ${}", min, max))));
  }

  public boolean hasTitle() {
    return hasEvaluatedSuccessfully(() -> findBy(titleLocator));
  }

  public void setRange(double min, double max) {
    JavascriptExecutor js = (JavascriptExecutor) getDriver();
    js.executeScript(
      "jQuery('input#min_price').val(arguments[0]);" +
        "jQuery('input#max_price').val(arguments[1]);" +
        "jQuery('body').trigger('price_slider_slide', [arguments[0], arguments[1]]);" +
        "jQuery('body').trigger('price_slider_updated', [arguments[0], arguments[1]]);",
      min, max
    );
  }

  public ProductPage applyFilter() {
    WebElement title = findBy(titleLocator);
    click(buttonLocator);
    waitForStalenessOf(title);
    return new ProductPage(getDriver());
  }
}
