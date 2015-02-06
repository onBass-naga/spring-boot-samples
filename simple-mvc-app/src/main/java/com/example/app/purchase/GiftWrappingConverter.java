package com.example.app.purchase;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Created by naga on 2015/02/06.
 */
@Converter
public class GiftWrappingConverter implements AttributeConverter<GiftWrapping, String> {

    public String convertToDatabaseColumn(GiftWrapping attribute) {
        return attribute == null
                ? null : attribute.getCode();
    }

    public GiftWrapping convertToEntityAttribute(String value) {
        return value == null
                ? null : GiftWrapping.of(value);
    }
}

