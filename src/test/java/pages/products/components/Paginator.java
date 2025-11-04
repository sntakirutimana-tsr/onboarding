package pages.products.components;

import pages.products.ProductPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;

import static utils.Formatters.f;

public class Paginator extends Component {
  private final By currentPageLocator = By.cssSelector("li span.page-numbers.current");

  public Paginator(WebDriver driver, By root) {
    super(driver, root, null);
  }

  public boolean isCurrentPage(String pageNumber) {
    return pageNumber.equals(getText(currentPageLocator));
  }

  String control(String using) {
    String ctrl = using.replaceAll(".*?(forward|backward|\\d+).*", "$1");
    return switch (ctrl) {
      case "forward" -> "→";
      case "backward" -> "←";
      default -> ctrl;
    };
  }

  public ProductPage viewAnotherPage(String using) {
    String locator = f("//li/a[contains(@class, 'page-numbers') and text()='{}']", control(using));
    WebElement toBeStaleAfterTheFact = findBy(currentPageLocator);
    click(By.xpath(locator));
    waitForStalenessOf(toBeStaleAfterTheFact);
    return new ProductPage(getDriver());
  }
}
