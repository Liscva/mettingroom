package com.liscva.mettingroom.service.impl;

import com.liscva.mettingroom.entity.po.MrUserInfo;
import com.liscva.mettingroom.mapper.MrUserInfoMapper;
import com.liscva.mettingroom.service.MrUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 用户信息扩展表 服务实现类
 * </p>
 *
 * @author 李诗诚
 * @since 2021-10-11
 */
@Service
public class MrUserInfoServiceImpl extends ServiceImpl<MrUserInfoMapper, MrUserInfo> implements MrUserInfoService {

    @Resource
    MrUserInfoMapper mrUserInfoMapper;

    @Override
    public MrUserInfo findUserInfoByCode(Integer code) {
        return mrUserInfoMapper.selectById(code);
    }
}
