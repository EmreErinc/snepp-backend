package com.snepp.backend.v1.security;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.accessibility.AccessibleStreamable;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.snepp.backend.v1.security.SecurityConstants.*;

/**
 * Created by emre on 15.02.2019
 */
@Component
@Slf4j
public class JwtTokenProvider {
  /*public String generateToken(Authentication authentication){
    final String authorities = authentication.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.joining(","));

    return Jwts.builder()
        .setSubject(authentication.getName())
        //.setId(userId)
        .claim(AUTHORITIES, authorities)
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .signWith(SignatureAlgorithm.HS512, SECRET)
        .compact();
  }*/

  public String generateToken(String userId, String email, List<SimpleGrantedAuthority> authorityList){
    final String authorities = authorityList.stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.joining(","));

    return Jwts.builder()
        .setSubject(email)
        //.setId(userId)
        .claim(USER_ID, userId)
        .claim(AUTHORITIES, authorities)
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .signWith(SignatureAlgorithm.HS512, SECRET)
        .compact();
  }

  public String getIdFromToken(String token) {
    return Jwts.parser()
        .setSigningKey(SECRET)
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
  }

  public String getUsernameFromToken(String token) {
    return Jwts.parser()
        .setSigningKey(SECRET)
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
  }

  UsernamePasswordAuthenticationToken getAuthentication(final String token,
                                                        final UserDetails userDetails) {

    final Claims claims = Jwts
        .parser()
        .setSigningKey(SECRET)
        .parseClaimsJws(token)
        .getBody();

    final Collection<? extends GrantedAuthority> authorities =
        Arrays.stream(claims.get(AUTHORITIES).toString().split(","))
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());

    return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parser()
          .setSigningKey(SECRET)
          .parseClaimsJws(token);
      return true;
    } catch (SignatureException ex) {
      log.error("Invalid JWT signature");
    } catch (MalformedJwtException ex) {
      log.error("Invalid JWT token");
    } catch (ExpiredJwtException ex) {
      log.error("Expired JWT token");
    } catch (UnsupportedJwtException ex) {
      log.error("Unsupported JWT token");
    } catch (IllegalArgumentException ex) {
      log.error("JWT claims string is empty.");
    }
    return false;
  }

  public Boolean validateToken(String token, UserDetails userDetails) {
    /*final String userId = getIdFromToken(token);
    return (userId.equals(userDetails.getUsername()) && !isTokenExpired(token));*/

    final String username = getUsernameFromToken(token);
    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }

  private Boolean isTokenExpired(String token) {
    return Jwts
        .parser()
        .setSigningKey(SECRET)
        .parseClaimsJws(token)
        .getBody()
        .getExpiration().before(new Date());
  }
}
