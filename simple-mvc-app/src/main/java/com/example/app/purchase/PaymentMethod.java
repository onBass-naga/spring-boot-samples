package com.example.app.purchase;

/**
 * Created by naga on 2015/02/01.
 */
public enum PaymentMethod {

    CARD("クレジットカード"),
    CHANGE("代金引換");

    private String label;

    private PaymentMethod(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
