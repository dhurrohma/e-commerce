package com.example.livecode_ecommerce.repository;

import com.example.livecode_ecommerce.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
