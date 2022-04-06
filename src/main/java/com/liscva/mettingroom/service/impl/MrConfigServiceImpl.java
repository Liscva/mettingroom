package com.liscva.mettingroom.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liscva.framework.core.ThrowStatus;
import com.liscva.framework.core.exception.CoreException;
import com.liscva.framework.core.exception.Exception;
import com.liscva.framework.core.exception.NoSysConfigException;
import com.liscva.mettingroom.entity.po.MrConfig;
import com.liscva.mettingroom.mapper.MrConfigMapper;
import com.liscva.mettingroom.service.MrConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 会议系统配置 服务实现类
 * </p>
 *
 * @author 李诗诚
 * @since 2021-08-26
 */
@Service
public class MrConfigServiceImpl extends ServiceImpl<MrConfigMapper, MrConfig> implements MrConfigService {

    @Resource
    MrConfigMapper mrConfigMapper;

    @Override
    @Exception(code = 404404,msg = "{configCode}配置不存在")
    public MrConfig queryMrConfigByConfigCode(String configCode) {
        if(configCode!=null){
            throw new NoSysConfigException();
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("config_code",configCode);
        return mrConfigMapper.selectOne(queryWrapper);
    }

    @Override
    public String queryMrConfigValueByConfigCode(String configCode) {
        return queryMrConfigByConfigCode(configCode).getConfigValue();
    }
}
