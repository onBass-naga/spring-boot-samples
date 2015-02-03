package com.example.app.purchase;

/**
 * Created by naga on 2015/02/01.
 */
public enum Prefecture {

    HOKKAIDO("北海道"),
    TOKYO("東京"),
    OKINAWA("沖縄");

    private String label;

    private Prefecture(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
