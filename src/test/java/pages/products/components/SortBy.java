package pages.products.components;

import pages.BasePage;
import pages.products.ProductPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static utils.ExtendedHelpers.*;

public final class SortBy extends Component {

  public SortBy(WebDriver driver, WebElement root) {
    super(driver, root);
  }

  @Override
  public void ensureIsReady() {
    waitForElementToBeInteractive(getDriver(), getRoot(), 5);
  }

  public String getSelectedText() {
    return new Select(getRoot()).getFirstSelectedOption().getText();
  }

  public BasePage selectOption(String criterion) {
    select(getDriver(), getRoot(), criterion);
    return new ProductPage(getDriver());
  }
}
