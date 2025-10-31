package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AccountPage extends BasePage {
  private final By userName = By.id("reg_username");
  private final By email = By.id("reg_email");
  private final By password = By.id("reg_password");
  private final By registerButton = By.cssSelector(".woocommerce-form-register__submit");
  private final By profileLink = By.cssSelector(".woocommerce-form-profile__link");

  public AccountPage(WebDriver driver) {
    super(driver);
  }

  public void registerAccount(String username, String emailAddress, String newPassword) {
    load("/account/");
    waitFor(ExpectedConditions.visibilityOfElementLocated(userName));
    waitFor(ExpectedConditions.visibilityOfElementLocated(email));
    waitFor(ExpectedConditions.visibilityOfElementLocated(password));
    waitFor(ExpectedConditions.visibilityOfElementLocated(registerButton));
    type(findBy(userName), username);
    type(findBy(email), emailAddress);
    type(findBy(password), newPassword);
    click(registerButton);
  }

}
