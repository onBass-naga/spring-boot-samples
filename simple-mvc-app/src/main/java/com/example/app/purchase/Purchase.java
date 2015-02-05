package com.example.app.purchase;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by naga on 2015/01/29.
 */
@Entity
public class Purchase {

    @Id
    @GeneratedValue
    private Long id;

    @Type(type="com.example.app.purchase.GiftWrappingType")
    private GiftWrapping giftWrapping;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private Prefecture prefecture;

    @Size(max = 100)
    private String address;

    @Size(max = 50)
    private String name;

    @Size(max = 50)
    private String tel;

    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GiftWrapping getGiftWrapping() {
        return giftWrapping;
    }

    public void setGiftWrapping(GiftWrapping giftWrapping) {
        this.giftWrapping = giftWrapping;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Prefecture getPrefecture() {
        return prefecture;
    }

    public void setPrefecture(Prefecture prefecture) {
        this.prefecture = prefecture;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
