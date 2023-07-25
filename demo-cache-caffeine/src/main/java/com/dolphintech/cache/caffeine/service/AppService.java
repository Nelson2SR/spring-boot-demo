package com.dolphintech.cache.caffeine.service;

import com.dolphintech.cache.caffeine.entity.App;
import com.dolphintech.cache.caffeine.entity.User;

/**
 * <p>
 * UserService
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-11-16 16:53
 */
public interface AppService {
    /**
     * 保存或修改应用
     *
     * @param app 应用对象
     * @return 操作结果
     */
    App saveOrUpdate(App app);

    /**
     * 获取应用
     *
     * @param id key值
     * @return 返回结果
     */
    App get(Long id);

    /**
     * 删除
     *
     * @param id key值
     */
    void delete(Long id);
}
