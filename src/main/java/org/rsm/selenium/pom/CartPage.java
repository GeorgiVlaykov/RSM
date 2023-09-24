package org.rsm.selenium.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.rsm.selenium.model.Product;

import java.time.Duration;

public class CartPage extends AmazonPage {

    @FindBy(css = "h1")
    WebElement pageTitle;

    @FindBy(css = "span.a-truncate-cut")
    WebElement productName;
    @FindBy(css = "span.sc-product-price")
    WebElement productPrice;

    public CartPage(WebDriver driver) {
        super(driver);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(d -> pageTitle.isDisplayed());
    }


    public Product getProductInfo() {
        return new Product(productName.getText(), productPrice.getText());
    }
}
