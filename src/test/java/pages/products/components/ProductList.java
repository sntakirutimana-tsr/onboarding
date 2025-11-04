package pages.products.components;

import pages.products.components.cards.ProductCard;
import pages.products.components.cards.RegularProdCard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import static utils.Executor.hasEvaluatedSuccessfully;

public final class ProductList extends Component {
  private final By resultsCounterLocator = By.cssSelector("p.woocommerce-result-count");
  private final By itemLocator = By.cssSelector("ul.products li");
  private final By paginatorLocator = By.cssSelector("nav.woocommerce-pagination");

  public ProductList(WebDriver driver) {
    super(driver, null, null);
  }

  public List<WebElement> items() {
    return findAllBy(itemLocator);
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
    return items().stream()
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
      .allMatch(p -> hasEvaluatedSuccessfully(() -> p.hasAllNecessaryDetails(category)));
  }

  public String getResultsCountMsg() {
    return getText(resultsCounterLocator);
  }

  public Paginator getPaginator() {
    return new Paginator(getDriver(), paginatorLocator);
  }

  public List<String> getNames() {
    return toObjects()
      .stream()
      .map(ProductCard::getName)
      .toList();
  }

  public boolean hasTheRightNumberOfItems() {
    return hasEvaluatedSuccessfully(() -> getWait().until(d -> items().size() == totalProductCount()));
  }
}
