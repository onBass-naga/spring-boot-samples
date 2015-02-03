package com.example.app.purchase;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Created by naga on 2015/02/01.
 */
public class ItemForm {

    private Long itemId;

    private String itemName;

    private Integer price;

    @Max(99)
    @Min(0)
    private Integer quantity = 1;

    public Boolean isLater;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Boolean getIsLater() {
        return isLater;
    }

    public void setIsLater(Boolean isLater) {
        this.isLater = isLater;
    }
}
