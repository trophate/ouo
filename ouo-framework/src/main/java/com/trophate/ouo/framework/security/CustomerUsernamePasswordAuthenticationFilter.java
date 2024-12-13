package com.trophate.ouo.framework.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trophate.ouo.framework.commons.utils.LogUtils;
import com.trophate.ouo.framework.security.exceptions.InvalidUsernameOrPasswordException;
import com.trophate.ouo.framework.result.Result;
import org.slf4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class CustomerUsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER =
            new AntPathRequestMatcher("/login", "POST");

    private boolean postOnly = true;

    private final Logger logger = LogUtils.getLogger(this);

    public CustomerUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(DEFAULT_ANT_PATH_REQUEST_MATCHER, authenticationManager);
    }

    {
        super.setAuthenticationSuccessHandler(new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
                    throws IOException, ServletException {
                var objectMapper = new ObjectMapper();
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                PrintWriter out = response.getWriter();
                Result res = Result.success();
                out.print(objectMapper.writeValueAsString(res));
                out.flush();
                out.close();
            }
        });
        super.setAuthenticationFailureHandler(new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
                    throws IOException, ServletException {
                var objectMapper = new ObjectMapper();
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                PrintWriter out = response.getWriter();
                Result res = Result.fail(new InvalidUsernameOrPasswordException());
                out.print(objectMapper.writeValueAsString(res));
                out.flush();
                out.close();
            }
        });
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        LoginDTO loginDTO = null;
        try {
            StringBuilder bodyJson = new StringBuilder();
            BufferedReader reader = request.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                bodyJson.append(line);
            }
            reader.close();
            var objectMapper = new ObjectMapper();
            loginDTO = objectMapper.readValue(bodyJson.toString(), LoginDTO.class);
        } catch (IOException e) {
            logger.error(e.getLocalizedMessage());
        }
        String username = "";
        String password = "";
        if (loginDTO != null) {
            username = loginDTO.getUsername() != null ? loginDTO.getUsername() : "";
            password = loginDTO.getPassword() != null ? loginDTO.getPassword() : "";
        }
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    protected void setDetails(HttpServletRequest request, UsernamePasswordAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }
}
