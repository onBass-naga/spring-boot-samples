package com.example.app.purchase;

/**
 * Created by naga on 2015/02/03.
 */
public class Production {

    private Long id;

    private String name;

    private Integer price;

    public Production(Long id, String name, Integer price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }
}
