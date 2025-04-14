package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.testng.Assert;
import pageObjects.SignInPage;

public class SignInStepDefinitions {
    WebDriver driver;
    SignInPage signInPage;

    @Given("user is on the sign-in page")
    public void user_is_on_the_sign_in_page() {
        try {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("https://magento.softwaretestingboard.com/customer/account/login/");
            signInPage = new SignInPage(driver);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Failed to open sign-in page");
        }
    }   
 

    @When("user enters email {string} and password {string}")
    public void user_enters_email_and_password(String email, String password) {
        try {
        	  String emailToUse = SignUpStepDefinitions.getUniqueEmail(); // Retrieve the generated email
              signInPage.enterEmail(emailToUse);
              signInPage.enterPassword(password);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Failed to enter sign-in details");
        }
    }

    @And("clicks on the sign-in button")
    public void clicks_on_the_sign_in_button() {
        try {
            signInPage.clickSignIn();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Failed to click on the sign-in button");
        }
    }

    @Then("the user is successfully signed in")
    public void the_user_is_successfully_signed_in() {
        try {
            // Verify My Account Text
            By profileLink = By.xpath("//span[text()='My Account']");
            Assert.assertTrue(driver.findElement(profileLink).isDisplayed(), "Sign-in failed: Profile link not displayed.");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Sign-in verification failed");
        } finally {
            driver.quit();
        }
    }
}
