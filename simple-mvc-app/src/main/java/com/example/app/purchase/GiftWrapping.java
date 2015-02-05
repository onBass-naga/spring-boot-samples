package com.example.app.purchase;

import java.util.stream.Stream;

/**
 * Created by naga on 2015/02/05.
 */
public enum GiftWrapping {

    NONE("不要", "00"),
    CUTE("キュート柄", "10"),
    CHIC("シック柄", "20");

    private String label;
    private String code;

    private GiftWrapping(String label, String code) {
        this.label = label;
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public String getCode() {
        return code;
    }

    public static GiftWrapping of(String code) {

        // 本当はUNDIFINEを返すべきだか、Respos to BeanでNULLが入らなくするまでnullを返却
        if (code == null) { return null; }

        return Stream.of(values())
                .filter(type -> type.code.equals(code))
                .findFirst()
                .orElse(null);
    }
}
