package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AccountPage extends BasePage {
    @FindBy(id = "username")
    private WebElement usernameFld;
    @FindBy(id = "password")
    private WebElement passwordFld;
    @FindBy(className = "woocommerce-form-login__submit")
    private WebElement loginBtn;
    @FindBy(xpath = "//div[contains(@class,'woocommerce-MyAccount-content')]//p[contains(text(),'Hello ')]")
    private WebElement welcomeMessagePara;
    @FindBy(xpath = "//ul[contains(@class,'woocommerce-error')]//li")
    private WebElement errorMessageEl;

    @FindBy(id = "reg_username")
    private WebElement registrationUsernameFld;

    @FindBy(id = "reg_email")
    private WebElement registrationEmailFld;

    @FindBy(id = "reg_password")
    private WebElement registrationPasswordFld;

    @FindBy(css = ".woocommerce-form-register__submit")
    private WebElement registerBtn;

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String username) {
        wait.until(ExpectedConditions.elementToBeClickable(usernameFld));
        usernameFld.click();
        usernameFld.sendKeys(username);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.elementToBeClickable(passwordFld));
        passwordFld.click();
        passwordFld.sendKeys(password);
    }

    public void submitLoginForm() {
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
        loginBtn.click();
    }

    public String getWelcomeMessage() {
        wait.until(ExpectedConditions.elementToBeClickable(welcomeMessagePara));
        return welcomeMessagePara.getText();
    }

    public String getErrorMessage() {
        wait.until(ExpectedConditions.elementToBeClickable(errorMessageEl));
        return errorMessageEl.getText();
    }

    public void enterRegistrationUsername(String username) {
        wait.until(ExpectedConditions.elementToBeClickable(registrationUsernameFld));
        registrationUsernameFld.sendKeys(username);
    }

    public void enterRegistrationEmail(String email) {
        wait.until(ExpectedConditions.elementToBeClickable(registrationEmailFld));
        registrationEmailFld.sendKeys(email);
    }

    public void enterRegistrationPassword(String password) {
        wait.until(ExpectedConditions.elementToBeClickable(registrationPasswordFld));
        registrationPasswordFld.sendKeys(password);
    }

    public void submitRegistrationForm() {
        wait.until(ExpectedConditions.elementToBeClickable(registerBtn));
        registerBtn.click();
    }
}
