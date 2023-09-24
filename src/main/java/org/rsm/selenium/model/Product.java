package org.rsm.selenium.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    private String name;
    private double price;
    private String currency;

    public Product(String name, String price) {
        this.name = name;
        currency = price.replaceAll("\\d+\\.?\\d*", "");
        this.price = Double.valueOf(price.replaceAll(".*(?=\\d+\\.\\d)", ""));
    }

}
