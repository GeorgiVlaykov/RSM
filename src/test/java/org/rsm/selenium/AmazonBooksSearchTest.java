package org.rsm.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.rsm.selenium.model.Product;
import org.rsm.selenium.model.ProductCategories;
import org.rsm.selenium.pom.CartPage;
import org.rsm.selenium.pom.ProductDetailsPage;
import org.rsm.selenium.pom.SearchResultsPage;
import org.rsm.selenium.pom.productPages.BooksProductsPage;
import org.rsm.selenium.pom.productPages.HomePage;
import org.rsm.selenium.util.DriverUtil;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AmazonBooksSearchTest {
    private WebDriver driver;

    @BeforeEach
    public void beforeEach() {
        driver = DriverUtil.getDriver();
        driver.get("https://www.amazon.co.uk/");
    }

    @AfterEach
    public void afterEach() {
        driver.quit();
    }

    @ParameterizedTest
    @CsvSource(value = {
            "Harry Potter and the Cursed Child - Parts One and Two: The Official Playscript of the Original West End Production,Paperback",
    })
    public void verifyBookSearch(String productName, String productType) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, InterruptedException {
        HomePage homePage = new HomePage(driver);
        assertEquals(homePage.getCartProductsCount(), 0,
                "Expected cart to be empty.");
        assertTrue(homePage.getSearchCategory().equalsIgnoreCase(ProductCategories.ALL.toString()),
                "Products category in the search bar is different.");

        BooksProductsPage booksProductsPage = (BooksProductsPage) homePage.openProductCategory(ProductCategories.BOOKS);
        assertTrue(booksProductsPage.getSearchCategory().equalsIgnoreCase(ProductCategories.BOOKS.toString()),
                "Products category in the search bar is different.");

        SearchResultsPage searchResultsPage = booksProductsPage.searchProduct(productName);
        Product productSearchResultPageInfo = searchResultsPage.getFirstProductInfo();
        assertTrue(productSearchResultPageInfo.getName().equalsIgnoreCase(productName),
                "Product names don't match.");


        ProductDetailsPage productDetailsPage = searchResultsPage.openFirstProduct(productType);
        Product productDetailsPageInfo = productDetailsPage.getProductInfo();
        assertTrue(productSearchResultPageInfo.equals(productDetailsPageInfo),
                "Products are not equal: " +
                        "\nproductSearchResultPageInfo:" + productSearchResultPageInfo +
                        "\nproductDetailsPageInfo:" + productDetailsPageInfo
        );

        productDetailsPage.addToCart(true);
        assertEquals(homePage.getCartProductsCount(), 1,
                "Expected cart to have a single product.");

        CartPage cartPage = productDetailsPage.openCart();
        Product productInfoFromCart = cartPage.getProductInfo();
        assertTrue(productSearchResultPageInfo.equals(productDetailsPageInfo),
                "Products are not equal: " +
                        "\nproductSearchResultPageInfo:" + productSearchResultPageInfo +
                        "\nproductInfoFromCart:" + productInfoFromCart
        );
    }
}
