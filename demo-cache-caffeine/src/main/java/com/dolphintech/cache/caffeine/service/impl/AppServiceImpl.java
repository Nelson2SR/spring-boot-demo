package com.dolphintech.cache.caffeine.service.impl;

import com.dolphintech.cache.caffeine.entity.App;
import com.dolphintech.cache.caffeine.entity.User;
import com.dolphintech.cache.caffeine.service.AppService;
import com.dolphintech.cache.caffeine.service.UserService;
import com.google.common.collect.Maps;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * <p>
 * UserService
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-11-16 16:54
 */
@Service
@Slf4j
public class AppServiceImpl implements AppService {
    /**
     * 模拟数据库
     */
    private static final Map<Long, App> DATABASES = Maps.newConcurrentMap();

    /**
     * 初始化数据
     */
    static {
        DATABASES.put(1L, new App(1L, "app1"));
        DATABASES.put(2L, new App(2L, "app2"));
        DATABASES.put(3L, new App(3L, "app3"));
    }

    /**
     * 保存或修改应用
     *
     * @param app 应用对象
     * @return 操作结果
     */
    @CachePut(value = "app", key = "#app.id")
    @Override
    public App saveOrUpdate(App app) {
        DATABASES.put(app.getId(), app);
        log.info("保存应用【app】= {}", app);
        return app;
    }

    /**
     * 获取应用
     *
     * @param id key值
     * @return 返回结果
     */
    @Cacheable(value = "app", key = "#id")
    @Override
    public App get(Long id) {
        // 我们假设从数据库读取
        log.info("查询应用【id】= {}", id);
        return DATABASES.get(id);
    }

    /**
     * 删除
     *
     * @param id key值
     */
    @CacheEvict(value = "app", key = "#id")
    @Override
    public void delete(Long id) {
        DATABASES.remove(id);
        log.info("删除应用【id】= {}", id);
    }
}
