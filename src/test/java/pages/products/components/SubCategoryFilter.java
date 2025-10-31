package pages.products.components;

import pages.BasePage;
import pages.products.ProductPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static utils.ExtendedHelpers.*;

public final class SubCategoryFilter extends Component {
  @FindBy(xpath = "//h2[text()='Browse By Categories']")
  private WebElement title;
  @FindBy(id = "product_cat")
  private WebElement dropdown;

  public SubCategoryFilter(WebDriver driver, WebElement root) {
    super(driver, root);
  }

  public BasePage selectOption(String subCategory) {
    select(getDriver(), dropdown, subCategory);
    return new ProductPage(getDriver());
  }

  @Override
  public void ensureIsReady() {
    waitForVisibility(getDriver(), title, 5);
    waitForElementToBeInteractive(getDriver(), dropdown, 3);
  }
}
