package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AccountDetailsPage extends BasePage {

  @FindBy(id = "account_first_name")
  private WebElement firstNameField;

  @FindBy(id = "account_last_name")
  private WebElement lastNameField;

  @FindBy(name = "save_account_details")
  private WebElement saveChangedBtn;

  public AccountDetailsPage(WebDriver driver) {
    super(driver);
  }

  public void enterFirstName(String name) {
    wait.until(ExpectedConditions.elementToBeClickable(firstNameField));
    firstNameField.click();
    firstNameField.sendKeys(name);
  }

  public void enterLastName(String name) {
    wait.until(ExpectedConditions.elementToBeClickable(lastNameField));
    lastNameField.click();
    lastNameField.sendKeys(name);
  }

  public void submitProfileForm (){
    wait.until(ExpectedConditions.elementToBeClickable(saveChangedBtn));
    saveChangedBtn.click();
  }


}
