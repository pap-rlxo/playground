package com.discovery.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = {"com.common.domain.item", "com.common.domain.user"})
@ComponentScan(basePackages = {"com.common", "com.discovery"})
@EnableJpaRepositories(basePackages = {"com.common.repository.item", "com.common.repository.user"})
@EnableJpaAuditing
public class DiscoveryConfig {
}
