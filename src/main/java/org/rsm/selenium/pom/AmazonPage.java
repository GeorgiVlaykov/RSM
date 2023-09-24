package org.rsm.selenium.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.rsm.selenium.model.ProductCategories;

import java.lang.reflect.InvocationTargetException;

public class AmazonPage {
    protected WebDriver driver;
    // SEARCH
    @FindBy(id = "twotabsearchtextbox")
    WebElement searchInput;
    @FindBy(id = "nav-search-submit-text")
    WebElement searchButton;
    @FindBy(id = "searchDropdownBox")
    WebElement searchDropdown;
    // NAVBAR
    @FindBy(id = "nav-xshop-container")
    WebElement navBar;
    @FindBy(id = "nav-cart")
    WebElement cart;
    @FindBy(id = "nav-cart-count")
    WebElement cartProductsCount;

    public AmazonPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public SearchResultsPage searchProduct(String productName) {
        searchInput.sendKeys(productName);
        searchButton.click();
        return new SearchResultsPage(driver);
    }

    public String getSearchCategory() {
        return new Select(searchDropdown).getFirstSelectedOption().getText();
    }

    public AmazonPage openProductCategory(ProductCategories productCategory) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        navBar.findElement(By.linkText(productCategory.toString())).click();
        return productCategory.getPageClass().getDeclaredConstructor(WebDriver.class).newInstance(driver);
    }

    public CartPage openCart() {
        cart.click();
        return new CartPage(driver);
    }

    public int getCartProductsCount() {
        return Integer.parseInt(cartProductsCount.getText());
    }
}
