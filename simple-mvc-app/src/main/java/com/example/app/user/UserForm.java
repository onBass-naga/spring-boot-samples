package com.example.app.user;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Size;

/**
 * Created by naga on 2015/01/29.
 */
public class UserForm {

    @NumberFormat
    @Size(min = 1, max = 9)
    private String id;

    @Size(min = 1, max = 50)
    private String name;

    @NotBlank
    @Email
    private String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
