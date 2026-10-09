package com.attendance.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class ApiAuthenticationFilter extends OncePerRequestFilter {
    public static final String PRINCIPAL_ATTRIBUTE = ApiAuthenticationFilter.class.getName() + ".principal";
    private final AuthTokenStore tokens;

    public ApiAuthenticationFilter(AuthTokenStore tokens) {
        this.tokens = tokens;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !request.getRequestURI().startsWith(request.getContextPath() + "/api/")
                || "OPTIONS".equalsIgnoreCase(request.getMethod())
                || ("POST".equalsIgnoreCase(request.getMethod())
                    && request.getRequestURI().equals(request.getContextPath() + "/api/auth/login"));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        AuthTokenStore.Principal principal = header != null && header.startsWith("Bearer ")
                ? tokens.find(header.substring(7).trim()) : null;
        if (principal == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"message\":\"Sign in to continue.\"}");
            return;
        }
        request.setAttribute(PRINCIPAL_ATTRIBUTE, principal);
        chain.doFilter(request, response);
    }
}
