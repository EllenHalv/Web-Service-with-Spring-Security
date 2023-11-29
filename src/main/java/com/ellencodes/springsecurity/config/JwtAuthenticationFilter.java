package com.ellencodes.springsecurity.config;

import com.ellencodes.springsecurity.service.JwtService;
import com.ellencodes.springsecurity.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * A Spring Security filter for JWT authentication.
 * This filter intercepts incoming requests, extracts JWTs, and validates them.
 * If a valid JWT is present, it sets up the Spring Security context for authentication.
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserService userService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        // Extracts the JWT token from the request header
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        // Checks if the Authorization header is present and starts with "Bearer "
        if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, "Bearer ")) {
            // If not, continues with the filter chain
            filterChain.doFilter(request, response);
            return;
        }

        // Extracts the JWT token from the Authorization header
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUserName(jwt);

        // Checks if the user email is not empty and if the user is not already authenticated
        if (StringUtils.isNotEmpty(userEmail)
                && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Loads user details from the user service based on the extracted user email
            UserDetails userDetails = userService.userDetailsService().loadUserByUsername(userEmail);

            // Validates the JWT token for the user
            if (jwtService.isTokenValid(jwt, userDetails)) {
                // Creates a security context, authentication token, and sets it in the SecurityContextHolder
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                context.setAuthentication(authToken);
                SecurityContextHolder.setContext(context);
            }
        }

        // Continues with the filter chain
        filterChain.doFilter(request, response);
    }
}
