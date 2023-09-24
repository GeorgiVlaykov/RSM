package org.rsm.selenium.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.rsm.selenium.model.Product;

import java.time.Duration;

public class SearchResultsPage extends AmazonPage {
    @FindBy(css = "span[data-component-type='s-search-results']")
    WebElement searchResults;

    @FindBy(css = "div[data-cel-widget='search_result_1']")
    WebElement firstResult;

    public SearchResultsPage(WebDriver driver) {
        super(driver);

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(d -> searchResults.isDisplayed());
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(d -> firstResult.isDisplayed());
    }

    public ProductDetailsPage openFirstProduct() {
        firstResult.findElement(By.cssSelector("h2>a>span")).click();
        return new ProductDetailsPage(driver);
    }

    public ProductDetailsPage openFirstProduct(String productType) {
        firstResult.findElement(By.linkText(productType)).click();
        return new ProductDetailsPage(driver);
    }

    public Product getFirstProductInfo() {
        String productName = firstResult.findElement(By.cssSelector("h2>a>span")).getText();
        String price = firstResult.findElement(By.cssSelector("span.a-price>span.a-offscreen")).getDomProperty("innerText");
        return new Product(productName, price);

    }
}
