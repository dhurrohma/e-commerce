package com.example.livecode_ecommerce.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "auth")
@Setter @Getter
public class Auth {
    @Id
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    public Auth(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Auth() {
    }
}
