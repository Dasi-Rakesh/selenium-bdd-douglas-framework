package stepDefinitions;


import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.HomePage;
import pageObjects.ParfumPage;
import utils.ConfigReader;
import utils.DriverManager;

import java.time.Duration;
import java.util.List;

public class ParfumSteps {

    private WebDriver driver = DriverManager.getDriver();
    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    private Actions actions = new Actions(driver);
    private HomePage homePage = new HomePage(driver);
    private ParfumPage parfumPage = new ParfumPage(driver);

    @When("I click on the {string} category")
    public void clickParfumCategory(String category) {
        try {
            Thread.sleep(5000); /* Wait for the page to load */
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        By locator = parfumPage.getCategoryLocator(category);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        scrollIntoView(element);
        element.click();
        wait.until(ExpectedConditions.urlToBe("https://www.douglas.de/de/c/parfum/01"));
    }
    @Given("I navigate to Douglas home page")
    public void navigateToHome() {
        driver.get(ConfigReader.getProperty("base.url"));
    }

    @Given("I accept cookies")
    public void acceptCookies() {
        homePage.closePopupWithKeyboard();
    }

    @When("I apply price filter from {string} to {string}")
    public void applyPriceFilter(String minPrice, String maxPrice) {
        WebElement priceElement = wait.until(ExpectedConditions.elementToBeClickable(parfumPage.getPriceFilter()));
        scrollIntoView(priceElement);
        parfumPage.sleep(3500); // Wait for the price filter to be clickable
        priceElement.click();

        WebElement fromElement = wait.until(ExpectedConditions.elementToBeClickable(parfumPage.getPriceFrom()));
        scrollIntoView(fromElement);
        fromElement.click();
        clearField(fromElement);
        fromElement.sendKeys(minPrice + "-");

        WebElement toElement = wait.until(ExpectedConditions.elementToBeClickable(parfumPage.getPriceTo()));
        scrollIntoView(toElement);
        toElement.click();
        clearField(toElement);
        toElement.sendKeys(maxPrice);
        actions.sendKeys(Keys.BACK_SPACE).perform();

        WebElement saveElement = wait.until(ExpectedConditions.elementToBeClickable(parfumPage.getSaveButton()));
        scrollIntoView(saveElement);
        saveElement.click();
        parfumPage.waitForPageLoad(5);
        parfumPage.sleep(4000);
        wait.until(ExpectedConditions.urlContains("priceValue:"));
    }

    @When("I apply product type filter {string}")
    public void applyProductTypeFilter(String productType) {
        WebElement filterElement = wait.until(ExpectedConditions.elementToBeClickable(parfumPage.getProductTypeFilter()));
        scrollIntoView(filterElement);
        filterElement.click();
        parfumPage.sleep(2000); // Wait for the filter to be clickable
        WebElement filterOption = wait.until(ExpectedConditions.elementToBeClickable(parfumPage.getFilterOptionByText("Eau de Parfum")));
        scrollIntoView(filterOption);
        filterOption.click();

        wait.until(ExpectedConditions.urlContains("classificationClassName:" + productType.replace(" ", "+")));

        WebElement activeElement = wait.until(ExpectedConditions.elementToBeClickable(parfumPage.getActiveFilter(productType)));
        scrollIntoView(activeElement);
        activeElement.click();
    }

    @When("I apply brand filter {string}")
    public void applyBrandFilter(String brand) {
        WebElement filterElement = wait.until(ExpectedConditions.elementToBeClickable(parfumPage.getBrandFilter()));
        scrollIntoView(filterElement);
        filterElement.click();

        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(parfumPage.getFilterOption(brand)));
        scrollIntoView(option);
        option.click();

        wait.until(ExpectedConditions.urlContains("brand:"));

        WebElement activeElement = wait.until(ExpectedConditions.elementToBeClickable(parfumPage.getActiveFilter(brand)));
        scrollIntoView(activeElement);
        activeElement.click();
    }

    @When("I select the first product")
    public void selectFirstProduct() {
        WebElement productElement = wait.until(ExpectedConditions.elementToBeClickable(parfumPage.getProductLink()));
        scrollIntoView(productElement);
        productElement.click();
        // Updated (variant is optional):
        String regex = "https://www.douglas.de/de/p/\\d+(\\?variant=\\d+)?";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Increased to 30 sec
        wait.until(ExpectedConditions.urlMatches(regex));
    }

    @Then("I should be on the product details page")
    public void verifyProductDetailsPage()  {
        wait.until(ExpectedConditions.urlContains("/p/"));
        parfumPage.sleep(2000);
        driver.quit();
    }

    private void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center', behavior: 'smooth'});", element);
    }

    private void clearField(WebElement element) {
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        element.sendKeys(Keys.BACK_SPACE);
    }
}
