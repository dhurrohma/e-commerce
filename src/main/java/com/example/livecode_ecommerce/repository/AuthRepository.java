package com.example.livecode_ecommerce.repository;

import com.example.livecode_ecommerce.model.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Auth, String> {
}
