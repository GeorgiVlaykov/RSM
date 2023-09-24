package org.rsm.selenium.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.rsm.selenium.model.Product;

import java.time.Duration;

public class ProductDetailsPage extends AmazonPage {
    @FindBy(id = "productTitle")
    WebElement productName;
    @FindBy(css = "span.a-price>span.a-offscreen")
    WebElement productPrice;
    @FindBy(id = "add-to-cart-button")
    WebElement addToCartButton;
    @FindBy(id = "gift-wrap")
    WebElement addGiftOptions;


    public ProductDetailsPage(WebDriver driver) {
        super(driver);

        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(d -> productName.isDisplayed());
    }

    public Product getProductInfo() {
        return new Product(productName.getText(), productPrice.getDomProperty("innerText"));
    }

    public void addToCart(boolean gift) {
        if (gift) addGiftOptions.click();
        addToCartButton.click();
    }
}
