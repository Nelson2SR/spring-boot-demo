package com.dolphintech.cache.caffeine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author rong.su 4/7/22
 */
@SpringBootApplication
@EnableCaching
public class SprintBootDemoCacheCaffeineApplication {

    public static void main(String[] args) {
        SpringApplication.run(SprintBootDemoCacheCaffeineApplication.class, args);
    }
}
