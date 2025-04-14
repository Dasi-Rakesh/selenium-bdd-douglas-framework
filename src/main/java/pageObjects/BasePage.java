package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    /**
     * Waits for specified milliseconds (use sparingly - prefer explicit waits)
     * @param milliseconds Time to wait in milliseconds
     */
    public void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Thread was interrupted during sleep", e);
        }
    }

    /**
     * Waits for page to fully load with a timeout
     * @param timeoutInSeconds Maximum time to wait
     */
    public void waitForPageLoad(long timeoutInSeconds) {
        sleep(500); // Initial buffer
        wait.withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .until(driver -> {
                    String state = (String) ((JavascriptExecutor) driver)
                            .executeScript("return document.readyState");
                    return state.equals("complete");
                });
    }
}