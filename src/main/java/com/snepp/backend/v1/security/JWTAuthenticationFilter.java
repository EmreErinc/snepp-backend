package com.snepp.backend.v1.security;

import com.snepp.backend.v1.service.UserService;
import com.snepp.backend.v1.service.UserServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.snepp.backend.v1.security.SecurityConstants.HEADER_STRING;
import static com.snepp.backend.v1.security.SecurityConstants.TOKEN_PREFIX;

/**
 * Created by emre on 15.02.2019
 */
public class JWTAuthenticationFilter extends OncePerRequestFilter {

  //@Autowired
  @Resource(name = "userService")
  private UserDetailsService userDetailsService;

  @Autowired
  private UserServiceImpl userServiceImpl;

  @Autowired
  private JwtTokenProvider tokenProvider;

  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {
    String header = request.getHeader(HEADER_STRING);
    //String userId = null;
    String username = null;
    String accessToken = null;

    if (header != null && header.startsWith(TOKEN_PREFIX)) {
      accessToken = header.replace(TOKEN_PREFIX, "");
      try {
        //userId = tokenProvider.getIdFromToken(accessToken);
        username = tokenProvider.getUsernameFromToken(accessToken);
      } catch (IllegalArgumentException e) {
        logger.error("an error occured during getting username from token", e);
      } catch (ExpiredJwtException e) {
        logger.warn("the token is expired and not valid anymore", e);
      } catch (SignatureException e) {
        logger.error("Authentication Failed. Username or Password not valid.");
      }
    } else {
      logger.warn("couldn't find bearer string, will ignore the header");
    }

    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = userDetailsService.loadUserByUsername(username);
      //UserDetails userDetails = userServiceImpl.loadUserByUsername(username);


      if (tokenProvider.validateToken(accessToken, userDetails)) {
        UsernamePasswordAuthenticationToken authentication = tokenProvider.getAuthentication(accessToken, userDetails);
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        logger.info("authenticated user " + username + ", setting security context");
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    }

    filterChain.doFilter(request, response);
  }
}

