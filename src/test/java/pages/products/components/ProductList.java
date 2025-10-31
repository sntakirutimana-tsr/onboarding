package pages.products.components;

import pages.products.components.cards.ProductCard;
import pages.products.components.cards.RegularProdCard;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import static utils.Executor.hasEvaluatedAndSucceed;
import static utils.ExtendedHelpers.*;

public final class ProductList extends Component {
  @FindBy(css = "p.woocommerce-result-count")
  private WebElement resultsCounter;
  @FindBy(css = "ul.products li")
  private List<WebElement> items;
  @FindBy(css = "nav.woocommerce-pagination")
  private WebElement paginatorShadow;

  private final Paginator paginator;

  public ProductList(WebDriver driver) {
    super(driver);
    paginator = new Paginator(driver, paginatorShadow);
  }

  int totalProductCount() {
    String value = getResultsCountMsg();
    if ("Showing the single result".equals(value))
      return 1;

    Matcher allMatcher = Pattern.compile("Showing all (\\d+)").matcher(value);
    if (allMatcher.find())
      return Integer.parseInt(allMatcher.group(1));

    Matcher rangeMatcher = Pattern.compile("Showing\\s+(\\d+)\\D+(\\d+)\\s+of\\s+\\d+").matcher(value);
    if (rangeMatcher.find()) {
      int start = Integer.parseInt(rangeMatcher.group(1));
      int end = Integer.parseInt(rangeMatcher.group(2));

      return end - start + 1;
    }

    throw new RuntimeException("Product results count must show a valid number >= 1");
  }

  List<RegularProdCard> toObjects() {
    return items.stream()
      .map(i -> new RegularProdCard(getDriver(), i))
      .toList();
  }

  public boolean areSortedBy(String criterion) {
    List<RegularProdCard> products = toObjects();
    return switch (criterion.toLowerCase()) {
      case "average rating" -> IntStream.range(0, products.size() - 1)
        .allMatch(i -> products.get(i).getRating() >= products.get(i + 1).getRating());
      case "price: low to high" -> IntStream.range(0, products.size() - 1)
        .allMatch(i -> products.get(i).getPrice() <= products.get(i + 1).getPrice());
      case "price: high to low" -> IntStream.range(0, products.size() - 1)
        .allMatch(i -> products.get(i).getPrice() >= products.get(i + 1).getPrice());
      default -> throw new IllegalArgumentException("Unknown sorting criterion~" + criterion);
    };
  }

  public boolean areInPriceRange(double minPrice, double maxPrice) {
    return toObjects()
      .stream()
      .allMatch(p -> {
        double productPrice = p.getPrice();
        return productPrice >= minPrice && productPrice <= maxPrice;
      });
  }

  public boolean hasOnlyItemsWhoseNamesContain(String partialName) {
    return toObjects()
      .stream()
      .allMatch(p -> p.getName().toLowerCase().contains(partialName));
  }

  public boolean hasOnlyItemsWithCategory(String category) {
    return toObjects()
      .stream()
      .allMatch(p -> hasEvaluatedAndSucceed(() -> p.ensureIsReady(category)));
  }

  public String getResultsCountMsg() {
    return getText(resultsCounter);
  }

  public Paginator getPaginator() {
    return paginator;
  }

  public List<String> getNames() {
    return toObjects()
      .stream()
      .map(ProductCard::getName)
      .toList();
  }

  @Override
  public void ensureIsReady() {
    waitForVisibility(getDriver(), resultsCounter, 5);
    waitFor(getDriver(), d -> items.size() == totalProductCount(), 5);
  }
}
