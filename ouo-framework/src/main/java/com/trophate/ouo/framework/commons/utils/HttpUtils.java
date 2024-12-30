package com.trophate.ouo.framework.commons.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class HttpUtils {

    /**
     * 通过名称获取Cookie对象。
     *
     * @param request request
     * @param name Cookie名称
     * @return Cookie
     */
    public static Cookie getCookie(HttpServletRequest request, String name) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }
        return null;
    }

    /**
     * 通过名称获取Cookie的值。
     *
     * @param request request
     * @param name Cookie名称
     * @return Cookie值
     */
    public static String getCookieVal(HttpServletRequest request, String name) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(name)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
