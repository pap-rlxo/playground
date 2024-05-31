package com.discovery.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = {"com.common.domain.discovery", "com.common.domain.user"})
@ComponentScan(basePackages = {"com.common", "com.discovery"})
@EnableJpaRepositories(basePackages = {"com.common.repository.user", "com.common.repository.discovery"})
@EnableJpaAuditing
public class DiscoveryConfig {
}
