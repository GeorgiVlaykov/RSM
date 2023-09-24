package org.rsm.selenium.pom.productPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.rsm.selenium.pom.AmazonPage;

import java.time.Duration;

public class MusicProductsPage extends AmazonPage {

    @FindBy(css = "span[original-text='Sign up and pay later']")
    WebElement signupButton;

    public MusicProductsPage(WebDriver driver) {
        super(driver);
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(d -> signupButton.isDisplayed());
    }


}
