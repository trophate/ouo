package com.trophate.ouo.framework.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    /**
     * 该方法获取当前用户。
     *
     * @return CurrentUser
     */
    public static CurrentUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null ? (CurrentUser) authentication.getPrincipal() : null;
    }
}
