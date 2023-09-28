package org.rsm.selenium.pom.productPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.rsm.selenium.model.ProductCategories;
import org.rsm.selenium.pom.AmazonPage;

import java.time.Duration;

public class BooksProductsPage extends AmazonPage {
    @FindBy(css = "h1")
    WebElement pageTitle;

    public BooksProductsPage(WebDriver driver) {
        super(driver);
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(d -> pageTitle
                        .getText()
                        .equalsIgnoreCase(ProductCategories.BOOKS.toString()));
    }


}
