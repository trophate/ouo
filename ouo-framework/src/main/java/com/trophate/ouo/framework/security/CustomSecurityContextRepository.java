package com.trophate.ouo.framework.security;

import com.trophate.ouo.framework.commons.utils.HttpUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.context.DeferredSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Component
public class CustomSecurityContextRepository implements SecurityContextRepository {

    private SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();
    /**
     * 上下文缓存键
     */
    private final static String CONTEXT_CACHE_KEY = "auth:context";

    @Resource
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public DeferredSecurityContext loadDeferredContext(HttpServletRequest request) {
        Supplier<SecurityContext> supplier = () -> getContext(request);
        return new CustomSupplierDeferredSecurityContext(supplier, this.securityContextHolderStrategy);
    }

    @Deprecated
    @Override
    public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
        return null;
    }

    @Override
    public void saveContext(SecurityContext context, HttpServletRequest request, HttpServletResponse response) {
        String acid = AcidBuilderFilter.acid;
        redisTemplate.opsForHash().put(CONTEXT_CACHE_KEY, acid, context);
        redisTemplate.expire(CONTEXT_CACHE_KEY, SecurityConstant.CONTEXT_CACHE_SURVIVAL_TIME, TimeUnit.SECONDS);
    }

    @Override
    public boolean containsContext(HttpServletRequest request) {
        return getContext(request) != null;
    }

    /**
     * 获取上下文
     */
    private SecurityContext getContext(HttpServletRequest request) {
        String acid = HttpUtils.getCookieVal(request, SecurityConstant.AUTH_COOKIE_NAME);
        SecurityContext context = null;
        if (acid != null) {
            context = redisTemplate.<String, SecurityContext>opsForHash().get(CONTEXT_CACHE_KEY, acid);
            if (context != null) {
                redisTemplate.expire(CONTEXT_CACHE_KEY, SecurityConstant.CONTEXT_CACHE_SURVIVAL_TIME, TimeUnit.SECONDS);
            }
        }
        return context;
    }

    protected SecurityContext generateNewContext() {
        return this.securityContextHolderStrategy.createEmptyContext();
    }
}
