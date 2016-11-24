package org.wefine.spring.config.security;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
 
public class LoginFormAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
     
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
    throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
    }
     
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed)
    throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
    }
     
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String id = request.getParameter("captcha");
        HttpSession session = request.getSession();
        int rand1 = Integer.parseInt((String) session.getAttribute("rand1"));
        int rand2 = Integer.parseInt((String) session.getAttribute("rand2"));
        if (Integer.valueOf(id) != rand1 + rand2) {
            throw new AuthenticationServiceException("Please correctly answer: "+rand1+ "+"+rand2);
        }
        return super.attemptAuthentication(request, response);
    }
}