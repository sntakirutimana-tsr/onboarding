package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductDetailsPage extends BasePage{

  @FindBy(className = "woocommerce-loop-product__title") private WebElement productName;
  @FindBy(css = ".woocommerce-breadcrumb") public WebElement breadcrumb;
  @FindBy(className = "wp-post-image") private WebElement image;
  @FindBy(className = "woocommerce-product-details__short-description") private WebElement description;
  @FindBy(className = "posted_in") private WebElement category;
  @FindBy(className = "quantity") private WebElement quantityfield;
  @FindBy(css = "#tab-title-description > a") private WebElement descriptiontab;
  @FindBy(css = "#tab-title-additional_information > a") private WebElement additionalinfotab;
  @FindBy(css = "#tab-title-reviews > a") private WebElement reviewstab;
  @FindBy(className = "single_add_to_cart_button") private WebElement addtocartbutton;






  public ProductDetailsPage(WebDriver driver) {
    super(driver);
  }

   public void navigateToDetailsPage(String link) {
    By locator = By.xpath("//a[@href='" + link+ "']");
    waitFor(ExpectedConditions.visibilityOfElementLocated(locator));
    click(locator);
   }

   public String getProductName() {
    return productName.getText();
   }


   public String getBreadcrumb() {
    return breadcrumb.getText();
   }


   public  boolean isImageDisplayed() {
    return image.isDisplayed();
   }

   public  boolean isDescriptionDisplayed() {
    return description.isDisplayed();
   }

   public boolean isCategoryDisplayed() {
    return category.isDisplayed();
   }
   public boolean isQuantityDisplayed() {
    return quantityfield.isDisplayed();
   }

   public boolean isDescriptionTabDisplayed() {
    return descriptiontab.isDisplayed();
   }

   public boolean isAdditionalInformationTabDisplayed() {
    return additionalinfotab.isDisplayed();
   }

   public boolean isReviewsTabDisplayed() {
    return reviewstab.isDisplayed();

   }

   public boolean isSubmitButtonDisplayed() {
    return addtocartbutton.isDisplayed();
   }

}
