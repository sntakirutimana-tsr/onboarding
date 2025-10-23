package com.pages.product.components;

import com.pages.product.StorePage;
import com.utils.FormatUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Paginator extends Component {
  public Paginator(WebDriver driver, WebElement root) {
    super(driver, root);
  }

  public boolean isCurrentPage(String pageNumber) {
    WebElement webElement = findElement(By.cssSelector("li span.page-numbers.current"));
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

  public StorePage navigateToPage(String using) {
    String locator = FormatUtils.f("//li/a[contains(@class, 'page-numbers') and text()='{}']", control(using));
    findElement(By.xpath(locator)).click();
    return new StorePage("Store", getDriver());
  }

  @Override
  public void ensureAllCheckpointsAreReady() {
    waitForVisibility(getRoot(), 5);
  }
}
