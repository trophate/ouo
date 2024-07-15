package com.trophate.ouo.framework.security;

import com.trophate.ouo.framework.common.utils.HttpUtils;
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

public class SecridBuilderFilter extends GenericFilterBean {

    public static String secrid;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        doFilter((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }

    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        Cookie secridCookie = HttpUtils.getCookie(request, SecurityConstant.SECRID_COOKIE_NAME);
        if (secridCookie == null) {
            secrid = RandomStringUtils.randomAlphabetic(10) + System.currentTimeMillis();
        } else {
            secrid = secridCookie.getValue();
        }
        secridCookie = new Cookie(SecurityConstant.SECRID_COOKIE_NAME, secrid);
        response.addCookie(secridCookie);
        chain.doFilter(request, response);
    }
}
