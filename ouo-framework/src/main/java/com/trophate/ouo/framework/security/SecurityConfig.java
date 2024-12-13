package com.trophate.ouo.framework.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trophate.ouo.framework.security.exceptions.NotLoginException;
import com.trophate.ouo.framework.result.Result;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain chain(HttpSecurity http, CustomSecurityContextRepository repo, AuthenticationConfiguration authConfig)
            throws Exception {
        http
                .securityContext((context) -> context.securityContextRepository(repo))
                .addFilterBefore(new SecridBuilderFilter(), SecurityContextHolderAwareRequestFilter.class)
                .addFilterBefore(new CustomerUsernamePasswordAuthenticationFilter(authConfig.getAuthenticationManager()),
                        SecurityContextHolderAwareRequestFilter.class)
                .authorizeRequests()
                .anyRequest().authenticated()
                .and().exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint() {
                    @Override
                    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
                            throws IOException, ServletException {
                        var objectMapper = new ObjectMapper();
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        PrintWriter out = response.getWriter();
                        Result res = Result.fail(new NotLoginException());
                        out.print(objectMapper.writeValueAsString(res));
                        out.flush();
                        out.close();
                    }
                })
                .and().csrf().disable();
        return http.build();
    }
}
