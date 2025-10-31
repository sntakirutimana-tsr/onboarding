package pages.products.components;

import pages.products.ProductPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static utils.ExtendedHelpers.waitForVisibility;
import static utils.FormatUtils.f;

public class Paginator extends Component {

  public Paginator(WebDriver driver, WebElement root) {
    super(driver, root);
  }

  public boolean isCurrentPage(String pageNumber) {
    WebElement webElement = findBy(By.cssSelector("li span.page-numbers.current"));
    return pageNumber.equals(getText(webElement));
  }

  String control(String using) {
    String ctrl = using.replaceAll(".*?(forward|backward|\\d+).*", "$1");
    return switch (ctrl) {
      case "forward" -> "→";
      case "backward" -> "←";
      default -> ctrl;
    };
  }

  public ProductPage navigateToPage(String using) {
    String locator = f("//li/a[contains(@class, 'page-numbers') and text()='{}']", control(using));
    findBy(By.xpath(locator)).click();
    return new ProductPage(getDriver());
  }

  @Override
  public void ensureIsReady() {
    waitForVisibility(getDriver(), getRoot(), 5);
  }
}
