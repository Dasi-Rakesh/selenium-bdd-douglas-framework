package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.AlertHandler;

import java.time.Duration;

public class HomePage extends  BasePage{
    public HomePage(WebDriver driver) {
        super(driver);
    }

    // Page elements
    public By acceptCookiesBtn = By.xpath("//button[@data-testid='uc-accept-all-button']");
    private By parfumCategory = By.xpath("//a[contains(text(),'Parfum')]");

    // Actions
    public void acceptCookies() throws InterruptedException {
        Thread.sleep(6000); // Wait for the page to load
        AlertHandler.acceptAlertIfPresent(driver);
    }

    public boolean isElementInsideShadowDOM(By locator) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script =
                "const element = document.querySelector(arguments[0]); " +
                        "if (!element) { " +
                        "   const shadowHost = document.querySelector('usercentrics-root, [data-testid*=\"shadow\"]'); " +
                        "   if (shadowHost && shadowHost.shadowRoot) { " +
                        "       return !!shadowHost.shadowRoot.querySelector(arguments[0]); " +
                        "   } " +
                        "} " +
                        "return !!element;";

        return (Boolean) js.executeScript(script, locator.toString().replace("By.cssSelector: ", ""));
    }

    public void closePopupWithKeyboard() {
        try {
            sleep(3500); // Wait for the page to load
            // 1. Explicitly focus the current window
            ((JavascriptExecutor) driver).executeScript("window.focus();");

            // 3. Wait for focus to stabilize
            new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(d -> ((JavascriptExecutor) driver)
                            .executeScript("return document.activeElement === document.body;"));
            Thread.sleep(4000);
            // 4. Perform keyboard navigation
            Actions actions = new Actions(driver);
            for (int i = 0; i < 6; i++) {
                actions.sendKeys(Keys.TAB).pause(Duration.ofMillis(800)).perform();
            }
            actions.sendKeys(Keys.ENTER).perform();

        } catch (Exception e) {
            throw new RuntimeException("Keyboard navigation failed", e);
        }
    }


    public void navigateToParfum() throws InterruptedException {
        Thread.sleep(5000); // Wait for the page to load
        wait.until(ExpectedConditions.elementToBeClickable(parfumCategory)).click();
    }

}
