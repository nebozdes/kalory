package com.matveev.kalory.config;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Collections;

import static com.matveev.kalory.domain.entity.UserRole.ADMINISTRATOR;
import static com.matveev.kalory.domain.entity.UserRole.CLIENT;
import static com.matveev.kalory.domain.entity.UserRole.MODERATOR;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Override
    @SneakyThrows
    protected void configure(HttpSecurity http) {
        http
                .cors().configurationSource(corsConfigurationSource()).and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(authenticationSuccessHandler())
                .failureHandler(authenticationFailureHandler())
                .loginProcessingUrl("/login")
                .and()
                .logout()
                .logoutUrl("/perform_logout")
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler())
                .authenticationEntryPoint(new Http403ForbiddenEntryPoint())
        ;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList(
                "accept", "cache-Control", "authorization", "content-type", "x-auth-token", "cookie", "Set-Cookie",
                "content-disposition", "dnt", "if-modified-since", "keep-alive", "origin", "user-agent", "x-requested-with"));
        configuration.setExposedHeaders(Collections.singletonList("x-auth-token"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(86400L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    // In-memory authentication to authenticate the user i.e. the user credentials are stored in the memory.
    @Override
    @SneakyThrows
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.inMemoryAuthentication().withUser("admin").password("{noop}password")
                .roles(CLIENT.getAuthority(), MODERATOR.getAuthority(), ADMINISTRATOR.getAuthority());
        auth.inMemoryAuthentication().withUser("client").password("{noop}password")
                .roles(CLIENT.getAuthority());
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (httpServletRequest, httpServletResponse, e) -> httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return (httpServletRequest, httpServletResponse, e) -> httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (httpServletRequest, httpServletResponse, e) -> {
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setHeader("Access-Control-Allow-Origin", httpServletRequest.getHeader("Origin"));
            httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
            httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, DELETE");
            httpServletResponse.setHeader("Access-Control-Allow-Headers",
                    "Accept, Authorization, Cache-Control, Content-Type, Content-Disposition, DNT, If-Modified-Since, "
                            + "Keep-Alive, Origin, User-Agent, X-Requested-With, Cookie, Set-Cookie");
            httpServletResponse.setHeader("Access-Control-Max-Age", "86400");
            httpServletResponse.setContentType("application/json");
        };
    }
}
