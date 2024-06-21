package com.user.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration
@EntityScan(basePackages = {"com.common.domain"})
public class UserTestConfig {
}
