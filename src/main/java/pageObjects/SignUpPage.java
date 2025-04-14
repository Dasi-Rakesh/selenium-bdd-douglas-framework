package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignUpPage {
    private WebDriver driver;

    // Constructor
    public SignUpPage(WebDriver driver) {
        this.driver = driver;
    }

    // Page elements
    private By firstName = By.id("firstname");
    private By lastName = By.id("lastname");
    private By email = By.id("email_address");
    private By password = By.id("password");
    private By confirmPassword = By.id("password-confirmation");
    private By signUpButton = By.cssSelector("button[title='Create an Account']");

    // Actions
    public void enterFirstName(String fName) {
        driver.findElement(firstName).sendKeys(fName);
    }

    public void enterLastName(String lName) {
        driver.findElement(lastName).sendKeys(lName);
    }

    public void enterEmail(String emailAddress) {
        driver.findElement(email).sendKeys(emailAddress);
    }

    public void enterPassword(String pwd) {
        driver.findElement(password).sendKeys(pwd);
    }

    public void enterConfirmPassword(String confirmPwd) {
        driver.findElement(confirmPassword).sendKeys(confirmPwd);
    }

    public void clickSignUp() {
        driver.findElement(signUpButton).click();
    }
}
