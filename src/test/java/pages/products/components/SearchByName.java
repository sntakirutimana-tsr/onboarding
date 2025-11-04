package pages.products.components;

import pages.BasePage;
import pages.products.ProductPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public final class SearchByName extends Component {
  private final By searchFieldLocator = By.id("woocommerce-product-search-field-0");
  private final By buttonLocator = By.xpath("//button[@type='submit' and text()='Search']");

  public SearchByName(WebDriver driver, By root) {
    super(driver, root, null);
  }

  public void type(String keyword) {
    type(searchFieldLocator, keyword);
  }

  public BasePage search() {
    WebElement element = findBy(searchFieldLocator);
    click(buttonLocator);
    waitForStalenessOf(element);
    return new ProductPage(getDriver());
  }
}
