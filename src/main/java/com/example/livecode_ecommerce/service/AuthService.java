package com.example.livecode_ecommerce.service;

import com.example.livecode_ecommerce.model.dto.LoginRequest;
import com.example.livecode_ecommerce.model.dto.RegisterRequest;
import com.example.livecode_ecommerce.model.entity.Auth;
import com.example.livecode_ecommerce.model.entity.User;
import com.example.livecode_ecommerce.repository.AuthRepository;
import com.example.livecode_ecommerce.utils.validation.JwtUtil;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @Transactional
    public String register(RegisterRequest registerRequest) {
        try {
            Auth auth = modelMapper.map(registerRequest, Auth.class);
            Auth authResult = authRepository.save(auth);

            User user = modelMapper.map(registerRequest, User.class);
            user.setAuth(authResult);
            userService.create(user);

            String token = jwtUtil.generateToken(user.getAuth().getEmail());
            return token;
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistsException();
        }
    }

    public String login(LoginRequest loginRequest) {
        try {
            Optional<Auth> auth = authRepository.findById(loginRequest.getEmail());
            if (auth.isEmpty()) throw new RuntimeException("User is not found");
            if (!auth.get().getPassword().equals(loginRequest.getPassword())) {
                throw new RuntimeException("Password is not match");
            }

            String token = jwtUtil.generateToken(loginRequest.getEmail());
            return token;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
