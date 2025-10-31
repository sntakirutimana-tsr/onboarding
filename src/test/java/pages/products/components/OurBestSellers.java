package pages.products.components;

import pages.products.components.cards.BestSellerProdCard;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static utils.Executor.raiseIf;
import static utils.ExtendedHelpers.*;
import static utils.Executor.hasEvaluatedAndSucceed;

public final class OurBestSellers extends Component {
  @FindBy(xpath = "//h2[text()='Our Best Sellers']")
  private WebElement title;
  @FindBy(css = "ul.product_list_widget li")
  private List<WebElement> items;

  public OurBestSellers(WebDriver driver, WebElement root) {
    super(driver, root);
  }

  @Override
  public void ensureIsReady() {
    waitForVisibility(getDriver(), getRoot(), 5);
    raiseIf(() -> title.isDisplayed());
    waitFor(getDriver(), d -> items.size() == 3 && items
        .stream()
        .map(i -> new BestSellerProdCard(getDriver(), i))
        .allMatch(p -> hasEvaluatedAndSucceed(p::ensureIsReady)),
      5
    );
  }
}
