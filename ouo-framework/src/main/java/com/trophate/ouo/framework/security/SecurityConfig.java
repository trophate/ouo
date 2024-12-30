package com.trophate.ouo.framework.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trophate.ouo.framework.result.Result;
import com.trophate.ouo.framework.security.exceptions.NotLoginException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain chain(HttpSecurity http, CustomSecurityContextRepository repo, AuthenticationConfiguration authConfig)
            throws Exception {
        http
                .securityContext((securityContext) -> securityContext.securityContextRepository(repo))
                .addFilterBefore(new AcidBuilderFilter(), SecurityContextHolderAwareRequestFilter.class)
                .addFilterBefore(new CustomerUsernamePasswordAuthenticationFilter(authConfig.getAuthenticationManager(), repo),
                        SecurityContextHolderAwareRequestFilter.class)
                .authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
                .exceptionHandling((exceptionHandling) -> exceptionHandling
                        .authenticationEntryPoint(new AuthenticationEntryPoint() {
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
                )
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}
