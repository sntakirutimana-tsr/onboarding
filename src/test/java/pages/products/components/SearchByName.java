package pages.products.components;

import pages.BasePage;
import pages.products.ProductPage;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static utils.ExtendedHelpers.*;

public final class SearchByName extends Component {
  @FindBy(id = "woocommerce-product-search-field-0")
  private WebElement field;
  @FindBy(xpath = "//button[@type='submit' and text()='Search']")
  private WebElement button;

  public SearchByName(WebDriver driver, WebElement root) {
    super(driver, root);
  }

  public void type(String keyword) {
    field.sendKeys(keyword);
  }

  public BasePage search() {
    button.click();
    return new ProductPage(getDriver());
  }

  @Override
  public void ensureIsReady() {
    waitForElementToBeInteractive(getDriver(), field, 5);
    waitForVisibility(getDriver(), button, 3);
  }
}
