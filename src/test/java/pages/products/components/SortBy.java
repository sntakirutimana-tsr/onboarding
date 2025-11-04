package pages.products.components;

import pages.BasePage;
import pages.products.ProductPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public final class SortBy extends Component {
  private final By selectLocator = By.tagName("select");

  public SortBy(WebDriver driver, By root) {
    super(driver, root, null);
  }

  public String getSelectionChoiceVisibleText() {
    return new Select(findBy(selectLocator)).getFirstSelectedOption().getText();
  }

  public BasePage selectOption(String criterion) {
    selectByContainsVisibleText(selectLocator, criterion);
    return new ProductPage(getDriver());
  }
}
