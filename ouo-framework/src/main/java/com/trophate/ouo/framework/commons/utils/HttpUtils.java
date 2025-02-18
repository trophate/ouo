package com.trophate.ouo.framework.commons.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class HttpUtils {

    /**
     * 该方法获取Cookie对象。
     *
     * @param request request
     * @param name cookie名称
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
     * 该方法获取cookie的值。
     *
     * @param request request
     * @param name cookie名称
     * @return cookie值
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
