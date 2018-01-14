package com.altarix.services.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Service
public class StatelessTokenBasedAuthStrategy extends GenericFilterBean{
    @Autowired
    UserService userService;

    private final TokenAuthService tokenAuthService;

    public StatelessTokenBasedAuthStrategy(TokenAuthService tokenAuthService) {
        this.tokenAuthService = tokenAuthService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(tokenAuthService.getAuthentication((HttpServletRequest) servletRequest).orElse(null));
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
