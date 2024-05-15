package com.item.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EntityScan("com.common.domain.item")
@ComponentScan(basePackages = {"com.common", "com.item"})
@EnableJpaAuditing
public class ItemConfig {
}
