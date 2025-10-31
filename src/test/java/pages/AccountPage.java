package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage extends BasePage {
  private final By userName = By.cssSelector(".ast-user-name");
  private final By email = By.cssSelector(".ast-user-email");
  private final By password = By.cssSelector(".ast-password");
  private final By registerButton = By.cssSelector(".ast-login-button");

  public AccountPage(WebDriver driver) {
    super(driver);
  }

  public void registerAccount(String username, String email, String password) {
    load("/account/");

  }

}
