package com.example.klinika;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class FiltrJwt extends OncePerRequestFilter {

    private final JwtSerwis jwtSerwis;

    public FiltrJwt(JwtSerwis jwtSerwis) {
        this.jwtSerwis = jwtSerwis;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String naglowek = request.getHeader("Authorization");

        if (naglowek != null && naglowek.startsWith("Bearer ")) {
            String token = naglowek.substring(7);

            if (jwtSerwis.czyPoprawny(token)) {
                String login = jwtSerwis.pobierzLogin(token);
                String rola = jwtSerwis.pobierzRole(token);

                var uwierzytelnienie = new UsernamePasswordAuthenticationToken(
                        login,
                        null,
                        AuthorityUtils.createAuthorityList("ROLE_" + rola)
                );
                SecurityContextHolder.getContext().setAuthentication(uwierzytelnienie);
            }
        }

        filterChain.doFilter(request, response);

    }
}