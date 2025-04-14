package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

public class ParfumPage extends BasePage {

    // Filter locators
    private By priceFilter = By.cssSelector("[data-testid='priceValue']");
    private By productTypeFilter = By.cssSelector("[data-testid='classificationClassName']");
    private By brandFilter = By.cssSelector("[data-testid='brand']");
    private By forWhomFilter = By.cssSelector("[data-testid='forWhom']");
    private By specialFilter = By.cssSelector("[data-testid='specialFilters']");
    private By filterInput = By.cssSelector("input.L0cGE_g_MG_wpv4Hq1Rn");

    // Price range fields
    private By priceFrom = By.cssSelector("[data-testid='preis-from']");
    private By priceTo = By.cssSelector("[data-testid='preis-to']");
    private By saveButton = By.xpath("//button[contains(text(), 'Speichern')]");

    // Product locators
    private By productCards = By.cssSelector("[data-testid='product-card']");
    private By productNames = By.cssSelector("[data-testid='product-name']");
    private By productPrices = By.cssSelector("[data-testid='priceValue']");
    private By productLink = By.xpath("(//*[@data-testid='image-link'])[1]");

    public ParfumPage(WebDriver driver) {
        super(driver);
    }

    public By getCategoryLocator(String category) {
        return By.xpath(String.format("//a[contains(text(), '%s')]", category));
    }

    public By getPriceFilter() {
        return priceFilter;
    }

    public By getPriceFrom() {
        return priceFrom;
    }

    public By getPriceTo() {
        return priceTo;
    }

    public By getSaveButton() {
        return saveButton;
    }

    public By getProductTypeFilter() {
        return productTypeFilter;
    }

    public By getFilterOptionByText(String text) {
        return By.xpath("//a[@role='checkbox' and .//div[text()='" + text + "']]");
    }

    public By getBrandFilter() {
        return brandFilter;
    }

    public By getFilterInput() {
        return filterInput;
    }

    public By getFilterOption(String text) {
        return By.xpath("//a[@role='checkbox' and .//div[text()='" + text + "']]");
    }

    public By getActiveFilter(String filterName) {
        return By.xpath(String.format("//button[contains(text(), '%s')]", filterName));
    }

    public By getProductLink() {
        return productLink;
    }

    public List<String> getProductNames() {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productNames))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<String> getProductPrices() {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productPrices))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public int getProductCount() {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productCards)).size();
    }
}
