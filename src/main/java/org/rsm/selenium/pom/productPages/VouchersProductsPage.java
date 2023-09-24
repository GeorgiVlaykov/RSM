package org.rsm.selenium.pom.productPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.rsm.selenium.pom.AmazonPage;

import java.time.Duration;

public class VouchersProductsPage extends AmazonPage {

    @FindBy(css = "h1")
    WebElement pageTitle;

    public VouchersProductsPage(WebDriver driver) {
        super(driver);
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(d -> pageTitle.isDisplayed() && pageTitle.getText().equalsIgnoreCase("Amazon Vouchers"));
    }


}
