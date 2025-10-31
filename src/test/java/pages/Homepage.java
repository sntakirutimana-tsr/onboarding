package pages;

import pages.products.ProductPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static utils.ExtendedHelpers.waitForVisibility;
import static utils.Executor.hasEvaluatedAndSucceed;

public class Homepage extends BasePage {
  @FindBy(id = "menu-item-1227")
  private WebElement storeOption;

  @FindBy(id = "menu-item-1228")
  private WebElement menOption;

  @FindBy(id = "menu-item-1229")
  private WebElement womenOption;

  @FindBy(id = "menu-item-1230")
  private WebElement accessoriesOption;

  public Homepage(WebDriver driver) {
    super(driver);
  }

  public boolean isReady() {
    return hasEvaluatedAndSucceed(() -> {
      waitForVisibility(driver, storeOption, 5);
      waitForVisibility(driver, menOption, 2);
      waitForVisibility(driver, womenOption, 2);
      waitForVisibility(driver, accessoriesOption, 2);
    });
  }

  public ProductPage browseProducts(String pageName) {
    String page = pageName.replaceAll("'s$", "").toLowerCase();
    switch (page) {
      case "men" -> menOption.click();
      case "women" -> womenOption.click();
      case "accessories" -> accessoriesOption.click();
      case "store" -> storeOption.click();
      default -> throw new IllegalArgumentException("Unknown menu option: " + pageName);
    }
    return new ProductPage(driver);
  }
}
