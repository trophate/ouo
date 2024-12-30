package com.trophate.ouo.framework.security;

import com.trophate.ouo.framework.commons.utils.LogUtils;
import org.slf4j.Logger;
import org.springframework.core.log.LogMessage;
import org.springframework.security.core.context.DeferredSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolderStrategy;

import java.util.function.Supplier;

final class CustomSupplierDeferredSecurityContext implements DeferredSecurityContext {

    private final Supplier<SecurityContext> supplier;

    private final SecurityContextHolderStrategy strategy;

    private CustomSecurityContextRepository repo;

    private SecurityContext securityContext;

    private boolean missingContext;

    private final Logger logger = LogUtils.getLogger(this);

    CustomSupplierDeferredSecurityContext(Supplier<SecurityContext> supplier, SecurityContextHolderStrategy strategy) {
        this.supplier = supplier;
        this.strategy = strategy;
    }

    @Override
    public boolean isGenerated() {
        init();
        return this.missingContext;
    }

    @Override
    public SecurityContext get() {
        init();
        return this.securityContext;
    }

    private void init() {
        if (this.securityContext == null) {
            this.securityContext = this.supplier.get();
            this.missingContext = this.securityContext == null;
            if (this.missingContext) {
                this.securityContext = this.strategy.createEmptyContext();
                if (logger.isTraceEnabled()) {
                    logger.trace(String.valueOf(LogMessage.format("Created %s", this.securityContext)));
                }
            }

        }
    }
}
