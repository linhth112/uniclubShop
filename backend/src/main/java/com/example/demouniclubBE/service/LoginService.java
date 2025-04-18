package com.example.demouniclubBE.service;

import com.example.demouniclubBE.entity.RoleEntity;
import com.example.demouniclubBE.entity.UserEntity;
import com.example.demouniclubBE.payload.request.SignUpRequest;
import com.example.demouniclubBE.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean insertUser(SignUpRequest signUpRequest) {
        try {
            String email = signUpRequest.getEmail().toLowerCase().trim();

            UserEntity existingUser = userRepository.findByEmail(email);
            if (existingUser != null) {
                return false;
            }

            String passwordEncoded = passwordEncoder.encode(signUpRequest.getPassword());

            UserEntity newUser = new UserEntity();
            newUser.setEmail(email);
            newUser.setPassword(passwordEncoded);

            RoleEntity userRole = new RoleEntity();
            userRole.setId(1);
            newUser.setRole(userRole);

            userRepository.save(newUser);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
