package com.trophate.ouo.framework.security;

import com.trophate.ouo.framework.commons.utils.HttpUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AcidBuilderFilter extends GenericFilterBean {

    public static String acid;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        doFilter((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }

    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        Cookie acidCookie = HttpUtils.getCookie(request, SecurityConstant.AUTH_COOKIE_NAME);
        if (acidCookie == null) {
            acid = RandomStringUtils.randomAlphabetic(10) + System.currentTimeMillis();
        } else {
            acid = acidCookie.getValue();
        }
        acidCookie = new Cookie(SecurityConstant.AUTH_COOKIE_NAME, acid);
        acidCookie.setPath("/");
        response.addCookie(acidCookie);
        chain.doFilter(request, response);
    }
}
