package com.trophate.ouo.framework.jpa;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories("**.repository")
@EnableTransactionManagement
public class JpaConfig {

}
