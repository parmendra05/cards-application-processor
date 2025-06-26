package com.banking.cards.application.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class CorrelationIDFilter extends OncePerRequestFilter {

    private final String CORRELATION_ID_HEADER = "X-Correlation-ID";
    private final String CORRELATION_ID_ATTRIBUTE = "correlationId";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String correlationId = UUID.randomUUID().toString();
        request.setAttribute(CORRELATION_ID_ATTRIBUTE, correlationId);
        response.setHeader(CORRELATION_ID_HEADER, correlationId);
        filterChain.doFilter(request, response);
    }
}
