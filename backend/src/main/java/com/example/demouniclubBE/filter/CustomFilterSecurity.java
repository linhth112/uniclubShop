package com.example.demouniclubBE.filter;

import com.example.demouniclubBE.utils.JwtHelper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomFilterSecurity extends OncePerRequestFilter {

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorValue = request.getHeader("Authorization");
//        System.out.println("Kiểm tra filter " + authorValue);

        if (authorValue != null && authorValue.startsWith("Bearer ")) {
            String token = authorValue.substring(7);
            if (!token.isEmpty()) {
                String roleName = jwtHelper.deCodeToken(token);
                if (!roleName.isEmpty()) {
                    List<GrantedAuthority> listRole = new ArrayList<>();
                    SimpleGrantedAuthority role = new SimpleGrantedAuthority(roleName);

                    listRole.add(role);

                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken("","", listRole);

                    SecurityContext context = SecurityContextHolder.getContext();
                    context.setAuthentication(authenticationToken);
                }
            }
        }

        filterChain.doFilter(request,response);
    }
}
