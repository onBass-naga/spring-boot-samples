package com.example.app.purchase;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class GiftWrappingTest {

    @Test
    public void コード値から列挙子に変換出来る() throws Exception {

        GiftWrapping expected = GiftWrapping.CUTE;
        GiftWrapping actual = GiftWrapping.of("10");

        assertThat(actual, is(expected));
    }
}