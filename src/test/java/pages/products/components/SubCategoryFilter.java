package pages.products.components;

import pages.BasePage;
import pages.products.ProductPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public final class SubCategoryFilter extends Component {
  private final By dropdownLocator = By.id("product_cat");

  public SubCategoryFilter(WebDriver driver, By root) {
    super(driver, root, null);
  }

  public BasePage selectOption(String subCategory) {
    selectByContainsVisibleTextFromDropdown(dropdownLocator, subCategory);
    return new ProductPage(getDriver());
  }
}
