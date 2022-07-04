package com.dolphintech.cache.caffeine.config;

import com.dolphintech.cache.caffeine.constant.Constant;
import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.concurrent.TimeUnit;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author rong.su 3/7/22
 */
@EnableCaching
@Configuration
public class CaffeineConfig {

  @Bean
  public Caffeine<Object, Object> loadingCacheConfig() {
    return Caffeine.newBuilder()
        .expireAfterWrite(Constant.CACHE_RELOAD_MINUTES, TimeUnit.MINUTES);
  }

  @Bean
  public CacheManager cacheManager() {
    CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
    caffeineCacheManager.setCaffeine(loadingCacheConfig());
    return caffeineCacheManager;
  }

}
