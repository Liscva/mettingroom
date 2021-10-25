package com.liscva.mettingroom.service;

import com.liscva.mettingroom.entity.po.MrUserInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户信息扩展表 服务类
 * </p>
 *
 * @author 李诗诚
 * @since 2021-10-11
 */
public interface MrUserInfoService extends IService<MrUserInfo> {

    MrUserInfo findUserInfoByCode(Integer code);
}
