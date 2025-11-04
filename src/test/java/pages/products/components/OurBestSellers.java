package pages.products.components;

import pages.products.components.cards.BestSellerProdCard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static utils.Executor.*;

public final class OurBestSellers extends Component {
  private final By titleLocator = By.xpath("//h2[text()='Our Best Sellers']");
  private final By itemLocator = By.cssSelector("ul.product_list_widget li");

  public OurBestSellers(WebDriver driver, By root) {
    super(driver, root, null);
  }

  public void ensureHasTitleAndThreeProducts() {
    raiseIf(findBy(titleLocator)::isDisplayed);
    raiseIf(() -> {
      List<WebElement> items = getWait().until(ExpectedConditions.numberOfElementsToBe(itemLocator, 3));
      return items.stream()
        .map(i -> new BestSellerProdCard(getDriver(), i))
        .allMatch(p -> hasEvaluatedSuccessfully(p::hasAllNecessaryDetails));
    });
  }
}
