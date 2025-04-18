package com.example.demouniclubBE.controller;

import com.example.demouniclubBE.entity.RoleEntity;
import com.example.demouniclubBE.entity.UserEntity;
import com.example.demouniclubBE.payload.request.SignUpRequest;
import com.example.demouniclubBE.payload.response.BaseResponse;
import com.example.demouniclubBE.service.LoginService;
import com.example.demouniclubBE.utils.JwtHelper;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.rsocket.RSocketSecurity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;

@CrossOrigin
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private LoginService loginService;

    @Autowired
    private JwtHelper jwtHelper;

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestParam String username, @RequestParam String password) {

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username,password);

        authenticationManager.authenticate(token);

        UserEntity userEntity = loginService.findByEmail(username);
        String roleUser = userEntity.getRole().getName();
        int idUser = userEntity.getId();

        String jwtToken = jwtHelper.generateToken(idUser, roleUser);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setData(jwtToken);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        boolean isSuccess = loginService.insertUser(signUpRequest);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage("");
        baseResponse.setData(isSuccess);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

}
