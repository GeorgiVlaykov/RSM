package org.rsm.selenium.pom.productPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.rsm.selenium.pom.AmazonPage;

import java.time.Duration;

public class HomePage extends AmazonPage {
    @FindBy(xpath = "/html/body/div[1]/header/div/div[4]/div[1]/div/div/div[3]/span[1]/span")
    WebElement acceptAlertButton;
    @FindBy(id = "sp-cc-accept")
    WebElement acceptCookiesButton;
    @FindBy(id = "navbar-main")
    WebElement header;
    @FindBy(id = "nav-logo-sprites")
    WebElement amazonLogo;
    @FindBy(id = "navFooter")
    WebElement footer;


    public HomePage(WebDriver driver) {
        super(driver);

        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(d -> amazonLogo.isDisplayed());
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(d -> header.isDisplayed());
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(d -> footer.isDisplayed());

        confirmDeliveryCountry();
        acceptCookies();
    }

    public void confirmDeliveryCountry() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(d -> acceptAlertButton.isDisplayed());
        acceptAlertButton.click();
    }

    public void acceptCookies() {
        acceptCookiesButton.click();
    }


}
