package com.trophate.ouo.framework.common.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class HttpUtils {

    /**
     * 获取cookie
     *
     * @param request 请求
     * @param name cookie名
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
     * 获取cookie值
     *
     * @param request 请求
     * @param name cookie名
     * @return Cookie
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
