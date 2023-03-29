package com.example.livecode_ecommerce.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Setter @Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "email", referencedColumnName = "email")
    private Auth auth;

    @Column(name = "phone")
    private String phone;

    public User(Long id, String name, Auth auth, String phone) {
        this.id = id;
        this.name = name;
        this.auth = auth;
        this.phone = phone;
    }

    public User() {
    }
}
