package steps;

import com.pages.Page;
import com.pages.home.Homepage;
import com.pages.product.ProductsPage;
import com.utils.DriverProvider;
import com.utils.RunContext;

import io.cucumber.java.en.Given;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public final class CommonSteps {

  @Given("a Customer is on the homepage")
  public void customer_is_on_the_homepage() {
    RunContext.setPage(CommonSteps.ensureHomepageIsAccessible());
  }

  public static Page ensureHomepageIsAccessible() {
    Homepage page = Homepage.visit();
    assertTrue(page.isLoaded());
    return page;
  }

  public static ProductsPage ensureProductPageIsAccessible(Homepage home, String name) {
    ProductsPage page = home.browseProducts(name);
    assertTrue(page.isLoaded());
    return page;
  }

  public static ProductsPage ensureProductPageIsAccessible(String name) {
    Homepage home = (Homepage) CommonSteps.ensureHomepageIsAccessible();
    ProductsPage page = home.browseProducts(name);
    assertTrue(page.isLoaded());
    return page;
  }

  @Given("Customer is on the checkout page")
  public void customerIsOnTheCheckoutPage() {
    WebDriver driver = DriverProvider.get();
    ensureHomepageIsAccessible();
    Actions actions= new Actions(driver, Duration.ofSeconds(5));
    WebElement add_cart_btn = driver.findElement(By.cssSelector("[data-product_id='1215']"));

    actions.moveToElement(add_cart_btn).click().perform();

    By cart = By.xpath("//span[@class='count' and contains(text(),'1')]");

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    wait.until(ExpectedConditions.visibilityOfElementLocated(cart));

    WebElement cart_icon = driver.findElement(By.cssSelector("a.cart-container"));
    actions.moveToElement(cart_icon).perform();

    // Wait for the panel to appear
    WebDriverWait waitCartPanel = new WebDriverWait(driver, Duration.ofSeconds(10));
    WebElement panelButton = waitCartPanel.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class,'checkout') and text()='Checkout']")));

    // Click the checkout button inside the panel
    panelButton.click();
  }
}
