package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.testng.Assert;
import pageObjects.SignUpPage;

public class SignUpStepDefinitions {
    WebDriver driver;
    SignUpPage signUpPage;
    static String uniqueEmail;

    @Given("user is on the signup page")
    public void user_is_on_the_signup_page() {
        try {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("https://magento.softwaretestingboard.com/customer/account/create/");
            signUpPage = new SignUpPage(driver);
            // Generate a unique email using the current timestamp
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            uniqueEmail = "sdetrakesh+" + LocalDateTime.now().format(formatter) + "@gmail.com";
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Failed to open signup page");
        }
    }

    @When("user enters first name {string}, last name {string}, email {string}, password {string}, and confirm password {string}")
    public void user_enters_signup_details(String firstName, String lastName, String email, String password, String confirmPassword) {
        try {
            signUpPage.enterFirstName(firstName);
            signUpPage.enterLastName(lastName);
            signUpPage.enterEmail(uniqueEmail);
            signUpPage.enterPassword(password);
            signUpPage.enterConfirmPassword(confirmPassword);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Failed to enter sign-up details");
        }
    }

    @And("clicks on the signup button")
    public void clicks_on_the_signup_button() {
        try {
            signUpPage.clickSignUp();           
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Failed to click on the sign-up button");
        }
    }

    @Then("the account is created successfully")
    public void the_account_is_created_successfully() {
        try {
            // Verify that a unique element on the welcome page or dashboard appears
            By welcomeMessage = By.cssSelector("div[data-ui-id=message-success]"); 
            Assert.assertTrue(driver.findElement(welcomeMessage).isDisplayed(), "Account creation failed: Welcome message not displayed.");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Account creation verification failed");
        } finally {
            driver.quit();
        }
    }
    
    public static String getUniqueEmail() {
        return uniqueEmail;
    }
}
