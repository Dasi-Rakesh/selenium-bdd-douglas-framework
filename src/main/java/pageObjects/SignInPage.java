package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignInPage {
    private WebDriver driver;

    // Constructor
    public SignInPage(WebDriver driver) {
        this.driver = driver;
    }

    // Page elements
    private By emailField = By.id("email");
    private By passwordField = By.id("pass");
    private By signInButton = By.xpath("(//span[text()='Sign In'])[1]");

    // Actions
    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickSignIn() {
        driver.findElement(signInButton).click();
    }
}
