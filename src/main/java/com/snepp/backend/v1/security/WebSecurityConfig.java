package com.snepp.backend.v1.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import static com.snepp.backend.v1.security.SecurityConstants.*;

/**
 * Created by emre on 15.02.2019
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//  @Resource(name = "userService")
//  private UserDetailsService userDetailsService;

  @Autowired
  private JWTAuthenticationEntryPoint authenticationEntryPoint;

  @Autowired
  JwtTokenProvider tokenProvider;

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  public JWTAuthenticationFilter authenticationFilterBean(){
    return new JWTAuthenticationFilter();
  }

  //??
//  public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
//    auth.userDetailsService(userDetailsService);//password encode will add
//  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .cors().and().csrf().disable()
        .authorizeRequests()
        .antMatchers(SIGN_IN_URL).permitAll()
        .antMatchers(SIGN_UP_URL).permitAll()
        .anyRequest().authenticated()
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
        //.exceptionHandling().accessDeniedPage(SIGN_IN_URL);
        //.and()

    http.addFilterBefore(authenticationFilterBean(), UsernamePasswordAuthenticationFilter.class);
  }
}
