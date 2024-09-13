package com.trophate.ouo.framework.security;

import com.trophate.ouo.framework.common.utils.HttpUtils;
import org.springframework.core.log.LogMessage;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@Component
public class CustomSecurityContextRepository extends HttpSessionSecurityContextRepository {

    /**
     * 上下文缓存键
     */
    private final static String CONTEXT_CACHE_KEY = "auth:context";

    @Resource
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
        SecurityContext context = getContext(requestResponseHolder.getRequest());
        if (context == null) {
            context = generateNewContext();
            if (this.logger.isTraceEnabled()) {
                this.logger.trace(LogMessage.format("Created %s", context));
            }
        }
        return context;
    }

    @Override
    public void saveContext(SecurityContext context, HttpServletRequest request, HttpServletResponse response) {
        String secrid = SecridBuilderFilter.secrid;
        redisTemplate.opsForHash().put(CONTEXT_CACHE_KEY, secrid, context);
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
        String secrid = HttpUtils.getCookieVal(request, SecurityConstant.AUTH_COOKIE_NAME);
        SecurityContext context = null;
        if (secrid != null) {
            context = redisTemplate.<String, SecurityContext>opsForHash().get(CONTEXT_CACHE_KEY, secrid);
            if (context != null) {
                redisTemplate.expire(CONTEXT_CACHE_KEY, SecurityConstant.CONTEXT_CACHE_SURVIVAL_TIME, TimeUnit.SECONDS);
            }
        }
        return context;
    }
}
