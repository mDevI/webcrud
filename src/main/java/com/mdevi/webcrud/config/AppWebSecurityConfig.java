package com.mdevi.webcrud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class AppWebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService appUserDetailsService;

    @Autowired
    public AppWebSecurityConfig(UserDetailsService appUserDetailsService) {
        this.appUserDetailsService = appUserDetailsService;
    }


    @Autowired
    private DefaultWebSecurityExpressionHandler webSecurityExpressionHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(appUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .expressionHandler(webSecurityExpressionHandler);

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/h2/**").permitAll()
                .antMatchers("/static/**", "/webjars/**", "/resources/**", "/js/**", "/css/**", "/fonts").permitAll()
                .antMatchers("/").access("hasAnyRole('ADMIN', 'EDITOR', 'READER') and isFullyAuthenticated()")
                .antMatchers("/**").hasAnyRole("ADMIN", "EDITOR", "READER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error")
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/accessDenied");

        http
                .headers()
                .frameOptions()
                .disable();
    }

}
