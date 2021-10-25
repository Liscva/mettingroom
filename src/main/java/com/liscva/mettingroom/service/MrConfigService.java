package com.liscva.mettingroom.service;

import com.liscva.mettingroom.entity.po.MrConfig;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会议系统配置 服务类
 * </p>
 *
 * @author 李诗诚
 * @since 2021-08-26
 */
public interface MrConfigService extends IService<MrConfig> {

    /**
     * 根据配置code获取系统配置
     * @author liscva
     * @date 2021/8/26 17:25
     * @param configCode
     * @return com.liscva.mettingroom.entity.vo.MrConfig
     */
    MrConfig queryMrConfigByConfigCode(String configCode);

    /**
     * 根据配置code获取系统配置的值
     * @author liscva
     * @date 2021/8/26 17:25
     * @param configCode
     * @return com.liscva.mettingroom.entity.vo.MrConfig
     */
    String queryMrConfigValueByConfigCode(String configCode);
}
