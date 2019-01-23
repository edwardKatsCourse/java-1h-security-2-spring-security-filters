package com.telran.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


public class SecurityFilter extends OncePerRequestFilter {

    //@Autowired
    //private UserRepository userRepository;

    private static final String VALID_TOKEN = "my_valid_token";

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain chain) throws ServletException, IOException {

        //interface Authentication

        String header = httpServletRequest.getHeader("Authorization");

        if (header != null) {

            if (header.equals(VALID_TOKEN)) {
                //>>>выдать КЛЮЧ тут<<<

                String usernameFromHeader = httpServletRequest.getHeader("java1h_username");

                if (usernameFromHeader == null) {
                    usernameFromHeader = "unknown user";
                }

                Authentication keyToPass = new UsernamePasswordAuthenticationToken(
                        usernameFromHeader,
                        null,
                        new ArrayList<>()
                );

                //1. username (for Principal in MyController)
                //2. credentials (always null)
                //3. Roles (ROLE_ADMIN) / authorities (ADMIN) - new ArrayList<>();

                SecurityContextHolder.getContext().setAuthentication(keyToPass);

            }

        }

        chain.doFilter(httpServletRequest, httpServletResponse);
    }
}
