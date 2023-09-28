package org.rsm.selenium.model;

import lombok.Getter;
import org.rsm.selenium.pom.AmazonPage;
import org.rsm.selenium.pom.productPages.BooksProductsPage;
import org.rsm.selenium.pom.productPages.HomePage;
import org.rsm.selenium.pom.productPages.MusicProductsPage;

public enum ProductCategories {
    ALL("All Departments", HomePage.class),
    BOOKS("Books", BooksProductsPage.class),
    MUSIC("Music", MusicProductsPage.class),
    VOUCHERS("Vouchers", MusicProductsPage.class),
    ;

    @Getter
    Class<? extends AmazonPage> pageClass;
    private String categoryText;

    ProductCategories(String categoryText, Class<? extends AmazonPage> pageClass) {
        this.categoryText = categoryText;
        this.pageClass = pageClass;
    }


    @Override
    public String toString() {
        return categoryText;
    }
}
